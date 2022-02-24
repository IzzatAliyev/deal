import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ContractRoutingModule } from './contract-routing.module';
import { ContractItemsComponent } from './components/contract-items/contract-items.component';
import { ContractDetailsComponent } from './components/contract-details/contract-details.component';
import { ContractNewComponent } from './components/contract-new/contract-new.component';
import { ContractUpdateComponent } from './components/contract-update/contract-update.component';
import {NgxPermissionsModule} from "ngx-permissions";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ContractItemsComponent,
    ContractDetailsComponent,
    ContractNewComponent,
    ContractUpdateComponent
  ],
  imports: [
    CommonModule,
    ContractRoutingModule,
    NgxPermissionsModule,
    ReactiveFormsModule
  ]
})
export class ContractModule { }
