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
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  { path: 'login', component: LoginComponent },
  {
    path: 'home',
    canActivate: [AuthGuard],
  },
  {
    path: 'addadmin',
    component: AddAdminComponent,
  },
  {
    path: 'modifyadmin',
    component: ModifyAdminComponent,
  },
  {
    path: 'modifycar',
    component: ModifyCarComponent,
  },
  { path: 'admin', component: ListAdminComponent },
  {
    path: 'client',
    component: ListClientComponent,
  },
  {
    path: 'contact',
    component: ListContactComponent,
  },
  { path: 'car', component: ListCarComponent },
  {
    path: 'addcar',
    component: AddCarComponent,
  },

  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
