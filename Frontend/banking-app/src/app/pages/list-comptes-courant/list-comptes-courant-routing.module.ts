import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComptesCourantComponent } from './list-comptes-courant.component';

const routes: Routes = [];

@NgModule({
  imports:  [RouterModule.forChild([
		{ path: '', component: ListComptesCourantComponent}
	])],
  exports: [RouterModule]
})
export class ListComptesCourantRoutingModule { }
