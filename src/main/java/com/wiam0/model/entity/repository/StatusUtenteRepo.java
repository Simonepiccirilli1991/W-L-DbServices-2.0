package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.StatusUtente;

public interface StatusUtenteRepo extends JpaRepository<StatusUtente, Long>{

	
	StatusUtente findByUtenteUsername(String username);
	
	boolean existsByUtenteUsername(String username);
	
	
}
