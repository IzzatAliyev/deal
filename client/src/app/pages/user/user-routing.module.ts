import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UserItemsComponent} from "./components/user-items/user-items.component";
import {NgxPermissionsGuard} from "ngx-permissions";
import {UserDetailsComponent} from "./components/user-details/user-details.component";
import {CommonModule} from "@angular/common";
import {UserUpdateComponent} from "./components/user-update/user-update.component";

const routes: Routes = [
  {
    path: '',
    component: UserItemsComponent,
    canActivate: [NgxPermissionsGuard],
    data: {
      permissions: {
        only: 'ROLE_ADMIN',
        redirectTo: 'dashboard'
      }
    }
  },
  {
    path: 'details/:email',
    component: UserDetailsComponent
  },
  {
    path: 'update/:email',
    component: UserUpdateComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
