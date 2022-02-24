import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ErrorComponent } from './pages/error/error.component';
import {ErrorDialogService} from "./service/error-dialog.service";
import {authInterceptorProviders} from "./interceptor/auth.interceptor";
import {DashboardModule} from "./pages/dashboard/dashboard.module";
import {ContractModule} from "./pages/contract/contract.module";
import {AuthModule} from "./pages/auth/auth.module";
import {UserModule} from "./pages/user/user.module";
import {HttpClientModule} from "@angular/common/http";
import {DepartmentModule} from "./pages/department/department.module";
import {ManagementModule} from "./pages/management/management.module";
import {DealerModule} from "./pages/dealer/dealer.module";
import {NgxPermissionsModule} from "ngx-permissions";
import {NgChartsModule} from "ng2-charts";
import {MatDialogModule} from "@angular/material/dialog";

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    DepartmentModule,
    DealerModule,
    ContractModule,
    ManagementModule,
    AuthModule,
    DashboardModule,
    UserModule,
    BrowserAnimationsModule,
    MatDialogModule,
    NgxPermissionsModule.forRoot(),
    NgChartsModule
  ],
  providers: [
    ErrorDialogService,
    authInterceptorProviders
  ],
  bootstrap: [AppComponent],
  entryComponents: [ErrorComponent]
})
export class AppModule { }
