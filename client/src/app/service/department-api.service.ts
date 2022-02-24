import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ApiService } from "./api.service";

import {DepartmentResponseDto} from "../model/department/department-response-dto";
import {DepartmentRequestDto} from "../model/department/department-request-dto";
import { appConst } from "../app.const";
import { environment } from "../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class DepartmentApiService {

  private _apiUrl = environment.apiUrl + appConst.departmentsPath;

  constructor(private _http: HttpClient,
              private _apiService: ApiService<DepartmentRequestDto, DepartmentResponseDto>) {
  }

  getDepartments(httpParams: HttpParams): Observable<DepartmentResponseDto[]> {
    return this._apiService.getAll(this._apiUrl, httpParams);
  }

  getDepartment(id: number): Observable<DepartmentResponseDto> {
    return this._apiService.getById(this._apiUrl,id);
  }

  create(department: DepartmentRequestDto): Observable<boolean> {
    return this._apiService.create(this._apiUrl, department);
  }

  update(id: number, department: DepartmentRequestDto): Observable<boolean> {
    return this._apiService.update(this._apiUrl, id, department);
  }

  count(httpParams: HttpParams): Observable<number> {
    return this._apiService.count(this._apiUrl + '/count', httpParams);
  }

  deleteById(id: number): Observable<boolean> {
    return this._apiService.deleteById(this._apiUrl, id);
  }
}
