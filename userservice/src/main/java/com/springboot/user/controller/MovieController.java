package com.springboot.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.user.service.MovieService;

@RestController
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	@RequestMapping(value="/movie/add",method = RequestMethod.GET)
	public void addMovie()
	{
		movieService.addMovie();
	}
}
