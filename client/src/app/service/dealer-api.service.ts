import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService } from "./api.service";

import { DealerResponseDto} from "../model/dealer/dealer-response-dto";
import { DealerRequestDto} from "../model/dealer/dealer-request-dto";
import { appConst } from "../app.const";
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class DealerApiService {

  private _apiUrl = environment.apiUrl + appConst.dealersPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<DealerRequestDto, DealerResponseDto>) { }

  getDealers(httpParams: HttpParams): Observable<DealerResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getDealer(id: number): Observable<DealerResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(dealer: DealerRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, dealer);
  }

  update(id: number, dealer: DealerRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, dealer);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
