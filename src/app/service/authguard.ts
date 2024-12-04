import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(private keycloak: KeycloakService, private router: Router) {}

  async canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
    const isAuthenticated = await this.keycloak.isLoggedIn();

    if (!isAuthenticated) {
      // Redirect to Keycloak login if not authenticated
      await this.keycloak.login({
        redirectUri: window.location.origin + state.url, // Redirect back to attempted route after login
      });
      return false;
    }

    // Check roles if specified in route data
    const requiredRoles = route.data['roles'];
    if (requiredRoles && !this.hasRequiredRoles(requiredRoles)) {
      this.router.navigate(['/login']); // Redirect if user lacks required roles
      return false;
    }

    return true;
  }

  private hasRequiredRoles(requiredRoles: string[]): boolean {
    const userRoles = this.keycloak.getUserRoles();
    return requiredRoles.every((role) => userRoles.includes(role));
  }
}
