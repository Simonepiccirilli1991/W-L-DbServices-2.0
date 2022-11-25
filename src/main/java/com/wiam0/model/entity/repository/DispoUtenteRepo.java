package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wiam0.model.entity.DispoConteUtente;
import com.wiam0.model.entity.Utente;

public interface DispoUtenteRepo extends JpaRepository<DispoConteUtente, Long>{

	void deleteByUtente(Utente utente);
	DispoConteUtente findByUtenteUsername(String username);
	void deleteByUtenteUsername(String username);
}
