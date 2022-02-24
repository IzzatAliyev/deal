import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DealerRoutingModule } from './dealer-routing.module';
import { DealerDetailsComponent } from './components/dealer-details/dealer-details.component';
import { DealerItemsComponent } from './components/dealer-items/dealer-items.component';
import { DealerNewComponent } from './components/dealer-new/dealer-new.component';
import { DealerUpdateComponent } from './components/dealer-update/dealer-update.component';
import {NgxPermissionsModule} from "ngx-permissions";
import {ReactiveFormsModule} from "@angular/forms";


@NgModule({
  declarations: [
    DealerDetailsComponent,
    DealerItemsComponent,
    DealerNewComponent,
    DealerUpdateComponent
  ],
  imports: [
    CommonModule,
    DealerRoutingModule,
    NgxPermissionsModule,
    ReactiveFormsModule
  ]
})
export class DealerModule { }
