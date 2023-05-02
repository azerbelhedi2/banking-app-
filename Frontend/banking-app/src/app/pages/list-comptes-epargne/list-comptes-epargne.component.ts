import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Comptebancaire } from '../../models/comptebancaire';
import { ApiService } from 'src/app/service/api.service';
import { Client } from 'src/app/models/client';

@Component({
  templateUrl: './list-comptes-epargne.component.html',
  providers: [MessageService]

})
export class ListComptesEpargneComponent implements OnInit {

  compteDialog: boolean = false;
  deleteCompteDialog: boolean = false;
  deleteComptesDialog: boolean = false;
  comptes: Comptebancaire[] = [];
  compte: Comptebancaire = {};
  selectedComptes: Comptebancaire[] = [];
  submitted: boolean = false;
  cols: any[] = [];
  statuses: any[] = [];
  rowsPerPageOptions = [5, 10, 20];
  currentIdClient?: string;
  client?: Client;
  selectedClientAdvanced: Client[] = [];
  filteredClient: Client[] = [];
  clients: Client[] = [];
  constructor(private messageService: MessageService, private apiService: ApiService
    , private router: Router) { }

  ngOnInit(): void {
    this.getAllComptes();
    this.cols = [
      { field: 'idCb', header: 'Id Compte_Bancaire' },
      { field: 'cin_client', header: 'CIN Client' },
      { field: 'idcompte', header: 'Id compte' },
      { field: 'nomCompte', header: 'Nom Compte' },
      { field: 'rib', header: 'Rib' },
      { field: 'solde', header: 'Solde' },
      { field: 'dateOuverture', header: 'Date ouverture' },
    ];
    this.apiService.getAllClients().subscribe(res => {
      this.clients = res;
    }, err => {
      console.log("error while fetching data.")
    });

    this.getFullnameClient();
  }

  filterClient(event: any) {
    const filtered: any[] = [];
    const query = event.query;
    for (let i = 0; i < this.clients.length; i++) {
      const client = this.clients[i];
      if ((client.firstname?.toLowerCase().indexOf(query.toLowerCase()) == 0) || (client.lastname?.toLowerCase().indexOf(query.toLowerCase()) == 0)) {
        filtered.push(client);

        //console.log(filtered);
      }
    }

    this.filteredClient = filtered;
    let idClient = this.filteredClient[0].idClient;
    this.currentIdClient = idClient;
    console.log(idClient)
  }
  openNew() {

    this.submitted = false;

    this.compte = {};
    this.submitted = false;
    this.compteDialog = true;
  }

  getAllComptes() {
    this.apiService.getAllComptesEpargne().subscribe(res => {
      this.comptes = res.reverse();
      console.log(this.comptes);

    }, err => {
      console.log("error while fetching data.")
    });
  }
   async asyncGetAllComptes() {
    this.apiService.getAllComptesEpargne().subscribe(res => {
      this.comptes = res.reverse();
      console.log(this.comptes);

    }, err => {
      console.log("error while fetching data.")
    });
  }
  getFullnameClient() {

    this.comptes.forEach((compte) => {
      console.log(this.comptes);
    });

  }

  deleteSelectedComptes() {
    this.deleteComptesDialog = true;
  }

  editCompte(compte: Comptebancaire) {
    this.compte = { ...compte };
    this.compteDialog = true;
  }
  deleteCompte(compte: Comptebancaire) {
    this.deleteCompteDialog = true;
    this.apiService.deleteCompteEpargne(compte).subscribe(res => {
      console.log(res);

      this.getAllComptes();
    }, err => {
      console.log(err);
    });
    this.compte = { ...compte };
  }

   confirmDeleteSelected() {
    this.deleteComptesDialog = false;
    this.comptes = this.comptes.filter(val => !this.selectedComptes.includes(val));
    for (let compte of this.selectedComptes) {
      this.apiService.deleteCompteEpargne(compte).subscribe(res => {
        console.log(res);
        this.selectedComptes.pop();
      }, err => {
        console.log(err);
      });
    }
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Comptes Deleted', life: 3000 });
    this.selectedComptes = [];
   
    
  }

  confirmDelete() {
    this.deleteCompteDialog = false;
    this.comptes = this.comptes.filter(val => val.idCb !== this.compte.idCb);
    this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Compte Deleted', life: 3000 });
    this.compte = {};
  }

  hideDialog() {
    this.compteDialog = false;
    this.submitted = false;
  }
  getSelectedValue(event: any) {
    console.log(event);
    this.compte.idClient = event.idClient;
    console.log(this.compte.idClient);
  }

  saveCompte() {
    this.submitted = true;
    if (this.compte.idClient != null) {
      if (this.compte.idCb) {
        // @ts-ignore
        this.compte.solde = this.compte.solde;
        this.comptes[this.findIndexById(this.compte.idCb)] = this.compte;
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'compte Updated', life: 3000 });
        this.apiService.updateCompteEpargne(this.compte).subscribe(res => {
          console.log(res);
        }, error => {
          alert(error.message);
        });
      } else {
        this.compte.solde = this.compte.solde;
        // this.compte.date_inscription= new Date();
        // @ts-ignore
        this.compte.iban = this.compte.iban;
        this.comptes.push(this.compte);
        this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'compte Created', life: 3000 });
        this.apiService.addCompteEpargne(this.compte).subscribe(res => {
          console.log(res);
          this.getAllComptes();
        }, error => {
          alert(error.message);
        });
      }
      this.comptes = [...this.comptes];
      this.compteDialog = false;
      this.compte = {};
    }
  }


  findIndexById(id: string): number {
    let index = -1;
    for (let i = 0; i < this.comptes.length; i++) {
      if (this.comptes[i].idCb === id) {
        index = i;
        break;
      }
    }

    return index;
  }

  onGlobalFilter(table: Table, event: Event) {
    table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }

}