import { Component, OnInit } from '@angular/core';
import { appConstRole } from 'src/app/app.const.role';
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {HttpParams} from "@angular/common/http";
import {TableHeader} from "../../../../model/table-header";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-department-items',
  templateUrl: './department-items.component.html',
  styleUrls: ['./department-items.component.scss']
})
export class DepartmentItemsComponent implements OnInit {

  appConstRole = appConstRole;
  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  departments: DepartmentResponseDto[] = [];
  headers: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'department type', isActive: false, isSortable: true, sort: 'departmentType', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _departmentApiService: DepartmentApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getDepartments();
  }

  getDepartments(): void {
    this._departmentApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._departmentApiService.getDepartments(this.initHttpParams())
      .subscribe(departments => this.departments = departments);
  }

  deleteById(id: number): void {
    this._departmentApiService.deleteById(id).subscribe(() => {
      this.getDepartments();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getDepartments();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getDepartments();
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

    this.getDepartments();
  }

  createDepartment(): void {
    this._router.navigateByUrl('departments/new');
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
