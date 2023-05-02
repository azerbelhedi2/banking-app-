package com.hbase.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import com.hbase.models.Client;
import com.hbase.models.CompteBancaire;
import com.hbase.repository.ClientRepository;
import com.hbase.repository.CompteBancaireRepository;

@Service
public class ClientService implements IClientService {

	@Autowired
	ClientRepository clientRepository;
	@Autowired
	CompteBancaireRepository cbRepository; 
	

	@Autowired
	HbaseService hbaseService;

	@Override
	public Client getClient(long id) {
		return clientRepository.findById(id);
	}

	@Override
	public void createClient(Client client) {

		clientRepository.save(client);

	}

	@Override
	public void deleteClient(long id) {

		clientRepository.deleteById(id);

	}

	@Override
	public void updateClient(Client client) {
		clientRepository.save(client);
	}
	
	@Override
	public List<Client> getAllClient() {
//		Pageable pageable = PageRequest.of(0,1000); 
//		Page<Client> listOfClients = clientRepository.findAll(pageable);
//		List<Client> clients = listOfClients.getContent();
//		return clients;
		return clientRepository.findAll();


	}
	@Override
	public List<Client> getLatestClients()
	{
		Pageable pageable = PageRequest.of(0,100000); 
		Page<Client> listOfClients = clientRepository.findAll(pageable);
		List<Client> clients = listOfClients.getContent();
		
		return clients;
		
	}

	@Override
	public Client addCompteToClient(long idClient, long idCb) {
		
		CompteBancaire cb ; 
		cb = cbRepository.findById(idCb); 
		Client client ;
		client = clientRepository.findById(idClient);
		cb.setClient(client);
		cb.setIdClient(idClient);
		client.addToListComptes(cb);
		clientRepository.save(client); 
		cbRepository.save(cb);
		return client ; 
		
	}

	public List<Client> getAllClients() {
		return clientRepository.findAllClients();
	}

	@Override
	public List<Client> QueryAllClient() {
		 String sql = "SELECT * FROM CLIENT";
	        return hbaseService.getJdbcTemplate().query(sql,new BeanPropertyRowMapper(Client.class));
	}

	@Override
	public Client getClientByCin(String cin) {
		// TODO Auto-generated method stub
		return clientRepository.findByCin(cin);
	}



}
