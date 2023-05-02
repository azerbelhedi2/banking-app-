package com.hbase.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hbase.models.Client;


@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
	
	
	public Client findById(long id);
	
	@Query(value ="SELECT * FROM CLIENT", nativeQuery=true)
	List<Client> findAllClients();
	
	Client findByCin(String cin);
	
	


}
