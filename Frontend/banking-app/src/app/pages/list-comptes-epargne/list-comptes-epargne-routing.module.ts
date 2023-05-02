import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComptesEpargneComponent } from './list-comptes-epargne.component';

const routes: Routes = [];

@NgModule({
  imports:  [RouterModule.forChild([
		{ path: '', component: ListComptesEpargneComponent}
	])],
  exports: [RouterModule]
})
export class ListComptesEpargneRoutingModule { }
