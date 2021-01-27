package com.springboot.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.user.service.TheatreService;

@RestController
public class TheatreController {
	@Autowired
	private TheatreService theatreService;
	
	@RequestMapping(value="/addTheatre",method = RequestMethod.GET)
	public void addTheatre()
	{
		theatreService.addTheatre();
	}
	
}