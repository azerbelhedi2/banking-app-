package com.hbase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbase.models.CompteCourant;
import com.hbase.models.CompteEpargne;
import com.hbase.models.CompteCourant;
import com.hbase.repository.CBCourantRepository;
import com.hbase.repository.ClientRepository;

@Service
public class CBCourantService implements ICBCourantService {

	@Autowired
	CBCourantRepository cbRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	HbaseService hbaseService;

	@Override
	public CompteCourant getCB(long id) {
		// TODO Auto-generated method stub
		return cbRepository.findById(id);
	}

	@Override
	public void createCB(CompteCourant cb) {

		cb.setClient(clientRepository.findById(cb.getIdClient()));
		cbRepository.save(cb);
		System.out.println(cb.getClient());

	}

	@Override
	public void deleteCB(long id) {
		cbRepository.deleteById(id);

	}

	@Override
	public void updateCB(CompteCourant cb) {
		cbRepository.save(cb);

	}

	@Override
	public List<CompteCourant> getAllCB() {
		// TODO Auto-generated method stub
		return cbRepository.findAll();
	}

//	@Override
//	public boolean virementCompteACompte(long idEmetteur, long idRecepteur,double montant) {
//		CompteCourant emetteur = cbRepository.findById(idEmetteur);
//		CompteCourant recepteur = cbRepository.findById(idRecepteur); 
//		emetteur.debiterCompte(montant);
//		recepteur.crediterCompte(montant);
//		cbRepository.save(emetteur); 
//		cbRepository.save(recepteur);
//		return true; 
//	}
	@Override
	public double debiterCompte(CompteCourant cb, double montant) {
		cb.debiterCompte(montant);
		cbRepository.save(cb);
		return cb.getSolde();
	}

	@Override
	public double crediterCompte(CompteCourant cb, double montant) {
		cb.crediterCompte(montant);
		cbRepository.save(cb);
		return cb.getSolde();
	}

	@Override
	public List<CompteCourant> getComptesCourantByCin(String cin) {
		return cbRepository.findByCinClient(cin);
	}

	@Override
	public void batchUpdateSolde(CompteCourant cb) {
		// TODO Auto-generated method stub
		
		String sqlUpdate = "UPSERT INTO COMPTECOURANT(ID_CB,SOLDE)values("+cb.getIdCb()+","+cb.getSolde()+")";
		
//		String sql = "UPSERT INTO USER(id,username,password)VALUES(" + user.getId() + ",'" + user.getFirstname() + "','"
//				+ user.getLastname() + "','" + user.getEmail() + "','" + user.getPassword()
//				+ "') ON DUPLICATE KEY UPDATE username=username";
		// hBaseBaseDAOImpl.batchUpdate(sql);
		hbaseService.batchUpdate(sqlUpdate);

	}

}
