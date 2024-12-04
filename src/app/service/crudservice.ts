import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Admin } from '../Entity/admin';
import { Car } from '../Entity/car';
import { Client } from '../Entity/client';
import { Contact } from '../Entity/contact';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root',
})
export class CrudService {

  private apiUrl = 'http://localhost:8080';
  private loginUserUrl = `${this.apiUrl}/api/admin/login`;

  constructor(private http: HttpClient, private keycloakService: KeycloakService) {}

  // Admin CRUD operations
  addAdmin(admin: Admin): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/admin`, admin);
  }

  getAdmin(): Observable<Admin[]> {
    return this.http.get<Admin[]>(`${this.apiUrl}/admin`);
  }

  findAdminById(id: number): Observable<Admin> {
    return this.http.get<Admin>(`${this.apiUrl}/admin/${id}`);
  }

  updateAdmin(id: number, admin: Admin): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/admin/${id}`, admin);
  }

  deleteAdmin(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/admin/${id}`);
  }

  // Admin login
  loginAdmin(admin: Admin): Observable<any> {
    return this.http.post<any>(this.loginUserUrl, admin);
  }

  // Car CRUD operations
  addCar(car: Car): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/car`, car);
  }

  getCar(): Observable<Car[]> {
    return this.http.get<Car[]>(`${this.apiUrl}/car`);
  }

  findCarById(id: number): Observable<Car> {
    return this.http.get<Car>(`${this.apiUrl}/car/${id}`);
  }

  updateCar(id: number, car: Car): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/car/${id}`, car);
  }

  deleteCar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/car/${id}`);
  }

  // Client CRUD operations
  getClient(): Observable<Client[]> {
    return this.http.get<Client[]>(`${this.apiUrl}/client`);
  }

  deleteClient(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/client/${id}`);
  }

  // Contact CRUD operations
  getContact(): Observable<Contact[]> {
    return this.http.get<Contact[]>(`${this.apiUrl}/contact`);
  }

  deleteContact(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/contact/${id}`);
  }

  // Get user info from Keycloak
  getUserInfo() {
    const userInfo = this.keycloakService.getKeycloakInstance().tokenParsed;
    return userInfo ? userInfo : null;
  }

  // Get the Keycloak token
  getToken() {
    return this.keycloakService.getKeycloakInstance().token;
  }

  // Check if the user is logged in
  isLoggedIn(): boolean {
    const token = localStorage.getItem('myToken');
    return !!token; // Simplified the token check
  }
}
