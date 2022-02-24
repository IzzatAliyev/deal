import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ContractItemsComponent} from "./components/contract-items/contract-items.component";
import {ContractNewComponent} from "./components/contract-new/contract-new.component";
import {ContractDetailsComponent} from "./components/contract-details/contract-details.component";
import {ContractUpdateComponent} from "./components/contract-update/contract-update.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: ContractItemsComponent
  },
  {
    path: 'new',
    component: ContractNewComponent
  },
  {
    path: 'details/:id',
    component: ContractDetailsComponent
  },
  {
    path: 'update/:id',
    component: ContractUpdateComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContractRoutingModule { }
