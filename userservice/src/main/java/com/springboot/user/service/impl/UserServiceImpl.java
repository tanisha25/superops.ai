package com.springboot.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.user.entity.Users;
import com.springboot.user.repo.UserRepository;
import com.springboot.user.utils.Utils;
import com.springboot.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public void addUser(String userName) {
		Users user = userRepository.findByName(userName);
		if(Utils.isEmpty(user))
		{
			 user = new Users();
			 user.setName(userName);
			 user.setDeleted(false);
			 userRepository.save(user);
		}
		System.out.println("user added");
	}
	
	@Override
	public void removeUser(String userName) throws Exception {
	   Users user = userRepository.findByName(userName);
	   if(!Utils.isEmpty(user))
	   {
		   user.setDeleted(true);
		   userRepository.save(user);
	   }
	   else {
			throw new Exception("User "+ userName + " does not exist");

	   }
	}

	

}
