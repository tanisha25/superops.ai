package com.springboot.user.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springboot.user.entity.Theatre;

@Repository
public interface TheatreRepository  extends JpaRepository<Theatre, UUID> {

	@Query("SELECT u FROM Theatre u WHERE u.name = ?1")
	Theatre findByName(String name);
}
