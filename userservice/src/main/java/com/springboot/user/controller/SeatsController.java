package com.springboot.user.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.user.service.SeatsService;
import com.springboot.user.utils.Utils;

@RestController
public class SeatsController {
	@Autowired
	private SeatsService seatBookingService;
	@RequestMapping(value="/book/seats/{theatreName}/{movieName}/{userName}/{noOfSeats}/{seatsId}",method = RequestMethod.GET)
	@ResponseBody
	public void bookSeats(@PathVariable String theatreName, @PathVariable String movieName, @PathVariable String userName, @PathVariable int noOfSeats, @PathVariable int[] seatsId) throws Exception
	{
		UUID seatBookingId = seatBookingService.bookSeats(theatreName, movieName, userName, noOfSeats, seatsId);
		if(!Utils.isEmpty(seatBookingId))
			seatBookingService.payForSeats(seatBookingId, System.currentTimeMillis());
		
	}
	
	
}
