import {Component, Input, OnInit} from '@angular/core';
import {UserRequestDto} from "../../../../model/user/user-request-dto";
import {TypeApiService} from "../../../../service/type-api.service";
import {formatDate} from "@angular/common";
import {TokenStorageService} from "../../../../service/token-storage.service";
import {UserResponseDto} from "../../../../model/user/user-response-dto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {appConstRole} from "../../../../app.const.role";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";
import {UserApiService} from "../../../../service/user-api.service";
import {Location} from "@angular/common";

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.scss']
})
export class UserUpdateComponent implements OnInit {

  email:string = "";
  user?: UserRequestDto;
  @Input() userResponseDto?: Observable<UserResponseDto>;
  roleTypes?: string[];

  userForm = new FormGroup({
    firstName: new FormControl('', Validators.required),
    lastName: new FormControl('', Validators.required),
    birthDay: new FormControl('', Validators.required),
    roleType: new FormControl('', Validators.required),
    password: new FormControl(''),
    enabled: new FormControl('', Validators.required)
  });

  constructor(private _userApiService: UserApiService,
              private _typeApiService: TypeApiService,
              private tokenStorageService: TokenStorageService,
              private _router: Router,
              private _location: Location,
              private _route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getRoleTypes();
    this.getUser();
  }

  getRoleTypes(): void {
    this._typeApiService.getTypes('roleTypes')
      .subscribe(roleTypes => this.roleTypes = roleTypes);
  }

  update(): void {
    let user = this.userForm.value as UserRequestDto;
    this._userApiService.update(this.email, user).subscribe(() => {
      this._location.back();
    });
  }

  getUser(): void {
    if (this.tokenStorageService.getUser().roles.length && this.tokenStorageService.getUser().roles[0] == appConstRole.admin) {
      this.email = String(this._route.snapshot.paramMap.get('email'));
    } else {
      this.email = this.tokenStorageService.getUser().username;
    }
    this.userResponseDto = this._userApiService.getUser(this.email);
    this.userResponseDto.subscribe(userResponseDto =>
      this.userForm.setValue({
        firstName: userResponseDto.firstName,
        lastName: userResponseDto.lastName,
        birthDay: formatDate(userResponseDto.birthDay, 'yyyy-MM-dd','en-US'),
        roleType: userResponseDto.roleType,
        password: "",
        enabled: userResponseDto.enabled,
      }))
  }

  goBack(): void {
    this._location.back();
  }
}
