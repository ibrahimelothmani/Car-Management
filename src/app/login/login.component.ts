import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  messageCommande = '';

  constructor(
    private fb: FormBuilder,
    private keycloakService: KeycloakService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      email: new FormControl('', [Validators.required, Validators.email]),
      mdp: new FormControl('', [Validators.required]),
    });
  }

  get email() {
    return this.loginForm.get('email');
  }

  get mdp() {
    return this.loginForm.get('mdp');
  }

  ngOnInit(): void {}

  async login() {
    if (this.loginForm.valid) {
      try {
        await this.keycloakService.login({
          redirectUri: window.location.origin,
        });
      } catch (error) {
        this.messageCommande = `<div class="alert alert-warning" role="alert">
          Login failed: ${error}
        </div>`;
      }
    } else {
      this.messageCommande = `<div class="alert alert-warning" role="alert">
        Please fill in all required fields correctly.
      </div>`;
    }
  }
}
