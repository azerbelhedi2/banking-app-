package com.hbase.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.hbase.models.CompteCourant;


public interface CBCourantRepository extends JpaRepository<CompteCourant, Long>{
	
	public CompteCourant findById(long id );
	public List<CompteCourant> findByCinClient(String cin); 
	

}
