import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DealerRequestDto} from "../../../../model/dealer/dealer-request-dto";
import {DealerApiService} from "../../../../service/dealer-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-dealer-new',
  templateUrl: './dealer-new.component.html',
  styleUrls: ['./dealer-new.component.scss']
})
export class DealerNewComponent implements OnInit {

  dealer?: DealerRequestDto;
  dealerTypes?: string[];

  dealerForm = new FormGroup({
    name: new FormControl(''),
    inn: new FormControl(''),
    dealerType: new FormControl('')
  });

  constructor(private _dealerApiService: DealerApiService,
              private _typeApiService: TypeApiService,
              private _route: ActivatedRoute,
              private _router: Router,
              private location: Location) {
  }

  ngOnInit(): void {
    this.getDealerTypes();
  }

  create(): void {
    let dealer = this.dealerForm.value as DealerRequestDto;

    this._dealerApiService.create(dealer).subscribe(() => {
      this._router.navigateByUrl('dealers');
    });
  }

  getDealerTypes(): void {
    this._typeApiService.getTypes('dealerTypes')
      .subscribe(dealerTypes => this.dealerTypes = dealerTypes);
  }

  goBack(): void {
    this.location.back();
  }
}
