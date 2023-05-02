//package com.hbase.models;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.SequenceGenerator;
//import javax.persistence.Table;
//
//@Entity
//@Table(name="TRANSACTION")
//@SequenceGenerator(name="idGen_transaction",sequenceName="auto_increment_transaction",initialValue = 1,allocationSize = 1) 
//public class Transaction {
//	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//	
//	@Id    
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idGen_transaction")
//	@Column(name = "id_transaction" )
//	private long idTransaction; 
//	
//	@Column(name="TYPE_TRANSACTION")
//	private String typeDeTransaction;
//	
//	@Column(name="MONTANT")
//	private String montant;
//	
//	@Column(name="DATE_TRANSACTION") 
//	private String date_transaction = formatter.format(new Date()); 
//	
//	
//	
//
//}
