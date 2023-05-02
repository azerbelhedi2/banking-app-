package com.hbase.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;





@Entity
@Table(name="CLIENT")
public class Client {
	
	
	
	@Id
	@SequenceGenerator(name="auto_increment",sequenceName="auto_increment_client",initialValue = 10000,allocationSize = 1)        
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="auto_increment")
	private long idClient ; 
	@Column(name = "firstname",length = 100 )
	private String firstname; 
	@Column(name = "lastname",length = 100)
	private String lastname ; 
	@Column(name = "email",length = 100)
	private String email ;
	@Column(name = "adresse",length = 200)
	private String adresse;
	@Column(name = "date_naissance",length = 100)
	private String date_naissance;
	@Column(name = "date_inscription",length = 1000)
	private String date_inscription = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	@Column(name = "tel",length = 100)
	private String tel ;
	@Column(name="CIN" , length = 100)
	private String cin; 
	
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	
	@JsonProperty(access = Access.READ_ONLY)
	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<CompteBancaire> listComptes;
	
	
	public List<CompteBancaire> getListComptes() {
		return listComptes;
	}
	public void setListComptes(List<CompteBancaire> listComptes) {
		this.listComptes = listComptes;
	}
	public void addToListComptes(CompteBancaire cb) {
		this.listComptes.add(cb);
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getDate_inscription() {
		return date_inscription;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	
	public long getIdClient() {
		return idClient;
	}
	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(long idClient, String firstname, String lastname, String email, String adresse, String date_naissance,
			String date_inscription, String tel) {
		super();
		this.idClient = idClient;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.adresse = adresse;
		this.date_naissance = date_naissance;
		this.date_inscription = date_inscription;
		this.tel = tel;
//		this.listComptes = listComptes;
	}


	
	
	
	

}
