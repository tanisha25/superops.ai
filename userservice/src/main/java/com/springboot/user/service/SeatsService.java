package com.springboot.user.service;

import java.util.UUID;

public interface SeatsService {
	
	public UUID bookSeats(String theatreName, String movieName, String userName, int noOfSeats, int[] seatList) throws Exception;

	public void payForSeats(UUID seatBookingId, long timestamp) throws Exception;

}
