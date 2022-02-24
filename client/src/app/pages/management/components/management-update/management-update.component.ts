import {Component, Input, OnInit} from '@angular/core';
import {ManagementRequestDto} from "../../../../model/management/management-request-dto";
import {ManagementApiService} from "../../../../service/management-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {HttpParams} from "@angular/common/http";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {ManagementResponseDto} from "../../../../model/management/management-response-dto";
import {Location} from "@angular/common";
import {Observable} from "rxjs";

@Component({
  selector: 'app-management-update',
  templateUrl: './management-update.component.html',
  styleUrls: ['./management-update.component.scss']
})
export class ManagementUpdateComponent implements OnInit {

  id: number = 0;
  management?: ManagementRequestDto;
  departments?: DepartmentResponseDto[];
  @Input() managementResponseDto?: Observable<ManagementResponseDto>;

  managementForm = new FormGroup({
    name: new FormControl(''),
    departmentIds: new FormArray([])
  });

  constructor(private _managementApiService: ManagementApiService,
              private _departmentApiService: DepartmentApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getManagement();
    this.getDepartments();
  }

  getFormsControls() : FormArray{
    return this.managementForm.controls['departmentIds'] as FormArray;
  }

  addDepartment(value?:number){
    (<FormArray>this.managementForm.controls["departmentIds"]).push(new FormControl(value));
  }
  removeDepartment(index: number){
    (<FormArray>this.managementForm.controls["departmentIds"]).removeAt(index);
  }

  getDepartments(): void {
    this._departmentApiService.getDepartments(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(departments => this.departments = departments);
  }

  update(): void {
    let management = this.managementForm.value as ManagementRequestDto;

    this._managementApiService.update(this.id, management).subscribe(() => {
      this._router.navigateByUrl('managements');
    });
  }

  getManagement(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.managementResponseDto = this._managementApiService.getManagement(this.id);

    this.managementResponseDto.subscribe(managementResponseDto =>
      this.managementForm.setValue({name: managementResponseDto.name,
        departmentIds: []}))
    this.managementResponseDto.subscribe(managementResponseDto => managementResponseDto.departments.forEach(value => {this.addDepartment(value.id)}))
  }

  goBack(): void {
    this.location.back();
  }
}
