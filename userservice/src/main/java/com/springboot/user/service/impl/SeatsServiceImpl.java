package com.springboot.user.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.user.entity.Movie;
import com.springboot.user.entity.Screening;
import com.springboot.user.entity.SeatBooking;
import com.springboot.user.entity.Seats;
import com.springboot.user.entity.Theatre;
import com.springboot.user.entity.Users;
import com.springboot.user.repo.MovieRepository;
import com.springboot.user.repo.SeatBookingRepository;
import com.springboot.user.repo.SeatsRepository;
import com.springboot.user.repo.TheatreRepository;
import com.springboot.user.repo.UserRepository;
import com.springboot.user.service.SeatsService;
import com.springboot.user.utils.Constants;
import com.springboot.user.utils.EnumUtils.Status;
import com.springboot.user.utils.Utils;

@Service
public class SeatsServiceImpl implements SeatsService{

	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private SeatBookingRepository seatsBookingRepository;
	
	@Autowired
	private SeatsRepository seatsRepository;
	
	Logger log= LoggerFactory.getLogger(SeatsServiceImpl.class);
	@Override
	public UUID bookSeats(String theatreName, String movieName, String userName, int noOfSeats,int[] seats) throws Exception {
		Theatre theatre = theatreRepository.findByName(theatreName);
		UUID seatBookingId = null;
		if(Utils.isEmpty(theatre))
		{
			throw new Exception(Constants.NO_THEATRE_FOUND);
		}
		Users user = userRepository.findByName(userName);
		if(Utils.isEmpty(user))
		{
			throw new Exception(Constants.NO_USER_FOUND);
		}
		Movie movie = movieRepository.findByName(movieName);
		if(Utils.isEmpty(movieName))
		{
			throw new Exception(Constants.NO_MOVIE_FOUND);
		}
		Optional<Screening> screen = theatre.getScreenings().stream().filter(t -> !Utils.isEmpty(t.getMovie())
				                                 && movie.getId().equals(t.getMovie().getId())).findAny();
		if(!screen.isPresent())
		{
			throw new Exception(Constants.NO_MOVIE_FOUND_IN_THEATRE);
		}
		else {
			if(noOfSeats > Constants.MAX_NO_OF_SEATS_BOOKED)
				throw new Exception(Constants.MAX_SEAT_MESSAGE);
			List<SeatBooking> seatBookingList = seatsBookingRepository.findAll();
			if(!Utils.isEmpty(seatBookingList))
				seatBookingList.removeIf(b-> b.getUser().getId().equals(user.getId()) && b.getSeatsRequested().contains(Arrays.stream(seats).boxed().collect(Collectors.toList())) && b.getStatus().equals(Status.unpaid.name()));
			if(Utils.isEmpty(seatBookingList))
			{
				//only one user request
				SeatBooking seatBookingObj = new SeatBooking();
				seatBookingObj.setUser(user);
				seatBookingObj.setScreening(screen.get());
				seatBookingObj.setStatus(Status.unpaid.name());
				seatBookingObj.setDeleted(false);
				seatBookingObj.setSeatsRequested(Arrays.stream(seats).boxed().collect(Collectors.toList()));
				seatsBookingRepository.save(seatBookingObj);
				log.info("User " + userName+ "allotted "+seatBookingObj.getSeatsRequested().toString()+"seats");
				seatBookingId = seatBookingObj.getId();
			}
			else {
				//other user request
				int max = seats.length;
				int index = 0;
				int selected = -1;
				boolean tie = false;
				for(SeatBooking seatBook : seatBookingList)
				{
					if(seatBook.getSeatsRequested().size() == max) {
						tie = true;
						selected = index;
					}
					else if(seatBook.getSeatsRequested().size()> max) {
						max=seatBook.getSeatsRequested().size();
						selected = index;
						tie = false;
					}
					index++;
				}
				int n=0;
				if(tie) {
					n = (int) Math.random() % 2;
					if(n == 1) {
						log.info("User " + userName+ " allotted "+seatBookingList.get(selected).getSeatsRequested().toString()+"seats");
						seatBookingList.remove(selected);
						seatBookingList.forEach(s->{s.setDeleted(true);});
						seatsBookingRepository.saveAll(seatBookingList);
					}
					
				}
				else if((selected == -1 && max == seats.length)||(tie && n == 0))
				{// no tie new request gets the seat
					SeatBooking booking = new SeatBooking();
					booking.setUser(user);
					booking.setScreening(screen.get());
					booking.setSeatsRequested(Arrays.stream(seats).boxed().collect(Collectors.toList()));
					booking.setStatus(Status.unpaid.name());
					booking.setDeleted(false);
					log.info("User " + userName+ " allotted "+booking.getSeatsRequested().toString()+"seats");
					seatBookingList.forEach(s -> {s.setDeleted(true); });
					seatBookingList.add(booking);
					seatsBookingRepository.saveAll(seatBookingList);
					seatBookingId = booking.getId();
				}
				
			}
			if(!Utils.isEmpty(seatBookingId))
				System.out.println("Book the seats");
			return seatBookingId;
		}
	}
	@Transactional(rollbackFor=Exception.class)
	@Override
	public void payForSeats(UUID seatBookingId, long timestamp) throws Exception {
		SeatBooking seatBookingService = seatsBookingRepository.getOne(seatBookingId);
		if(!Utils.isEmpty(seatBookingService))
		{
			int n = (int)Math.random() % 2;
			String status = (n == 1)? Status.success.name() :Status.failure.name();
			List<Seats> seats = new ArrayList<>(seatBookingService.getSeatsRequested().size());
			if(n == 1)
			{
				for(int i=0;i<seatBookingService.getSeatsRequested().size();i++)
				{
					Seats seat = new Seats();
					seat.setAlreadyBooked(true);
					seat.setName(seatBookingService.getSeatsRequested().get(i));
					seat.setScreening(seatBookingService.getScreening());
					seat.setUser(seatBookingService.getUser());
					seats.add(seat);
				}
				seatBookingService.setStatus(Status.paid.name());
				seatsRepository.saveAll(seats);
				seatsBookingRepository.save(seatBookingService);
			}
		}
		if(System.currentTimeMillis() - timestamp > 120000)
			throw new Exception(Constants.TIME_OUT);
	}
	
	

}
