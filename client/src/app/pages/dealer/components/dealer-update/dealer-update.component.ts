import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {DealerResponseDto} from "../../../../model/dealer/dealer-response-dto";
import {TypeApiService} from "../../../../service/type-api.service";
import {ActivatedRoute, Router} from "@angular/router";
import {DealerRequestDto} from "../../../../model/dealer/dealer-request-dto";
import {Observable} from "rxjs";
import {DealerApiService} from "../../../../service/dealer-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-dealer-update',
  templateUrl: './dealer-update.component.html',
  styleUrls: ['./dealer-update.component.scss']
})
export class DealerUpdateComponent implements OnInit {

  id: number = 0;
  dealer?: DealerRequestDto;
  dealerTypes?: string[];
  @Input() dealerResponseDto?: Observable<DealerResponseDto>;

  dealerForm = new FormGroup({
    name: new FormControl(''),
    inn: new FormControl(''),
    dealerType: new FormControl('')
  });

  constructor(private _dealerApiService: DealerApiService,
              private _typeApiService: TypeApiService,
              private _router: Router,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getDealerTypes();
    this.getDealer();
  }

  update(): void {
    let dealer = this.dealerForm.value as DealerRequestDto;

    this._dealerApiService.update(this.id, dealer).subscribe(() => {
      this._router.navigateByUrl('dealers');
    });
  }

  getDealer(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.dealerResponseDto = this._dealerApiService.getDealer(this.id);
    this.dealerResponseDto.subscribe(dealerResponseDto =>
      this.dealerForm.setValue({
        name: dealerResponseDto.name,
        inn: dealerResponseDto.inn,
        dealerType: dealerResponseDto.dealerType
      }))
  }

  getDealerTypes(): void {
    this._typeApiService.getTypes('dealerTypes')
      .subscribe(dealerTypes => this.dealerTypes = dealerTypes);
  }

  goBack(): void {
    this.location.back();
  }
}
