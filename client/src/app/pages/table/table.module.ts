import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableHeaderComponent } from './components/table-header/table-header.component';
import {ReactiveFormsModule} from "@angular/forms";



@NgModule({
  declarations: [
    TableHeaderComponent
  ],
  exports: [
    TableHeaderComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ]
})
export class TableModule { }
