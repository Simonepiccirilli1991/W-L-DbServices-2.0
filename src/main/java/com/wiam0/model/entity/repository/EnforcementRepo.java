package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.Enforcement;

public interface EnforcementRepo extends JpaRepository<Enforcement, Long>{

	
	Enforcement findByUtenteUsername(String username);
	
	
}
