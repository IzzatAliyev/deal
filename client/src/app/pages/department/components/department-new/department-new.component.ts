import { Component, OnInit } from '@angular/core';
import {Location} from "@angular/common";
import {ManagementApiService} from "../../../../service/management-api.service";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {DepartmentRequestDto} from "../../../../model/department/department-request-dto";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {ManagementResponseDto} from "../../../../model/management/management-response-dto";

@Component({
  selector: 'app-department-new',
  templateUrl: './department-new.component.html',
  styleUrls: ['./department-new.component.scss']
})
export class DepartmentNewComponent implements OnInit {

  department?: DepartmentRequestDto;
  departmentTypes?: string[];
  managements?: ManagementResponseDto[];

  departmentForm = new FormGroup({
    name: new FormControl('', Validators.required),
    departmentType: new FormControl(''),
    managementIds: new FormArray([
      new FormControl('')])
  });

  constructor(private _departmentApiService: DepartmentApiService,
              private _managementApiService: ManagementApiService,
              private _typeApiService: TypeApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getDepartmentTypes();
    this.getManagements();
  }

  getFormsControls(): FormArray {
    return this.departmentForm.controls['managementIds'] as FormArray;
  }

  addManagement() {
    (<FormArray>this.departmentForm.controls["managementIds"]).push(new FormControl(''));
  }

  removeManagement(index: number) {
    (<FormArray>this.departmentForm.controls["managementIds"]).removeAt(index);
  }

  getManagements(): void {
    this._managementApiService.getManagements(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(managements => this.managements = managements);
  }

  create(): void {
    let department = this.departmentForm.value as DepartmentRequestDto;

    this._departmentApiService.create(department).subscribe(() => {
      this._router.navigateByUrl('departments');
    });
  }

  getDepartmentTypes(): void {
    this._typeApiService.getTypes('departmentTypes')
      .subscribe(departmentTypes => this.departmentTypes = departmentTypes);
  }

  goBack(): void {
    this.location.back();
  }
}
