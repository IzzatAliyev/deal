import { Component, OnInit } from '@angular/core';
import {DealerResponseDto} from "../../../../model/dealer/dealer-response-dto";
import {TypeApiService} from "../../../../service/type-api.service";
import {ContractRequestDto} from "../../../../model/contract/contract-request-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {FormControl, FormGroup} from "@angular/forms";
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {ContractApiService} from "../../../../service/contract-api.service";
import {DealerApiService} from "../../../../service/dealer-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-contract-new',
  templateUrl: './contract-new.component.html',
  styleUrls: ['./contract-new.component.scss']
})
export class ContractNewComponent implements OnInit {

  departmentId: string = "";
  dealerId: string = "";
  contract?: ContractRequestDto;
  contractTypes?: string[];
  departments?: DepartmentResponseDto[];
  dealers?: DealerResponseDto[];

  contractForm = new FormGroup({
    name: new FormControl(''),
    contractType: new FormControl(''),
    departmentId: new FormControl(this.departmentId),
    dealerId: new FormControl(this.dealerId)
  });

  constructor(private _contractApiService: ContractApiService,
              private _typeApiService: TypeApiService,
              private _departmentApiService: DepartmentApiService,
              private _dealerApiService: DealerApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) { }

  ngOnInit(): void {
    this.parseHttpParams();
    this.getContractTypes();
    this.getDepartments();
    this.getDealers();
  }

  create(): void {
    let contract = this.contractForm.value as ContractRequestDto;
    this._contractApiService.create(contract).subscribe(() => {
      this._router.navigateByUrl('contracts');
    });
  }

  getContractTypes(): void {
    this._typeApiService.getTypes('contractTypes')
      .subscribe(contractTypes => this.contractTypes = contractTypes);
  }

  getDepartments(): void {
    this._departmentApiService.getDepartments(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(departments => this.departments = departments);
  }

  getDealers(): void {
    this._dealerApiService.getDealers(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(dealers => this.dealers = dealers);
  }

  goBack(): void {
    this.location.back();
  }

  parseHttpParams(): void {
    this._route.queryParams.subscribe(params => {
      if (params['departmentId'] != undefined) {
        this.departmentId = params['departmentId'];
      }
      if (params['dealerId'] != undefined) {
        this.dealerId = params['dealerId'];
      }
      this.contractForm.setValue({
        name: '',
        contractType: '',
        departmentId: this.departmentId,
        dealerId: this.dealerId
      })
    })
  }
}
