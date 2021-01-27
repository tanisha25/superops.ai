package com.springboot.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.springboot.user.repo.MovieRepository;
import com.springboot.user.repo.ScreeningRepository;
import com.springboot.user.repo.SeatBookingRepository;
import com.springboot.user.repo.SeatsRepository;
import com.springboot.user.repo.TheatreRepository;
import com.springboot.user.repo.UserRepository;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories(basePackageClasses = {MovieRepository.class,ScreeningRepository.class,SeatBookingRepository.class,SeatsRepository.class,TheatreRepository.class,UserRepository.class})
public class UserServiceApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(UserServiceApplication.class, args);
	}
}
