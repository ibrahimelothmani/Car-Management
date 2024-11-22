import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { CarsModule } from './cars/cars.module';
import { SharedModule } from './shared/shared.module';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { CarListComponent } from './cars/car-list/car-list.component';
import { CarItemComponent } from './cars/car-list/car-item/car-item.component';
import { CarStartComponent } from './cars/car-start/car-start.component';
import { CarDetailComponent } from './cars/car-detail/car-detail.component';
import { CarEditComponent } from './cars/car-edit/car-edit.component';
import { CarsComponent } from './cars/cars.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    PageNotFoundComponent,
    CarListComponent,
    CarItemComponent,
    CarStartComponent,
    CarDetailComponent,
    CarEditComponent,
    CarsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CarsModule,
    SharedModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [HomeComponent]
})
export class AppModule { }
