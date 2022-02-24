import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ManagementUpdateComponent} from "./components/management-update/management-update.component";
import {ManagementItemsComponent} from "./components/management-items/management-items.component";
import {ManagementDetailsComponent} from "./components/management-details/management-details.component";
import {ManagementNewComponent} from "./components/management-new/management-new.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: ManagementItemsComponent
  },
  {
    path: 'new',
    component: ManagementNewComponent
  },
  {
    path: 'details/:id',
    component: ManagementDetailsComponent
  },
  {
    path: 'update/:id',
    component: ManagementUpdateComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagementRoutingModule { }
