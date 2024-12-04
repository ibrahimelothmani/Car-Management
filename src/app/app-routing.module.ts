import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { ModifyAdminComponent } from './modify-admin/modify-admin.component';
import { ListClientComponent } from './list-client/list-client.component';
import { ListContactComponent } from './list-contact/list-contact.component';
import { AddCarComponent } from './add-car/add-car.component';
import { ModifyCarComponent } from './modify-car/modify-car.component';
import { LoginComponent } from './login/login.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ListAdminComponent } from './list-admin/list-admin.component';
import { ListCarComponent } from './list-car/list-car.component';
import { AuthGuard } from './service/authguard';

const routes: Routes = [
  { path: '', component: HomeComponent, canActivate: [AuthGuard] },
  {
    path: 'addadmin',
    component: AddAdminComponent,
    canActivate: [AuthGuard],
    data: { roles: ['admin'] }, // Requires 'admin' role
  },
  {
    path: 'modify',
    component: ModifyAdminComponent,
    canActivate: [AuthGuard],
    data: { roles: ['admin', 'editor'] }, // Requires either 'admin' or 'editor' role
  },
  { path: 'admin', component: ListAdminComponent, canActivate: [AuthGuard] },
  {
    path: 'client',
    component: ListClientComponent,
    canActivate: [AuthGuard],
    data: { roles: ['client-manager'] }, // Requires 'client-manager' role
  },
  {
    path: 'contact',
    component: ListContactComponent,
    canActivate: [AuthGuard],
  },
  { path: 'car', component: ListCarComponent, canActivate: [AuthGuard] },
  {
    path: 'addcar',
    component: AddCarComponent,
    canActivate: [AuthGuard],
    data: { roles: ['car-manager'] }, // Requires 'car-manager' role
  },
  {
    path: 'modifycar',
    component: ModifyCarComponent,
    canActivate: [AuthGuard],
  },
  { path: 'login', component: LoginComponent },
  { path: '**', component: PageNotFoundComponent, canActivate: [AuthGuard] },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
