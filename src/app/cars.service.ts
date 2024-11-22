import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Car } from './car.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CarsService {

  Base = 'http://localhost:8080/api/cars';


  constructor(private http: HttpClient) { }

  getAllCars():Observable<any>{
    return this.http.get<Car[]>(this.Base)
  }

  getCars(id:string):Observable<any> {
    return this.http.get<Car>(`${this.Base}/${id}`)
  }

  postCar(car: Car):Observable<Car> {
    return this.http.post<Car>(this.Base, car)
  }
  updateCar(car: Car):Observable<Car> {
    return this.http.put<Car>(this.Base + "/" + car.id, car)
  }

  deleteCar(car: Car):Observable<String> {
    return this.http.delete<String>(`${this.Base}/${car.id}`)
  }

}
