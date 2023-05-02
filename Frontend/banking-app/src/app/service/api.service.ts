import { Injectable } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Client } from '../models/client';
import { Comptebancaire } from '../models/comptebancaire';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  
  apiUrl : string;
  apiCompteCourant : string ; 
  apiCompteEpargne : string ; 
  apiBase:string ; 

constructor(private http : HttpClient) {

  // this.apiBase='http://localhost:8087/HbaseApi';
  this.apiBase='http://localhost:8086';
    this.apiUrl = this.apiBase+'/api/clients/';
    this.apiCompteCourant=this.apiBase+'/api/cb/courant/';
    this.apiCompteEpargne=this.apiBase+'/api/cb/epargne/';
   }

   // API FOR COMPTE COURANT 
   //BEGIN
   addCompteCourant(compte : Comptebancaire): Observable<Client> {
    return this.http.post<Comptebancaire>(this.apiCompteCourant,compte);
  }

  getCompteCourantById(id:number):Observable<Comptebancaire>{
    return this.http.get<Comptebancaire>(this.apiCompteCourant+id);
  }

  getAllComptesCourant(): Observable<Comptebancaire[]>{
    return this.http.get<Comptebancaire[]>(this.apiCompteCourant);
  } 
  
  updateCompteCourant(compte :Comptebancaire) : Observable<Comptebancaire>{
    
    return this.http.patch<Comptebancaire>(this.apiCompteCourant,compte);
  }
  batchUpdateCompteCourant(listComptes :Comptebancaire[]) : Observable<Comptebancaire[]>{
    
    return this.http.put<Comptebancaire[]>(this.apiCompteCourant+"batchUpdateSolde",listComptes);
  }

  deleteCompteCourant(compte : Comptebancaire) : Observable<Comptebancaire> {
    return this.http.delete<Comptebancaire>(this.apiCompteCourant+'delete/'+compte.idCb);
  }
  // ------------------------END--------------------------------------- 
  // API FOR COMPTE EPARGNE 
  //BEGIN 
  addCompteEpargne(compte : Comptebancaire): Observable<Client> {
    return this.http.post<Comptebancaire>(this.apiCompteEpargne,compte);
  }
  getCompteEpargneById(id:number):Observable<Comptebancaire>{
    return this.http.get<Comptebancaire>(this.apiCompteEpargne+id);
  }

  getAllComptesEpargne(): Observable<Comptebancaire[]>{
    return this.http.get<Comptebancaire[]>(this.apiCompteEpargne);
  } 
  
  updateCompteEpargne(compte :Comptebancaire) : Observable<Comptebancaire>{
    
    return this.http.patch<Comptebancaire>(this.apiCompteEpargne,compte);
  }

  deleteCompteEpargne(compte : Comptebancaire) : Observable<Comptebancaire> {
    return this.http.delete<Comptebancaire>(this.apiCompteEpargne+'delete/'+compte.idCb);
  }

  //END 

  //------------------------API FOR CLIENT --------------------------------
  addClient(client : Client): Observable<Client> {
    return this.http.post<Client>(this.apiUrl,client);
  }
  getClientById(id:number):Observable<Client>{
    return this.http.get<Client>(this.apiUrl+id);
  }

  getAllClients(): Observable<Client[]>{
    return this.http.get<Client[]>(this.apiUrl+'query');
  } 
  getClientByCin(cin : string): Observable<Client>{
    return this.http.get<Client>(this.apiUrl+'get/'+cin);
  } 
  
  updateClient(client :Client) : Observable<Client>{
    
    return this.http.patch<Client>(this.apiUrl,client);
  }

  deleteClient(client : Client) : Observable<Client> {
    return this.http.delete<Client>(this.apiUrl+'delete/'+client.idClient);
  }
  // -------------------------END --------------------------------------
  
  }
