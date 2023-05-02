package com.hbase.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hbase.models.Client;
import com.hbase.models.CompteCourant;
import com.hbase.models.CompteEpargne;
import com.hbase.services.CBEpargneService;
import com.hbase.services.ClientService;


@RestController
@RequestMapping("/api/cb/epargne")
@CrossOrigin(origins = "*")
public class CBEpargneController {

	@Autowired
	CBEpargneService cbService; 
	@Autowired
	ClientService clientService;

	@GetMapping("")
	public List<CompteEpargne> getAllCB() {
		return cbService.getAllCB();

	}

	@GetMapping("/{id}")
	public CompteEpargne getById(@PathVariable("id") long id) {
		return cbService.getCB(id);
	}
	@GetMapping("/get/{cin}")
	public List<CompteEpargne> getCompteByCin(@PathVariable("cin") String cin ) {
		return cbService.getComptesEpargneByCin(cin);
	}
	@GetMapping("/{idCb}/{idClient}")
	public Client adddCompteClient(@PathVariable("idCb") long idCb, @PathVariable("idClient") long idClient) {
		return clientService.addCompteToClient(idClient, idCb);

	}

	@PostMapping("")
	public CompteEpargne addCompteEpargne(@RequestBody CompteEpargne cb) {
		cbService.createCB(cb);
		clientService.addCompteToClient(cb.getIdClient(), cb.getIdCb());
		return cb;
	}

	@PatchMapping("")
	public CompteEpargne updateCompteEpargne(@RequestBody CompteEpargne cb) {
		CompteEpargne existingCb = cbService.getCB(cb.getIdCb());
		// existingCb.setRib(cb.getRib());
		existingCb.setSolde(cb.getSolde());
		cbService.updateCB(existingCb);
		return existingCb;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteCompteEpargne(@PathVariable("id") long id) {
		cbService.deleteCB(id);
	}

//	@GetMapping("/virement/{idEmetteur}/{idRecepteur}/{montant}")
//	public void virementCompteACompte(@PathVariable("idEmetteur") long idEmetteur,
//			@PathVariable("idRecepteur") long idRecepteur, @PathVariable("montant") double montant) {
//
//		cbService.virementCompteACompte(idEmetteur, idRecepteur, montant);
//
//	}

	@PutMapping("/accrediter/{idCb}/{montant}")
	public void accrediterCompte(@PathVariable("idCb") long idCb, @PathVariable("montant") double montant) {
		CompteEpargne compte = cbService.getCB(idCb);
		cbService.crediterCompte(compte, montant);

	}

	@PutMapping("/debiter/{idCb}/{montant}")
	public void debiterCompte(@PathVariable("idCb") long idCb, @PathVariable("montant") double montant) {
		CompteEpargne compte = cbService.getCB(idCb);
		cbService.debiterCompte(compte, montant);

	}
}
