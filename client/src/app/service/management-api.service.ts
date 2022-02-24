import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.prod";
import {appConst} from "../app.const";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService} from "./api.service";
import {ManagementRequestDto} from "../model/management/management-request-dto";
import {ManagementResponseDto} from "../model/management/management-response-dto";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ManagementApiService {

  private _apiUrl = environment.apiUrl + appConst.managementsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<ManagementRequestDto, ManagementResponseDto>) { }

  getManagements(httpParams: HttpParams): Observable<ManagementResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getManagement(id: number): Observable<ManagementResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(management: ManagementRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, management);
  }

  update(id: number, management: ManagementRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, management);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
