package com.hbase.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;


@Entity(name="COMPTEEPARGNE")
@SequenceGenerator(name="idGen",sequenceName="auto_increment_compte_bancaire_courant",initialValue = 1,allocationSize = 1) 
public class CompteEpargne extends CompteBancaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
		super.setTypeDeCompte("CompteEpargne");
	}

	public CompteEpargne(long idCb, double solde, long idClient,String cin, Client client) {
		super(idCb, solde, idClient,cin ,client);
		// TODO Auto-generated constructor stub
	}


}
