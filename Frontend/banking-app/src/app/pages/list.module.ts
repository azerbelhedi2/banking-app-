import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListRoutingModule } from './list-routing.module';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { ListComptesEpargneComponent } from './list-comptes-epargne/list-comptes-epargne.component';


@NgModule({
    declarations: [
    ProfileComponent,
    
  ],
    imports: [
        CommonModule,
        ListRoutingModule
    ]
})
export class ListModule { }
