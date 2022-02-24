import {Component, Input, OnInit} from '@angular/core';
import {DealerResponseDto} from "../../../../model/dealer/dealer-response-dto";
import {ActivatedRoute, Router} from "@angular/router";
import {DealerApiService} from "../../../../service/dealer-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-dealer-details',
  templateUrl: './dealer-details.component.html',
  styleUrls: ['./dealer-details.component.scss']
})
export class DealerDetailsComponent implements OnInit {

  id?:number;
  @Input() dealer?: DealerResponseDto;

  constructor(private route: ActivatedRoute,
              private _dealerApiService: DealerApiService,
              private location: Location,
              private _router: Router) { }

  ngOnInit(): void {
    this.getDealer();
  }

  getDealer(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this._dealerApiService.getDealer(this.id)
      .subscribe(dealer => this.dealer = dealer);
  }

  goBack(): void {
    this.location.back();
  }

  goToContracts(): void {
    this._router.navigate(['/contracts'],{
      queryParams: { dealer: this.id}
    });
  }

  updateDealer(): void {
    this._router.navigateByUrl('dealers/update/' + this.id);
  }
}
