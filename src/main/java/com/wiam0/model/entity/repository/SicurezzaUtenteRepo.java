package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.SicurezzaUtente;

public interface SicurezzaUtenteRepo extends JpaRepository<SicurezzaUtente, Long>{

	SicurezzaUtente findByUtenteUsername(String username);
	
	void deleteByUtenteUsername(String username);
	
	boolean existsByUtenteUsername(String username);
	
}
