import {Component, Input, OnInit} from '@angular/core';
import {ManagementResponseDto} from "../../../../model/management/management-response-dto";
import {ManagementApiService} from "../../../../service/management-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-management-details',
  templateUrl: './management-details.component.html',
  styleUrls: ['./management-details.component.scss']
})
export class ManagementDetailsComponent implements OnInit {

  id?:number;

  @Input() management?: ManagementResponseDto;

  constructor(private route: ActivatedRoute,
              private _managementApiService: ManagementApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getManagement();
  }

  getManagement(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._managementApiService.getManagement(this.id)
      .subscribe(management => this.management = management);
  }

  goBack(): void {
    this.location.back();
  }

  updateManagement(): void {
    this._router.navigateByUrl('managements/update/' + this.id);
  }
}
