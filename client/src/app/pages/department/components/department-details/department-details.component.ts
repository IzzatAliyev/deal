import {Component, Input, OnInit} from '@angular/core';
import {DepartmentResponseDto} from "../../../../model/department/department-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {DepartmentApiService} from "../../../../service/department-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-department-details',
  templateUrl: './department-details.component.html',
  styleUrls: ['./department-details.component.scss']
})
export class DepartmentDetailsComponent implements OnInit {

  id?:number;

  @Input() department?: DepartmentResponseDto;

  constructor(private route: ActivatedRoute,
              private _departmentApiService: DepartmentApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getDepartment();
  }

  getDepartment(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._departmentApiService.getDepartment(this.id)
      .subscribe(department => this.department = department);
  }

  goBack(): void {
    this.location.back();
  }

  goToContracts(): void {
    this._router.navigate(['/contracts'],{
      queryParams: { department: this.id}
    });
  }

  updateDepartment(): void {
    this._router.navigateByUrl('departments/update/' + this.id);
  }
}
