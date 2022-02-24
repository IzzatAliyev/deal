import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DepartmentRoutingModule } from './department-routing.module';
import { DepartmentItemsComponent } from './components/department-items/department-items.component';
import { DepartmentDetailsComponent } from './components/department-details/department-details.component';
import { DepartmentNewComponent } from './components/department-new/department-new.component';
import { DepartmentUpdateComponent } from './components/department-update/department-update.component';
import {NgxPermissionsModule} from "ngx-permissions";
import {ReactiveFormsModule} from "@angular/forms";
import {TableModule} from "../table/table.module";


@NgModule({
  declarations: [
    DepartmentItemsComponent,
    DepartmentDetailsComponent,
    DepartmentNewComponent,
    DepartmentUpdateComponent
  ],
  imports: [
    CommonModule,
    DepartmentRoutingModule,
    NgxPermissionsModule,
    TableModule,
    ReactiveFormsModule
  ]
})
export class DepartmentModule { }
