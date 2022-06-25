package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.entity.Utente;

public interface AnagraficaRepo extends JpaRepository<Anagrafica, Long>{

	void deleteByUtente(Utente utente);
	Anagrafica findByUtenteUsername(String username);
	void deleteByUtenteUsername(String username);
	
}
