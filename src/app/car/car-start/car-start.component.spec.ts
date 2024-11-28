import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CarStartComponent } from './car-start.component';

describe('CarStartComponent', () => {
  let component: CarStartComponent;
  let fixture: ComponentFixture<CarStartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CarStartComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CarStartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
