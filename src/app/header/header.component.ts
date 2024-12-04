import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CrudService } from '../service/crudservice';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent {
  userDetails: any;

  constructor(
    private router: Router,
    private service: CrudService,
    private keycloakService: KeycloakService
  ) {
    // Get user info using Keycloak
    this.userDetails = this.service.getUserInfo();
  }

  logout() {
    console.log('Logging out...');
  
    // Clear localStorage
    localStorage.clear();
  
    // Log out from Keycloak
    this.keycloakService.logout().then(() => {
      // Navigate to login page
      this.router.navigate(['/login']);
    });
  }
}
