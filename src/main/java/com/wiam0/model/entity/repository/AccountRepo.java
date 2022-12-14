package com.wiam0.model.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.wiam0.model.entity.Account;
import com.wiam0.model.entity.Utente;

public interface AccountRepo extends JpaRepository<Account, Long>{

	void deleteByUtente(Utente utente);
	Account findByUtenteUsername(String username);
	void deleteByUtenteUsername(String username);
	
	  @Query(value = """
		        UPDATE account
		        SET saldoAttuale = :saldoAttuale
		        SET debito = :debito
		        WHERE numeroConto = :numeroConto
		        """,
		        nativeQuery = true)
		    @Modifying
		    @Transactional
		    int addBalanceDebit(@Param("numeroConto") String numeroConto, @Param("saldoAttuale") double saldoAttuale, @Param("debito") Double debito);
	  
//	  @Query(value = """
//				UPDATE account
//				SET saldoattuale =:saldoattuale
//				WHERE numeroconto =:numeroconto
//				""",
//				nativeQuery = true)

	  @Query(value = "UPDATE wiam0.account SET saldoattuale\\=:saldoattuale WHERE numeroconto\\=:numeroconto",nativeQuery = true)
	  @Modifying
	  @Transactional
	  int addBalance(@Param("numeroconto") String numeroconto, @Param("saldoattuale") double saldoattuale);
}
