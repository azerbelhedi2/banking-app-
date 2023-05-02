import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common'
import { KeycloakService } from 'keycloak-angular';

import { Observable } from 'rxjs';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  user !:Observable<any>;
  userDetails : any ; 
  constructor(@Inject(DOCUMENT) private document: Document , private keycloakService :KeycloakService) { }

 async  ngOnInit(): Promise<void> {
    console.log(this.keycloakService.getUsername());
    this.userDetails = await this.keycloakService.loadUserProfile();
    console.log(this.userDetails);
    
    
  }
  
  async loadUserProfile()
  {
    this.userDetails = await this.keycloakService.loadUserProfile();
  }
  logout() {
    this.keycloakService.logout().then(() => this.keycloakService.clearToken());;
  }
  sidebarToggle()
  {
    //toggle sidebar function
    this.document.body.classList.toggle('toggle-sidebar');
  }
}
