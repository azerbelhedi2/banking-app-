package com.hbase.services;

import java.util.List;

import com.hbase.models.CompteEpargne;

public interface ICBEpargneService {

	public CompteEpargne getCB(long id ); 
	public void createCB(CompteEpargne cb); 
	public void deleteCB(long id ); 
	public void updateCB(CompteEpargne cb); 
	public List<CompteEpargne> getAllCB(); 
	double debiterCompte(CompteEpargne cb, double montant);
	double crediterCompte(CompteEpargne cb, double montant);
	//boolean virementCompteACompte(CompteEpargne emetteur, CompteEpargne recepteur, double montant);
	//boolean virementCompteACompte(long idEmetteur, long idRecepteur, double montant); 
	List<CompteEpargne> getComptesEpargneByCin(String cin); 
}
