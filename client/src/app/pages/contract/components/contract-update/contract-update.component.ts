import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ContractResponseDto} from "../../../../model/contract/contract-response-dto";
import {Observable} from "rxjs";
import {ContractRequestDto} from "../../../../model/contract/contract-request-dto";
import {HttpParams} from "@angular/common/http";
import {ContractApiService} from "../../../../service/contract-api.service";
import {DealerResponseDto} from "../../../../model/dealer/dealer-response-dto";
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {DealerApiService} from "../../../../service/dealer-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-contract-update',
  templateUrl: './contract-update.component.html',
  styleUrls: ['./contract-update.component.scss']
})
export class ContractUpdateComponent implements OnInit {

  id: number = 0;
  contract?: ContractRequestDto;
  @Input() contractResponseDto?: Observable<ContractResponseDto>;
  contractTypes?: string[];
  departments?: DepartmentResponseDto[];
  dealers?: DealerResponseDto[];

  contractForm = new FormGroup({
    name: new FormControl(''),
    contractType: new FormControl(''),
    departmentId: new FormControl(''),
    dealerId: new FormControl('')
  });

  constructor(private _contractApiService: ContractApiService,
              private _typeApiService: TypeApiService,
              private _departmentApiService: DepartmentApiService,
              private _dealerApiService: DealerApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getContractTypes();
    this.getContract();
    this.getDepartments();
    this.getDealers();
  }

  update(): void {
    let contract = this.contractForm.value as ContractRequestDto;
    this._contractApiService.update(this.id, contract).subscribe(() => {
      this._router.navigateByUrl('contracts');
    });
  }

  getContract(): void {
    this.id = Number(this._route.snapshot.paramMap.get('id'));
    this.contractResponseDto = this._contractApiService.getContract(this.id);
    this.contractResponseDto.subscribe(contractResponseDto =>
      this.contractForm.setValue({
        name: contractResponseDto.name,
        contractType: contractResponseDto.contractType,
        departmentId: contractResponseDto.department.id,
        dealerId: contractResponseDto.dealer.id
      }))
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
}
