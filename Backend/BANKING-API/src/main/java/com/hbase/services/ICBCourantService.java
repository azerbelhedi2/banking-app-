package com.hbase.services;

import java.util.List;

import com.hbase.models.CompteCourant;
import com.hbase.models.CompteEpargne;

public interface ICBCourantService {

	public CompteCourant getCB(long id ); 
	public void createCB(CompteCourant cb); 
	public void deleteCB(long id ); 
	public void updateCB(CompteCourant cb); 
	public List<CompteCourant> getAllCB(); 
	double debiterCompte(CompteCourant cb, double montant);
	double crediterCompte(CompteCourant cb, double montant);
	//boolean virementCompteACompte(CompteCourant emetteur, CompteCourant recepteur, double montant);
	//boolean virementCompteACompte(long idEmetteur, long idRecepteur, double montant);
	List<CompteCourant> getComptesCourantByCin(String cin); 
	public void batchUpdateSolde(CompteCourant cb );
	
}
