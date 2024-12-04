import { Component, OnInit } from '@angular/core';
import { Car } from '../Entity/car';
import { CrudService } from '../service/crudservice';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-car',
  templateUrl: './list-car.component.html',
  styleUrls: ['./list-car.component.css'], // Corrected property name
})
export class ListCarComponent implements OnInit {
  listCar: Car[] = []; // Initialized the array
  car: Car | null = null; // Corrected type to align with the expected data

  constructor(private service: CrudService, private router: Router) {}

  // Method to delete a car
  DeleteCar(car: Car): void {
    if (confirm(`Voulez-vous supprimer cette voiture avec l'ID ${car.id} ?`)) {
      this.service.deleteCar(car.id).subscribe({
        next: () => {
          this.listCar = this.listCar.filter((c) => c.id !== car.id); // Update the list locally
        },
        error: (err) => {
          console.error('Error deleting car:', err);
          alert('Une erreur est survenue lors de la suppression.');
        },
      });
    }
  }

  // Method to navigate to modify car page
  ModifyCar(car: Car): void {
    this.router.navigate(['/modifycar', car.id]); // Pass only the ID
  }

  // Lifecycle hook to fetch car data
  ngOnInit(): void {
    this.service.getCar().subscribe({
      next: (cars) => {
        this.listCar = cars; // Assign fetched cars to the list
      },
      error: (err) => {
        console.error('Error fetching cars:', err);
        alert('Une erreur est survenue lors du chargement des données.');
      },
    });
  }
}
