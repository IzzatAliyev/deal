import { Component, OnInit } from '@angular/core';
import {ManagementApiService} from "../../../../service/management-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {ManagementRequestDto} from "../../../../model/management/management-request-dto";
import {Location} from "@angular/common";

@Component({
  selector: 'app-management-new',
  templateUrl: './management-new.component.html',
  styleUrls: ['./management-new.component.scss']
})
export class ManagementNewComponent implements OnInit {

  management?: ManagementRequestDto;
  departments?: DepartmentResponseDto[];

  managementForm = new FormGroup({
    name: new FormControl(''),
    departmentIds: new FormArray([
      new FormControl('')])
  });


  constructor(private _managementApiService: ManagementApiService,
              private _departmentApiService: DepartmentApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getDepartments();
  }

  getFormsControls(): FormArray {
    return this.managementForm.controls['departmentIds'] as FormArray;
  }

  addDepartment() {
    (<FormArray>this.managementForm.controls["departmentIds"]).push(new FormControl(''));
  }

  removeDepartment(index: number) {
    (<FormArray>this.managementForm.controls["departmentIds"]).removeAt(index);
  }

  getDepartments(): void {
    this._departmentApiService.getDepartments(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(departments => this.departments = departments);
  }

  create(): void {
    let management = this.managementForm.value as ManagementRequestDto;

    this._managementApiService.create(management).subscribe(() => {
      this._router.navigateByUrl('managements');
    });
  }

  goBack(): void {
    this.location.back();
  }
}
