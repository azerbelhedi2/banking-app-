//package com.hbase.services;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.hbase.models.CompteBancaire;
//import com.hbase.repository.ClientRepository;
//import com.hbase.repository.CompteBancaireRepository;
//
//
//@Service
//public class CompteBancaireService implements ICompteBancaireService {
//
//	@Autowired
//	CompteBancaireRepository cbRepository;
//	@Autowired
//	ClientRepository clientRepository; 
//
//	@Override
//	public CompteBancaire getCB(long id) {
//		// TODO Auto-generated method stub
//		return cbRepository.findById(id);
//	}
//
//	@Override
//	public void createCB(CompteBancaire cb) {
//		
//		cb.setClient(clientRepository.findById(cb.getIdClient()));
//		cbRepository.save(cb);
//		System.out.println(cb.getClient());
//
//	}
//
//	@Override
//	public void deleteCB(long id) {
//		cbRepository.deleteById(id);
//
//	}
//
//	@Override
//	public void updateCB(CompteBancaire cb) {
//		cbRepository.save(cb);
//
//	}
//
//	@Override
//	public List<CompteBancaire> getAllCB() {
//		// TODO Auto-generated method stub
//		return cbRepository.findAll();
//	}
//	@Override
//	public boolean virementCompteACompte(long idEmetteur, long idRecepteur,double montant) {
//		CompteBancaire emetteur = cbRepository.findById(idEmetteur);
//		CompteBancaire recepteur = cbRepository.findById(idRecepteur); 
//		emetteur.debiterCompte(montant);
//		recepteur.crediterCompte(montant);
//		cbRepository.save(emetteur); 
//		cbRepository.save(recepteur);
//		return true; 
//	}
//	@Override
//	public double debiterCompte(CompteBancaire cb,double montant) {
//		cb.debiterCompte(montant);		
//		cbRepository.save(cb);
//		return cb.getSolde();
//	}
//	@Override
//	public double crediterCompte(CompteBancaire cb , double montant) {
//		cb.crediterCompte(montant);
//		cbRepository.save(cb); 
//		return cb.getSolde(); 
//	}
//
//}
