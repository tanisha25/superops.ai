package com.springboot.user.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.user.entity.Movie;
import com.springboot.user.entity.Screening;
import com.springboot.user.entity.Theatre;
import com.springboot.user.repo.MovieRepository;
import com.springboot.user.repo.ScreeningRepository;
import com.springboot.user.repo.TheatreRepository;
import com.springboot.user.service.MovieService;
import com.springboot.user.utils.EnumUtils.Movies;
import com.springboot.user.utils.Utils;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Autowired
	private ScreeningRepository screeningRepository;
	
	Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);
	@Override
	public void addMovie() {
		Movies movieArr[]= Movies.values();
		List<Movie> movieList = new ArrayList<>(movieArr.length);
		for(Movies movieName: movieArr) {
			Movie movie = movieRepository.findByName(movieName.toString());
			if(Utils.isEmpty(movie))
			{
				movie = new Movie();
				movie.setName(movieName.toString());
				List<Theatre> theatres = theatreRepository.findAll();
				Set<Screening> screen = new HashSet<>(movieArr.length);
				if(!Utils.isEmpty(theatres))
				{			
					theatres.forEach(t -> {
					    List<Screening> screens = t.getScreenings();
					    if(!Utils.isEmpty(screens))
					    {
					    	Optional<Screening> first = screens.stream().filter(s-> Utils.isEmpty(s.getMovie())).findAny();
					    	if(first.isPresent())
					    	{
					    		Screening s = first.get();
					    		s.setTheatre(t);
					    		screen.add(s);
					    	}
					    }
					});
					movieList.add(movie);	
					if(!Utils.isEmpty(movieList))
						movieRepository.saveAll(movieList);
					for(Screening s: screen)
					{
						s.setMovie(movie);
						screeningRepository.save(s);
					}
				}
			}
		}
		
		System.out.print("Movie added");
	}

}
