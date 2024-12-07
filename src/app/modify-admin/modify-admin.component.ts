import { Component } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Admin } from '../Entity/admin';
import { CrudService } from '../service/crudservice';

@Component({
  selector: 'app-modify-admin',
  templateUrl: './modify-admin.component.html',
  styleUrl: './modify-admin.component.css'
})
export class ModifyAdminComponent {

  updateForm: FormGroup;
  id: number;
  loading: boolean = false;
  error: string | null = null;

  constructor(
    private fb: FormBuilder,
    private service: CrudService,
    private route: Router,
    private router: ActivatedRoute
  ) {
    let formControls = {
      nom: new FormControl('', [Validators.required]),
      prenom: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      mdp: new FormControl('', [Validators.required]),
      role: new FormControl('', [Validators.required]),
    };
    this.updateForm = this.fb.group(formControls);
  }

  get nom() {
    return this.updateForm.get('nom');
  }
  get prenom() {
    return this.updateForm.get('prenom');
  }
  get email() {
    return this.updateForm.get('email');
  }
  get mdp() {
    return this.updateForm.get('mdp');
  }
  get role() {
    return this.updateForm.get('role');
  }

  ngOnInit(): void {
    this.loading = true; // Start loading spinner
    let idEvent = this.router.snapshot.params['id'];
    this.id = idEvent;

    this.service.findAdminById(idEvent).subscribe({
      next: (result) => {
        let event = result;
        this.updateForm.patchValue({
          nom: event.nom,
          prenom: event.prenom,
          email: event.email,
          mdp: event.mdp,
          role: event.role
        });
        this.loading = false; // Stop loading spinner
      },
      error: (err) => {
        console.error('Error fetching admin details', err);
        this.error = 'Failed to load admin details. Please try again.';
        this.loading = false;
      }
    });
  }

  updateAdmin() {
    if (this.updateForm.invalid) {
      return; // Prevent submission if the form is invalid
    }

    let data = this.updateForm.value;
    let admin = new Admin(this.id, data.nom, data.prenom, data.email, data.mdp, data.role);

    this.service.updateAdmin(this.id, admin).subscribe({
      next: (res) => {
        console.log('Admin updated successfully', res);
        this.route.navigate(['/admin']);
      },
      error: (err) => {
        console.error('Error updating admin', err);
        this.error = 'Failed to update admin. Please try again.';
      }
    });
  }

}
