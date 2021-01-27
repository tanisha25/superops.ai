package com.springboot.user.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.user.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID>{

	@Query("SELECT u FROM Users u WHERE u.name = :name")
	Users findByName(@Param("name") String name);
	
}
