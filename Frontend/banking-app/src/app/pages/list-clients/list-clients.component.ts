import { Component, OnInit } from '@angular/core';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table';
import { ProductService } from 'src/app/demo/service/product.service';
import { Client } from 'src/app/models/client';
import { ApiService } from 'src/app/service/api.service';

import { Router } from '@angular/router';
import { ThisReceiver } from '@angular/compiler';
@Component({
  templateUrl: './list-clients.component.html',
  providers: [MessageService]
})
export class ListClientsComponent implements OnInit {

   

  clientDialog : boolean = false ; 
  deleteClientDialog : boolean = false ; 
  deleteClientsDialog : boolean = false ;
  clients : Client[] =[];
  client : Client ={};
  selectedClients : Client[] =[];
  submitted : boolean = false ; 
  cols: any[] = [];
  statuses: any[] = [];
  rowsPerPageOptions = [5, 10, 20];





  constructor(private productService: ProductService, private messageService: MessageService ,private apiService : ApiService
      , private router : Router ) { }

  ngOnInit() {

     this.getAllClients();
      this.cols = [
          { field: 'firstname', header: 'Prenom' },
          { field: 'adresse', header: 'Adresse' },
          { field: 'date_naissance', header: 'Date naissance' },
          { field: 'date_inscription', header: 'Date d inscription' },
          { field: 'tel', header: 'Tel' }, 
          { field: 'cin', header: 'CIN' }
      ];

      this.statuses = [
          { label: 'INSTOCK', value: 'instock' },
          { label: 'LOWSTOCK', value: 'lowstock' },
          { label: 'OUTOFSTOCK', value: 'outofstock' }
      ];
  }

  openNew() {
     
      this.submitted = false;

      this.client={};
      this.submitted=false;
      this.clientDialog =true ; 
  }
  getAllClients() {
      this.apiService.getAllClients().subscribe(res=>{
          this.clients = res.reverse();
          console.log(this.clients);
      },err=>{
        console.log("error while fetching data.")
      });
    } 

  deleteSelectedClients() {
      this.deleteClientsDialog = true;    
  }


  editClient(client: Client) {
      this.client = { ...client };
      this.clientDialog = true;
  }
  deleteClient(client: Client) {
      this.deleteClientDialog = true;
      this.apiService.deleteClient(client).subscribe(res=>{
          console.log(res);
         
          this.getAllClients();
        },err => {
          console.log(err);
        });
      this.client = { ...client };
  }



  confirmDeleteSelected() {
      this.deleteClientsDialog = false;
      this.clients =this.clients.filter(val => !this.selectedClients.includes(val));
      for  (let client of this.selectedClients){
        this.apiService.deleteClient(client).subscribe(res=>{
            console.log(res);
            this.selectedClients.pop();              
          },err => {
            console.log(err);
          });
    }
      this.messageService.add({severity: 'success', summary: 'Successful', detail: 'Clients Deleted', life: 3000});
      this.selectedClients = [];
  }

  confirmDelete() {
      this.deleteClientDialog = false;
      this.clients = this.clients.filter(val => val.idClient !== this.client.idClient);
      this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Deleted', life: 3000 });
      this.client = {};
  }


  hideDialog() {
      this.clientDialog = false;
      this.submitted = false;
  }
  saveClient() {
      this.submitted = true;
      if (this.client.firstname?.trim()) {
          if (this.client.idClient) {
              // @ts-ignore
              this.client.adresse = this.client.adresse  ;
              this.client.date_inscription=this.client.date_inscription;
              this.client.date_naissance=this.client.date_naissance; 
              this.client.firstname=this.client.firstname; 
              this.client.lastname=this.client.lastname; 
              this.client.idClient=this.client.idClient; 
              this.client.tel=this.client.tel;
              this.clients[this.findIndexById(this.client.idClient)] = this.client;
              
              this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Updated', life: 3000 });
              console.log(this.client);
              this.apiService.updateClient(this.client).subscribe(res=>{
                console.log(res);
            },error=>{
              alert(error.message);
            });
          } else {
              this.client.adresse = this.client.adresse;
              this.client.date_inscription= new Date();
              // @ts-ignore
              this.client.date_naissance = this.client.date_naissance;
              this.clients.push(this.client);
              this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Client Created', life: 3000 });
              this.apiService.addClient(this.client).subscribe(res=>{
                  console.log(res);
                  this.getAllClients();
              },error=>{
                alert(error.message);
              });
          }
          this.clients = [...this.clients];
          this.clientDialog = false;
          this.client = {};
      }
  }

 

  findIndexById(id: string): number {
      let index = -1;
      for (let i = 0; i < this.clients.length; i++) {
          if (this.clients[i].idClient === id) {
              index = i;
              break;
          }
      }

      return index;
  }

  // createId(): string {
  //     let id = '';
  //     const chars = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
  //     for (let i = 0; i < 5; i++) {
  //         id += chars.charAt(Math.floor(Math.random() * chars.length));
  //     }
  //     return id;
  // }

  onGlobalFilter(table: Table, event: Event) {
      table.filterGlobal((event.target as HTMLInputElement).value, 'contains');
  }
}
