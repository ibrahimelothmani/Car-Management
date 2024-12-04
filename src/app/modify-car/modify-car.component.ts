import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Car } from '../Entity/car';
import { CrudService } from '../service/crudservice';

@Component({
  selector: 'app-modify-car',
  templateUrl: './modify-car.component.html',
  styleUrl: './modify-car.component.css'
})
export class ModifyCarComponent implements OnInit {

  CarForm: FormGroup;
  userFile: any;
  message: any;
  imagePath: any;
  imgURL: any;

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
      image: new FormControl('', [Validators.required]),
      model: new FormControl('', [Validators.required]),
      prix: new FormControl('', [Validators.required]),
      annee: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      categorie: new FormControl('', [Validators.required]),
    };
    this.CarForm = this.fb.group(formControls);
  }

  // Getters for form controls
  get nom() {
    return this.CarForm.get('nom');
  }

  get image() {
    return this.CarForm.get('image');
  }

  get model() {
    return this.CarForm.get('model');
  }

  get prix() {
    return this.CarForm.get('prix');
  }

  get annee() {
    return this.CarForm.get('annee');
  }

  get description() {
    return this.CarForm.get('description');
  }

  get categorie() {
    return this.CarForm.get('categorie');
  }

  ngOnInit(): void {
    this.loading = true; // Start loading spinner
    let idEvent = this.router.snapshot.params['id'];
    this.id = idEvent;

    this.service.findCarById(idEvent).subscribe({
      next: (result) => {
        let car = result as Car;
        this.CarForm.patchValue({
          nom: car.nom,
          image: car.image,
          model: car.model,
          prix: car.prix,
          annee: car.annee,
          description: car.description,
          categorie: car.categorie,
        });
        this.loading = false; // Stop loading spinner
      },
      error: (err) => {
        console.error('Error fetching car details', err);
        this.error = 'Failed to load car details. Please try again.';
        this.loading = false;
      }
    });
  }

  updateCar() {
    if (this.CarForm.invalid) {
      return; // Prevent submission if the form is invalid
    }

    let data = this.CarForm.value;
    let car = new Car(
      this.id,
      data.nom,
      this.imgURL,
      data.model,
      data.prix,
      data.annee,
      data.description,
      data.categorie
    );

    this.service.updateCar(this.id, car).subscribe({
      next: (res) => {
        console.log('Car updated successfully', res);
        this.route.navigate(['/car']);
      },
      error: (err) => {
        console.error('Error updating car', err);
        this.error = 'Failed to update car. Please try again.';
      }
    });
  }

  onSelectFile(event: any) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.userFile = file;
      var mimeType = event.target.files[0].type;
      if (mimeType.match(/image\/*/) == null) {
        this.message = 'Only images are supported.';
        return;
      }
      var reader = new FileReader();
      this.imagePath = file;
      reader.readAsDataURL(file);
      reader.onload = (_event) => {
        this.imgURL = reader.result;
      };
    }
  }

}
