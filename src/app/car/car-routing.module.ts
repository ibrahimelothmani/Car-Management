import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CarComponent } from './car.component';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { CarEditComponent } from './car-edit/car-edit.component';
import { CarStartComponent } from './car-start/car-start.component';
import { PageNotFoundComponent } from '../page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', redirectTo: 'car', pathMatch: 'full' },
  {
    path: 'car',
    component: CarComponent,
    children: [
      { path: 'start', component: CarStartComponent },
      { path: 'new', component: CarEditComponent },
      { path: ':id', component: CarDetailComponent },
      { path: 'edit/:id', component: CarEditComponent },
    ],
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class CarRoutingModule {}
