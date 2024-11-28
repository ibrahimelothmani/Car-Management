import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CarRoutingModule } from './car-routing.module';
import { CarDetailComponent } from './car-detail/car-detail.component';
import { CarEditComponent } from './car-edit/car-edit.component';
import { CarListComponent } from './car-list/car-list.component';
import { CarStartComponent } from './car-start/car-start.component';
import { CarItemComponent } from './car-list/car-item/car-item.component';
import { RouterModule } from '@angular/router';
import { CarComponent } from './car.component';

@NgModule({
  declarations: [
    CarComponent,
    CarDetailComponent,
    CarEditComponent,
    CarListComponent,
    CarStartComponent,
    CarItemComponent,
  ],
  imports: [
    CommonModule,
    CarRoutingModule
  ],
  exports: [
    RouterModule
  ]
})
export class CarModule { }

