import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { AuthGuard } from '../service/auth.guard';
import { HomeComponent } from './home/home.component';
import { ListComptesCourantComponent } from './list-comptes-courant/list-comptes-courant.component';
import { ProfileComponent } from './profile/profile.component';


@NgModule({
    imports: [RouterModule.forChild([
     
        { path: 'list-clients', loadChildren: () => import('./list-clients/list-clients.module').then(m => m.ListClientsModule) ,canActivate: [AuthGuard], data: { roles: ['ADMIN'] },},
        { path: 'list-comptes-courant', loadChildren: () => import('./list-comptes-courant/list-comptes-courant.module').then(m => m.ListComptesCourantModule) ,canActivate: [AuthGuard], data: { roles: ['ADMIN'] },},
        { path: 'list-comptes-epargne', loadChildren: () => import('./list-comptes-epargne/list-comptes-epargne.module').then(m => m.ListComptesEpargneModule) ,canActivate: [AuthGuard], data: { roles: ['ADMIN'] },},
        // { path: 'list-comptes-courant', component:ListComptesCourantComponent},
        { path: 'profile', component:ProfileComponent},
        { path: 'home', loadChildren: () => import('./home/home.module' ).then(m => m.HomeModule) ,canActivate: [AuthGuard], data: { roles: ['ADMIN'] },},
        { path: '**', redirectTo: '/notfound'  }
    ])],
    exports: [RouterModule]
})
export class ListRoutingModule { }
