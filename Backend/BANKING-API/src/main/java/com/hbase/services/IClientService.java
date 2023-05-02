package com.hbase.services;

import java.util.List;

import com.hbase.models.Client;
import com.hbase.models.CompteBancaire;

public interface IClientService {
	
	public Client getClient(long id); 
	public void createClient(Client client); 
	public void deleteClient(long id); 
	public void updateClient(Client client); 
	public List<Client> getAllClient(); 
	public Client addCompteToClient(long idClient , long idCb); 
	public List<Client> getAllClients();
	public List<Client> QueryAllClient();
	public List<Client> getLatestClients();
	
	public Client getClientByCin(String cin); 

	
	

}
