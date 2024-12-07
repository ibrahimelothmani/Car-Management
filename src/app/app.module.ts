import { APP_INITIALIZER, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { AddCarComponent } from './add-car/add-car.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { ListClientComponent } from './list-client/list-client.component';
import { ListContactComponent } from './list-contact/list-contact.component';
import { ListAdminComponent } from './list-admin/list-admin.component';
import { ListCarComponent } from './list-car/list-car.component';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { ModifyAdminComponent } from './modify-admin/modify-admin.component';
import { ModifyCarComponent } from './modify-car/modify-car.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HTTP_INTERCEPTORS, HttpClient, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { KeycloakService } from './service/keycloak.service';
import { HttpTokenInterceptor } from './service/http-token.interceptor';

export function kcFactory(kcService: KeycloakService) {
  return () => kcService.init();
}

@NgModule({
  declarations: [
    AppComponent,
    AddAdminComponent,
    AddCarComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    ListClientComponent,
    ListContactComponent,
    ListAdminComponent,
    ListCarComponent,
    LoginComponent,
    MenuComponent,
    ModifyAdminComponent,
    ModifyCarComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  providers: [
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    },
    {
      provide: APP_INITIALIZER,
      deps: [KeycloakService],
      useFactory: kcFactory,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
