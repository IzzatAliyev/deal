import {Component, Input, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ContractApiService} from "../../../../service/contract-api.service";
import {ContractResponseDto} from "../../../../model/contract/contract-response-dto";
import {Location} from "@angular/common";

@Component({
  selector: 'app-contract-details',
  templateUrl: './contract-details.component.html',
  styleUrls: ['./contract-details.component.scss']
})
export class ContractDetailsComponent implements OnInit {

  id?:number;

  @Input() contract?: ContractResponseDto;

  constructor(private route: ActivatedRoute,
              private _contractApiService: ContractApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getContract();
  }

  getContract(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._contractApiService.getContract(this.id)
      .subscribe(contract => this.contract = contract);
  }

  goBack(): void {
    this.location.back();
  }

  updateContract(): void {
    this._router.navigateByUrl('contracts/update/' + this.id);
  }
}
