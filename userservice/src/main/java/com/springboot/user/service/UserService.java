package com.springboot.user.service;

public interface UserService {

	public void addUser(String userName);

	public void removeUser(String userName) throws Exception;
}
