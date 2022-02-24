import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: 'departments',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/department/department.module').then(module => module.DepartmentModule)
  }
  ,
  {
    path: 'dealers',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/dealer/dealer.module').then(module => module.DealerModule)
  },
  {
    path: 'contracts',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/contract/contract.module').then(module => module.ContractModule)
  },
  {
    path: 'managements',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/management/management.module').then(module => module.ManagementModule)
  },
  {
    path: 'auth',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/auth/auth.module').then(module => module.AuthModule)
  },
  {
    path: 'dashboard',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/dashboard/dashboard.module').then(module => module.DashboardModule)
  },
  {
    path: 'users',
    pathMatch: 'prefix',
    loadChildren: () => import('./pages/user/user.module').then(module => module.UserModule)
  },
  {
    path: '**',
    redirectTo: 'dashboard'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
