import { Component, OnInit } from '@angular/core';
import {HttpParams} from "@angular/common/http";
import {ManagementApiService} from "../../../../service/management-api.service";
import {TableHeader} from "../../../../model/table-header";
import {Router} from "@angular/router";
import {ManagementResponseDto} from "../../../../model/management/management-response-dto";

@Component({
  selector: 'app-management-items',
  templateUrl: './management-items.component.html',
  styleUrls: ['./management-items.component.scss']
})
export class ManagementItemsComponent implements OnInit {

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  managements: ManagementResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _managementApiService: ManagementApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getManagements();
  }

  getManagements(): void {
    this._managementApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._managementApiService.getManagements(this.initHttpParams())
      .subscribe(managements => this.managements = managements);
  }

  deleteById(id: number): void {
    this._managementApiService.deleteById(id).subscribe(() => {
      this.getManagements();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getManagements();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getManagements();
  }

  sortOn(sort: string, order: string) {
    // @ts-ignore
    this.headers.forEach(function (header) {
      if (header.sort == sort) {
        header.isActive = true;
        header.order = order;
      } else {
        header.isActive = false;
      }
    })

    this.sort = sort;
    this.order = order;

    this.getManagements();
  }

  createManagement(): void {
    this._router.navigateByUrl('managements/new');
  }

  initHttpParams(): any {
    return new HttpParams()
      .set('sort', this.sort)
      .set('order', this.order)
      .set('currentPage', this.currentPage - 1)
      .set('sizeOfPage', this.sizeOfPage)
      ;
  }
}
