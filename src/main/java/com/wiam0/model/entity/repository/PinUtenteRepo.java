package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.PinUtente;

public interface PinUtenteRepo extends JpaRepository<PinUtente, Long>{

	PinUtente findByUtenteUsername(String username);
	
}
