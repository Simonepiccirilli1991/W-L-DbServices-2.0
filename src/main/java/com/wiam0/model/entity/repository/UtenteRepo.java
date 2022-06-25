package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.Utente;

public interface UtenteRepo extends JpaRepository<Utente, String>{

	
	Utente findByUsername(String username);
	
	Utente findByBt(String bt);
	boolean existsByUsername(String username);
	
	boolean existsByBt(String bt);
	
}
