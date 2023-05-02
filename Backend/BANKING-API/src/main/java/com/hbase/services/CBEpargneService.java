package com.hbase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbase.models.CompteEpargne;
import com.hbase.models.CompteEpargne;
import com.hbase.repository.CBEpargneRepository;
import com.hbase.repository.ClientRepository;
@Service
public class CBEpargneService implements ICBEpargneService {

	
	@Autowired
	CBEpargneRepository cbRepository ; 
	@Autowired
	ClientRepository clientRepository; 

	@Override
	public CompteEpargne getCB(long id) {
		// TODO Auto-generated method stub
		return cbRepository.findById(id);
	}

	@Override
	public void createCB(CompteEpargne cb) {
		
		cb.setClient(clientRepository.findById(cb.getIdClient()));
		cbRepository.save(cb);
		System.out.println(cb.getClient());

	}

	@Override
	public void deleteCB(long id) {
		cbRepository.deleteById(id);

	}

	@Override
	public void updateCB(CompteEpargne cb) {
		cbRepository.save(cb);

	}

	@Override
	public List<CompteEpargne> getAllCB() {
		// TODO Auto-generated method stub
		return cbRepository.findAll();
	}
//	@Override
//	public boolean virementCompteACompte(long idEmetteur, long idRecepteur,double montant) {
//		CompteEpargne emetteur = cbRepository.findById(idEmetteur);
//		CompteEpargne recepteur = cbRepository.findById(idRecepteur); 
//		emetteur.debiterCompte(montant);
//		recepteur.crediterCompte(montant);
//		cbRepository.save(emetteur); 
//		cbRepository.save(recepteur);
//		return true; 
//	}
	@Override
	public double debiterCompte(CompteEpargne cb,double montant) {
		cb.debiterCompte(montant);		
		cbRepository.save(cb);
		return cb.getSolde();
	}
	@Override
	public double crediterCompte(CompteEpargne cb , double montant) {
		cb.crediterCompte(montant);
		cbRepository.save(cb); 
		return cb.getSolde(); 
	}

	@Override
	public List<CompteEpargne> getComptesEpargneByCin(String cin) {
		// TODO Auto-generated method stub
		return cbRepository.findByCinClient(cin);
	}

}
