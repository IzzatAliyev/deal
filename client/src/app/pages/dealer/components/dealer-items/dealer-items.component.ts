import { Component, OnInit } from '@angular/core';
import { appConstRole } from 'src/app/app.const.role';
import {HttpParams} from "@angular/common/http";
import {Router} from "@angular/router";
import {DealerResponseDto} from "../../../../model/dealer/dealer-response-dto";
import {TableHeader} from "../../../../model/table-header";
import {DealerApiService} from "../../../../service/dealer-api.service";

@Component({
  selector: 'app-dealer-items',
  templateUrl: './dealer-items.component.html',
  styleUrls: ['./dealer-items.component.scss']
})
export class DealerItemsComponent implements OnInit {

  appConstRole = appConstRole;

  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  dealers: DealerResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'dealer type', isActive: false, isSortable: true, sort: 'dealerType', order: 'asc'},
    {headerName: 'inn', isActive: false, isSortable: true, sort: 'inn', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _dealerApiService: DealerApiService,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getDealers();
  }

  getDealers(): void {
    this._dealerApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._dealerApiService.getDealers(this.initHttpParams())
      .subscribe(dealers => this.dealers = dealers);
  }

  deleteById(id: number): void {
    this._dealerApiService.deleteById(id).subscribe(() => {
      this.getDealers();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getDealers();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getDealers();
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

    this.getDealers();
  }

  createDealer(): void {
    this._router.navigateByUrl('dealers/new');
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
