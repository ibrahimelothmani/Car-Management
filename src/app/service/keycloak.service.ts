import { Injectable } from '@angular/core';
import Keycloak, { KeycloakConfig, KeycloakProfile } from 'keycloak-js';
import { Admin } from '../Entity/admin';

@Injectable({
  providedIn: 'root',
})
export class KeycloakService {
  private _keycloak: Keycloak | undefined;
  private _admin: Admin | undefined;

  get keycloak(): Keycloak {
    if (!this._keycloak) {
      const keycloakConfig: KeycloakConfig = {
        url: 'http://localhost:9090',
        realm: 'car-management',
        clientId: 'car',
      };
      this._keycloak = new Keycloak(keycloakConfig);
    }
    return this._keycloak;
  }

  get admin(): Admin | undefined {
    return this._admin;
  }

  async init(): Promise<void> {
    const authenticated = await this.keycloak.init({
      onLoad: 'login-required',
      checkLoginIframe: false,  // Disable if causing issues
    });

    if (authenticated) {
      const profile = await this.keycloak.loadUserProfile();
      this._admin = profile as unknown as Admin;
      this._admin.token = this.keycloak.token || '';

      // Perform additional setup
      this.initAppState();
    } else {
      console.error('Keycloak initialization failed');
    }
  }

  login(): Promise<void> {
    return this.keycloak.login();
  }

  logout(): Promise<void> {
    return this.keycloak.logout({ redirectUri: 'http://localhost:4200' });
  }

  private initAppState() {
    // Initialize application state that depends on user authentication
    console.log('Initializing application state');
  }
}
