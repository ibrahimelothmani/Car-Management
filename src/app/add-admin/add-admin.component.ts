import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { CrudService } from '../service/crudservice';
import { Admin } from '../Entity/admin';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrl: './add-admin.component.css',
})
export class AddAdminComponent implements OnInit {
  messageCommande = '';
  loading: boolean = false; // Added loading state
  error: string | null = null; // Added error state

  AdminForm: FormGroup;

  constructor(
    private service: CrudService,
    private router: Router,
    private fb: FormBuilder
  ) {
    let formControls = {
      nom: new FormControl('', [Validators.required]),
      prenom: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      mdp: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
    };
    this.AdminForm = this.fb.group(formControls);
  }

  get nom() {
    return this.AdminForm.get('nom');
  }

  get prenom() {
    return this.AdminForm.get('prenom');
  }

  get email() {
    return this.AdminForm.get('email');
  }

  get mdp() {
    return this.AdminForm.get('mdp');
  }

  get role() {
    return this.AdminForm.get('role');
  }

  addNewAdmin() {
    let data = this.AdminForm.value;
    let admin = new Admin(
      undefined,
      data.nom,
      data.prenom,
      data.email,
      data.mdp,
      data.role
    );

    // Check if form data is missing
    if (!data.nom || !data.prenom || !data.email || !data.mdp || !data.role) {
      this.messageCommande = `<div class="alert alert-warning" role="alert">
        Tous les champs sont obligatoires!
      </div>`;
      return;
    }

    this.loading = true; // Start the loading spinner
    this.error = null; // Reset error state

    this.service.addAdmin(admin).subscribe(
      (res) => {
        this.messageCommande = `<div class="alert alert-success" role="alert">
          Administrateur ajouté avec succès !
        </div>`;
        this.loading = false; // Stop loading
        this.router.navigate(['/login']);
      },
      (err) => {
        console.error(err);
        this.error = 'Erreur du serveur. Veuillez réessayer plus tard.';
        this.loading = false; // Stop loading on error
        this.messageCommande = `<div class="alert alert-danger" role="alert">
          ${this.error}
        </div>`;
      }
    );
  }

  ngOnInit(): void {}
}
