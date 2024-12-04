import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { Admin } from '../Entity/admin';
import { CrudService } from '../service/crudservice';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {

  messageCommande = '';
  loginForm: FormGroup;
  constructor(
    private fb: FormBuilder,
    private service: CrudService,
    private router: Router
  ) {
    let formControls = {
      email: new FormControl('', [Validators.required, Validators.email]),
      mdp: new FormControl('', [Validators.required]),
    };

    this.loginForm = this.fb.group(formControls);
  }

  get email() {
    return this.loginForm.get('email');
  }
  get mdp() {
    return this.loginForm.get('mdp');
  }
  ngOnInit(): void {}

  login() {
    let data = this.loginForm.value;
    console.log(data);
    let admin = new Admin(null, null, null, data.email, data.mdp, null);
    console.log(admin);
    if (data.email == 0 || data.mdp == 0) {
      this.messageCommande = `<div class="alert alert-warning" role="alert">
        service en panne!!!! 
      </div>`;
    } else {
      this.service.loginAdmin(admin).subscribe(
        (res) => {
          console.log(res);
          let token = res.token;
          localStorage.setItem('myToken', res.token);
          localStorage.setItem('role', res.role);
          this.router.navigate(['']).then(() => window.location.reload());
        },

        (err) => {
          console.log(err);
          this.messageCommande = `<div class="alert alert-warning" role="alert">
          service en panne!!!! 
        </div>`;
        }
      );
    }
  }
}