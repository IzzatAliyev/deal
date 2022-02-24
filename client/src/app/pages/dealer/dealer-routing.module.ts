import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DealerItemsComponent} from "./components/dealer-items/dealer-items.component";
import {DealerUpdateComponent} from "./components/dealer-update/dealer-update.component";
import {DealerDetailsComponent} from "./components/dealer-details/dealer-details.component";
import {DealerNewComponent} from "./components/dealer-new/dealer-new.component";
import {CommonModule} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: DealerItemsComponent
  },
  {
    path: 'new',
    component: DealerNewComponent
  },
  {
    path: 'details/:id',
    component: DealerDetailsComponent
  },
  {
    path: 'update/:id',
    component: DealerUpdateComponent
  }
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DealerRoutingModule { }
