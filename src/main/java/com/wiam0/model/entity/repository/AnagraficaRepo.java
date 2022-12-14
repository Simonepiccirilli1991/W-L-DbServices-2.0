package com.wiam0.model.entity.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.wiam0.model.entity.Anagrafica;
import com.wiam0.model.entity.Utente;

public interface AnagraficaRepo extends JpaRepository<Anagrafica, Long>{

	void deleteByUtente(Utente utente);
	Anagrafica findByUtenteUsername(String username);
	void deleteByUtenteUsername(String username);
	
	@Query( value ="SELECT ALL FROM anagrafica WHERE nome\\ =:nome ", nativeQuery = true)
	List<Anagrafica>  anagraficaByName(@Param("nome") String nome);
	
	@Query(value ="SELECT ALL FROM anagragica wher cf\\ =: cf ", nativeQuery = true)
	Optional<List<Anagrafica>> anagraficaByCf(@Param("cf") String cf);
}
