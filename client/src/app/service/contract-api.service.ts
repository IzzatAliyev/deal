import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService } from "./api.service";

import {ContractResponseDto} from "../model/contract/contract-response-dto";
import {ContractRequestDto} from "../model/contract/contract-request-dto";
import { appConst } from "../app.const";
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class ContractApiService {

  private _apiUrl = environment.apiUrl + appConst.contractsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<ContractRequestDto, ContractResponseDto>) {
  }

  getContracts(httpParams: HttpParams): Observable<ContractResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getContract(id: number): Observable<ContractResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(contract: ContractRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, contract);
  }

  update(id: number, contract: ContractRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, contract);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
