package com.wiam0.model.entity.repository;

import java.util.List;

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
	
	@Query(value = "SELECT * FROM utente",
			nativeQuery = true)
	List<Utente> findAllUtente();
	
	Utente findByBt(String bt);
	boolean existsByUsername(String username);

	boolean existsByBt(String bt);
	
	
	// queery test Hibernate
	@Query(value = "SELECT * FROM wiam0.utente WHERE wiam0.utente.username = :username",
			nativeQuery = true)
	Utente findByUsernameTest(@Param("username") String username);

	@Query(value = "SELECT * FROM wiam0.utente",
			nativeQuery = true)
	List<Utente> findAllTest();
	
//	@Query(value = "insert into wiam0.utente (id, bt, abi, username, email, cellulare ) values (:id ,:bt, :abi, :username, :email, :cellulare)",
//			nativeQuery = true)
//	void saveUtente(@Param("utente") Utente utente);
}
