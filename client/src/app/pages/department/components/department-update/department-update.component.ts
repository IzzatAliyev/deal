import {Component, Input, OnInit} from '@angular/core';
import {DepartmentApiService} from "../../../../service/department-api.service";
import {TypeApiService} from "../../../../service/type-api.service";
import {DepartmentRequestDto} from "../../../../model/department/department-request-dto";
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {FormArray, FormControl, FormGroup} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ManagementResponseDto} from "../../../../model/management/management-response-dto";
import {Observable} from "rxjs";
import {ManagementApiService} from "../../../../service/management-api.service";
import {HttpParams} from "@angular/common/http";
import {Location} from "@angular/common";

@Component({
  selector: 'app-department-update',
  templateUrl: './department-update.component.html',
  styleUrls: ['./department-update.component.scss']
})
export class DepartmentUpdateComponent implements OnInit {

  id: number = 0;
  department?: DepartmentRequestDto;
  departmentTypes?: string[];
  managements?: ManagementResponseDto[];
  @Input() departmentResponseDto?: Observable<DepartmentResponseDto>;

  departmentForm = new FormGroup({
    name: new FormControl(''),
    departmentType: new FormControl(''),
    managementIds: new FormArray([])
  });

  constructor(private _departmentApiService: DepartmentApiService,
              private _managementApiService: ManagementApiService,
              private _typeApiService: TypeApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getDepartmentTypes();
    this.getDepartment();
    this.getManagements();
  }

  getFormsControls() : FormArray{
    return this.departmentForm.controls['managementIds'] as FormArray;
  }

  addManagement(value?:number){
    (<FormArray>this.departmentForm.controls["managementIds"]).push(new FormControl(value));
  }
  removeManagement(index: number){
    (<FormArray>this.departmentForm.controls["managementIds"]).removeAt(index);
  }

  getManagements(): void {
    this._managementApiService.getManagements(new HttpParams()
      .set('sort', 'name')
      .set('order', 'asc'))
      .subscribe(managements => this.managements = managements);
  }

  update(): void {
    let department = this.departmentForm.value as DepartmentRequestDto;

    this._departmentApiService.update(this.id, department).subscribe(() => {
      this._router.navigateByUrl('departments');
    });
  }

  getDepartment(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.departmentResponseDto = this._departmentApiService.getDepartment(this.id);
    this.departmentResponseDto.subscribe(departmentResponseDto =>
      this.departmentForm.setValue({name: departmentResponseDto.name, departmentType: departmentResponseDto.departmentType, managementIds: []}))
    this.departmentResponseDto.subscribe(departmentResponseDto => departmentResponseDto.managements.forEach(value => {this.addManagement(value.id)}))
  }

  getDepartmentTypes(): void {
    this._typeApiService.getTypes('departmentTypes')
      .subscribe(departmentTypes => this.departmentTypes = departmentTypes);
  }

  goBack(): void {
    this.location.back();
  }
}
