import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UserItemsComponent } from './components/user-items/user-items.component';
import { UserUpdateComponent } from './components/user-update/user-update.component';
import {NgxPermissionsModule} from "ngx-permissions";
import {ReactiveFormsModule} from "@angular/forms";
import {MatRadioModule} from "@angular/material/radio";


@NgModule({
  declarations: [
    UserDetailsComponent,
    UserItemsComponent,
    UserUpdateComponent
  ],
  imports: [
    CommonModule,
    UserRoutingModule,
    NgxPermissionsModule,
    ReactiveFormsModule,
    MatRadioModule,
  ]
})
export class UserModule { }
