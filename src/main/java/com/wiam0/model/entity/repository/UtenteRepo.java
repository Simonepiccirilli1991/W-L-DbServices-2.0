package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wiam0.model.entity.Utente;

public interface UtenteRepo extends JpaRepository<Utente, String>{

	@Query(value = "SELECT * FROM utente WHERE utente.username = :username",
			 nativeQuery = true)
	Utente findByUsername(@Param("username") String username);
	
	Utente findByBt(String bt);
	boolean existsByUsername(String username);
	
	boolean existsByBt(String bt);
	
	// queery test Hibernate
	 @Query(value = "SELECT * FROM wiam0.utente WHERE wiam0.utente.username = :username",
			 nativeQuery = true)
		    Utente findByUsernameTest(@Param("username") String username);
}
