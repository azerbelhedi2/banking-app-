package com.hbase.models;



import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.iban4j.CountryCode;
import org.iban4j.Iban;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CompteBancaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id    
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idGen")
	@Column(name = "id_cb" )
	private long idCb ;
	@Column(name = "solde")
	private double solde ; 
	@Column(name = "rib" )
	private String rib = new Iban.Builder()
            .countryCode(CountryCode.TN)
            .bankCode("19043")
            .accountNumber("00234573201")
            .build().toString();
	@Column(name = "dateOuverture" )
	private String dateOuverture = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	@Column(name="id_client"  )
	private long idClient;
	@Column(name="type_compte")
	private String typeDeCompte;
	@Column(name="cin_client")
	private String cinClient; 
	
	
	
	public String getCin_client() {
		return cinClient;
	}
	public void setCin_client(String cinClient) {
		this.cinClient = cinClient;
	}
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	Client client ;
	
	
	
	public String getTypeDeCompte() {
		return typeDeCompte;
	}
	public void setTypeDeCompte(String typeDeCompte) {
		this.typeDeCompte = typeDeCompte;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDateOuverture() {
		return dateOuverture;
	}
	public void setDateOuverture(String date) {
		this.dateOuverture = date;
	}
	
	public long getIdCb() {
		return idCb;
	}
	public void setIdCb(long idCb) {
		this.idCb = idCb;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	
	public String getRib() {
		return rib;
	}
	public void setRib(String rib) {
		this.rib = rib;
	}
	public long getIdClient() {
		return idClient;
	}
	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public CompteBancaire(long idCb, double solde,long idClient, String cin ,Client client) {
		
		super();
		this.idCb = idCb;
		this.solde = solde;
		this.cinClient=cin ; 
		this.idClient = idClient;
		this.client = client;
		
	}
	public CompteBancaire() {
		super();
		
		;
	} 
	
	public double debiterCompte(double montant) {
		this.solde-=montant;
		return this.getSolde();
	}
	public double crediterCompte(double montant) {
		this.solde+=montant; 
		return this.solde;
	}
	public void virement(CompteBancaire recepteur, double montant) {
		this.debiterCompte(montant);
		recepteur.crediterCompte(montant);
		
	}
	@Override
	public String toString() {
		return "CompteBancaire [idCb=" + idCb + ", solde=" + solde + ", rib=" + rib + ", dateOuverture="
				+ dateOuverture + ", idClient=" + idClient + ", client=" + client + "]";
	}
	
	
	
	
}