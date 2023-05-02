//package com.hbase.services;
//
//import java.util.List;
//
//import com.hbase.models.CompteBancaire;
//
//
//
//public interface ICompteBancaireService {
//	
//	public CompteBancaire getCB(long id ); 
//	public void createCB(CompteBancaire cb); 
//	public void deleteCB(long id ); 
//	public void updateCB(CompteBancaire cb); 
//	public List<CompteBancaire> getAllCB(); 
//	double debiterCompte(CompteBancaire cb, double montant);
//	double crediterCompte(CompteBancaire cb, double montant);
//	//boolean virementCompteACompte(CompteBancaire emetteur, CompteBancaire recepteur, double montant);
//	boolean virementCompteACompte(long idEmetteur, long idRecepteur, double montant); 
//
//}
