import { Component, OnInit } from '@angular/core';
import {ContractResponseDto} from "../../../../model/contract/contract-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import { ContractApiService } from 'src/app/service/contract-api.service';
import {TableHeader} from "../../../../model/table-header";
import {HttpParams} from "@angular/common/http";
import {appConstRole} from "../../../../app.const.role";

@Component({
  selector: 'app-contract-items',
  templateUrl: './contract-items.component.html',
  styleUrls: ['./contract-items.component.scss']
})
export class ContractItemsComponent implements OnInit {

  appConstRole = appConstRole;
  departmentId: string = "";
  dealerId: string = "";
  currentPage: number = 1;
  countOfItems: number = 0;
  totalPageSize: number = 0;
  sizeOfPage: number = 10;
  pageSizeItems: number[] = [10, 25, 50, 100];
  sort: string = "id";
  order: string = "asc";
  contracts: ContractResponseDto[] = [];
  headers?: TableHeader[] = [{headerName: 'id', isActive: true, isSortable: true, sort: 'id', order: 'asc'},
    {headerName: 'name', isActive: false, isSortable: true, sort: 'name', order: 'asc'},
    {headerName: 'contract type', isActive: false, isSortable: true, sort: 'contractType', order: 'asc'},
    {headerName: 'department', isActive: false, isSortable: false, sort: 'department', order: 'asc'},
    {headerName: 'dealer', isActive: false, isSortable: false, sort: 'dealer', order: 'asc'},
    {headerName: 'details', isActive: false, isSortable: false, sort: '', order: ''},
    {headerName: 'delete', isActive: false, isSortable: false, sort: '', order: ''}];

  constructor(private _contractApiService: ContractApiService,
              private route: ActivatedRoute,
              private _router: Router) {
  }

  ngOnInit(): void {
    this.getContracts();
  }

  getContracts(): void {
    this._contractApiService.count(this.initHttpParams())
      .subscribe(countOfItems => {
        this.countOfItems = countOfItems;
        if (this.countOfItems % this.sizeOfPage == 0) {
          this.totalPageSize = this.countOfItems / this.sizeOfPage;
        } else {
          this.totalPageSize = Math.floor(this.countOfItems / this.sizeOfPage) + 1;
        }
      });

    this._contractApiService.getContracts(this.initHttpParams())
      .subscribe(contracts => this.contracts = contracts);
  }

  deleteById(id: number): void {
    this._contractApiService.deleteById(id).subscribe(() => {
      this.getContracts();
    });
  }

  changeSizeOfPage(sizeOfPage: number): void {
    this.sizeOfPage = sizeOfPage;
    this.getContracts();
  }

  setCurrentPage(currentPage: number): void {
    this.currentPage = currentPage;
    this.getContracts();
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

    this.getContracts();
  }

  createContract(): void {
    this._router.navigate(['contracts/new'], {
      queryParams: {departmentId: this.departmentId, dealerId: this.dealerId}
    });
  }

  initHttpParams(): any {
    this.route.queryParams.subscribe(params => {
      if (params['department'] != undefined) {
        this.departmentId = params['department'];
      }
      if (params['dealer'] != undefined) {
        this.dealerId = params['dealer'];
      }
    })
    return new HttpParams()
      .set('sort', this.sort)
      .set('order', this.order)
      .set('department', this.departmentId)
      .set('dealer', this.dealerId)
      .set('currentPage', this.currentPage - 1)
      .set('sizeOfPage', this.sizeOfPage)
      ;
  }
}
