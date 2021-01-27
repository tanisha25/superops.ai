package com.springboot.user.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.user.entity.Screening;

@Repository
public interface ScreeningRepository  extends JpaRepository<Screening, UUID> {

	
}
