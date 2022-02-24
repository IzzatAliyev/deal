import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DepartmentItemsComponent} from "./components/department-items/department-items.component";
import {DepartmentNewComponent} from "./components/department-new/department-new.component";
import {DepartmentUpdateComponent} from "./components/department-update/department-update.component";
import {DepartmentDetailsComponent} from "./components/department-details/department-details.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: DepartmentItemsComponent
  },
  {
    path: 'new',
    component: DepartmentNewComponent
  },
  {
    path: 'details/:id',
    component: DepartmentDetailsComponent
  },
  {
    path: 'update/:id',
    component: DepartmentUpdateComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DepartmentRoutingModule { }
