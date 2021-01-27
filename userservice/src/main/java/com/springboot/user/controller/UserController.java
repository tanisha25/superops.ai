package com.springboot.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.user.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping(value="/add/{userName}",method = RequestMethod.GET)
	public void addUser(@PathVariable String userName)
	{
		userService.addUser(userName);
	}
	@RequestMapping(value="/remove/{userName}",method = RequestMethod.GET)
	public void removeUser(@PathVariable String userName) throws Exception
	{
		userService.removeUser(userName);
	}
}