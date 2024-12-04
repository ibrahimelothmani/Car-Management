import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { CrudService } from '../service/crudservice';
import { Router } from '@angular/router';
import { Car } from '../Entity/car';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent {
  message = '';
  loading: boolean = false;
  error: string | null = null;

  carForm: FormGroup;
  selectedFile: any;
  imagePath: any;
  imgURL: any;

  constructor(
    private service: CrudService,
    private router: Router,
    private fb: FormBuilder
  ) {
    this.carForm = this.fb.group({
      id: new FormControl(null),
      nom: new FormControl('', [Validators.required]),
      image: new FormControl('', [Validators.required]),
      model: new FormControl('', [Validators.required]),
      prix: new FormControl(null, [Validators.required]),
      annee: new FormControl(null, [Validators.required]),
      description: new FormControl('', [Validators.required]),
      categorie: new FormControl('', [Validators.required]),
    });
  }

  get nom() {
    return this.carForm.get('nom');
  }
  get image() {
    return this.carForm.get('image');
  }
  get model() {
    return this.carForm.get('model');
  }
  get prix() {
    return this.carForm.get('prix');
  }
  get annee() {
    return this.carForm.get('annee');
  }
  get description() {
    return this.carForm.get('description');
  }
  get categorie() {
    return this.carForm.get('categorie');
  }

  addNewCar() {
    if (this.carForm.invalid) {
      this.message = 'Please fill in all required fields.';
      return;
    }

    const data = this.carForm.value;
    const car = new Car(
      data.id,
      data.nom,
      this.imgURL,
      data.model,
      data.prix,
      data.annee,
      data.description,
      data.categorie
    );

    this.loading = true;
    this.error = null;

    this.service.addCar(car).subscribe(
      (res) => {
        this.message = 'Car added successfully!';
        this.loading = false;
        this.router.navigate(['/cars']);
      },
      (err) => {
        console.error(err);
        this.error = 'Server error. Please try again later.';
        this.loading = false;
      }
    );
  }

  onSelectFile(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.selectedFile = file;

      const mimeType = file.type;
      if (!mimeType.match(/image\/*/)) {
        this.message = 'Only images are supported.';
        return;
      }

      const reader = new FileReader();
      this.imagePath = file;
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;
      };
    }
  }
}
