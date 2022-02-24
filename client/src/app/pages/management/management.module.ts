import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagementRoutingModule } from './management-routing.module';
import { ManagementDetailsComponent } from './components/management-details/management-details.component';
import { ManagementNewComponent } from './components/management-new/management-new.component';
import { ManagementItemsComponent } from './components/management-items/management-items.component';
import { ManagementUpdateComponent } from './components/management-update/management-update.component';
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    ManagementDetailsComponent,
    ManagementNewComponent,
    ManagementItemsComponent,
    ManagementUpdateComponent
  ],
    imports: [
        CommonModule,
        ManagementRoutingModule,
        ReactiveFormsModule
    ]
})
export class ManagementModule { }
