//package com.hbase.controllers;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.hbase.services.ClientService;
//import com.hbase.services.CompteBancaireService;
//
//import com.hbase.models.Client;
//import com.hbase.models.CompteBancaire;
//
//@RestController
//@RequestMapping("/api/cb")
//@CrossOrigin(origins = "*")
//public class CompteBancaireController {
//	@Autowired
//	CompteBancaireService cbService;
//	@Autowired
//	ClientService clientService;
//
//	@GetMapping("")
//	public List<CompteBancaire> getAllCB() {
//		return cbService.getAllCB();
//
//	}
//
//	@GetMapping("/{id}")
//	public CompteBancaire getById(@PathVariable("id") long id) {
//		return cbService.getCB(id);
//	}
//
//	@GetMapping("/{idCb}/{idClient}")
//	public Client adddCompteClient(@PathVariable("idCb") long idCb, @PathVariable("idClient") long idClient) {
//		return clientService.addCompteToClient(idClient, idCb);
//
//	}
//
//	@PostMapping("")
//	public CompteBancaire addCompteBancaire(@RequestBody CompteBancaire cb) {
//		cbService.createCB(cb);
//		clientService.addCompteToClient(cb.getIdClient(), cb.getIdCb());
//		return cb;
//	}
//
//	@PatchMapping("")
//	public CompteBancaire updateCompteBancaire(@RequestBody CompteBancaire cb) {
//		CompteBancaire existingCb = cbService.getCB(cb.getIdCb());
//		// existingCb.setRib(cb.getRib());
//		existingCb.setSolde(cb.getSolde());
//		return existingCb;
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public void deleteCompteBancaire(@PathVariable("id") long id) {
//		cbService.deleteCB(id);
//	}
//
//	@GetMapping("/virement/{idEmetteur}/{idRecepteur}/{montant}")
//	public void virementCompteACompte(@PathVariable("idEmetteur") long idEmetteur,
//			@PathVariable("idRecepteur") long idRecepteur, @PathVariable("montant") double montant) {
//
//		cbService.virementCompteACompte(idEmetteur, idRecepteur, montant);
//
//	}
//
//	@GetMapping("/accrediter/{idCb}/{montant}")
//	public void accrediterCompte(@PathVariable("idCb") long idCb, @PathVariable("montant") double montant) {
//		CompteBancaire compte = cbService.getCB(idCb);
//		cbService.crediterCompte(compte, montant);
//
//	}
//
//	@GetMapping("/debiter/{idCb}/{montant}")
//	public void debiterCompte(@PathVariable("idCb") long idCb, @PathVariable("montant") double montant) {
//		CompteBancaire compte = cbService.getCB(idCb);
//		cbService.debiterCompte(compte, montant);
//
//	}
//}
