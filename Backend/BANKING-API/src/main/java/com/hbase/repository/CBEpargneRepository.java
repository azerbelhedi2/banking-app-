package com.hbase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbase.models.CompteCourant;
import com.hbase.models.CompteEpargne;

@Repository
public interface CBEpargneRepository extends JpaRepository<CompteEpargne, Long>{

	
	CompteEpargne findById(long id); 
	public List<CompteEpargne> findByCinClient(String cin); 
}
