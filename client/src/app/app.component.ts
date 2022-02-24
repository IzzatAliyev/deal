import { Component } from '@angular/core';
import {TokenStorageService} from "./service/token-storage.service";
import {appConstRole} from "./app.const.role";
import {Router} from "@angular/router";
import {NgxPermissionsService} from "ngx-permissions";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'client';
  isLoggedIn = false;
  isRegistration = false;
  roles: string[] = [];

  public appConstRole = appConstRole;
  constructor(private tokenStorageService: TokenStorageService,
              private permissionsService: NgxPermissionsService,
              private _router: Router) {
  }

  ngOnInit() {
    this.roles = this.tokenStorageService.getUser().roles;
    this.permissionsService.loadPermissions(this.roles);
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (!this.isLoggedIn && window.location.pathname != '/authentication/signup') {
      this._router.navigateByUrl('authentication/login');
    }
  }

  successfulLogin(isLoggedIn:boolean){
    this.isLoggedIn = isLoggedIn;
    this.isRegistration = false;
    this.roles = this.tokenStorageService.getUser().roles;
    this.permissionsService.loadPermissions(this.roles);
  }

  isSignUp(isRegistration:boolean){
    this.isLoggedIn = false;
    this.isRegistration = isRegistration;
  }

  logout() {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

  userDetails() {
    this._router.navigateByUrl('users/details/' + this.tokenStorageService.getUser().username);
  }
}
