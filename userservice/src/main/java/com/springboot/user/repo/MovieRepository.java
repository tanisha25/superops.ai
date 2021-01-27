package com.springboot.user.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.user.entity.Movie;

@Repository
public interface MovieRepository  extends JpaRepository<Movie, UUID> {

	@Query("SELECT u FROM Movie u WHERE u.name = ?1")
	Movie findByName(String name);
}
