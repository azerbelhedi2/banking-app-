package com.hbase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hbase.models.CompteBancaire;

public interface CompteBancaireRepository extends JpaRepository<CompteBancaire, Long> {
	
	public CompteBancaire findById(long id ); 

}
