import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FormResumenReservasComponent } from './form-resumen-reservas.component';

describe('FormResumenReservasComponent', () => {
  let component: FormResumenReservasComponent;
  let fixture: ComponentFixture<FormResumenReservasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormResumenReservasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FormResumenReservasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
