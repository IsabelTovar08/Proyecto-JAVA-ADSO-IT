import {
  Component,
  Input,
  Output,
  EventEmitter,
  OnChanges,
  SimpleChanges,
  OnInit
} from '@angular/core';
import { FormGroup, FormBuilder, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

import { MatDialogModule } from '@angular/material/dialog';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../../../../services/api.service';

@Component({
  selector: 'app-form-service',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule
  ],
  templateUrl: './form-service.component.html',
  styleUrls: ['./form-service.component.css']
})
export class FormServiceComponent implements OnInit {
  @Input() servicio: any = null;
  @Input() categoria: any;
  @Output() cerrar = new EventEmitter<boolean>();

  servicioForm!: FormGroup;
  isEditMode = false;

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit(): void {
    this.isEditMode = !!this.servicio;
    console.log(this.servicio)
    this.servicioForm = this.fb.group({
      nameService: [this.servicio?.nameService || '', Validators.required],
      descriptionService: [this.servicio?.descriptionService || ''],
      basePriceService: [
        this.servicio?.basePriceService ?? '',
        [Validators.required, Validators.min(0)]
      ],
      idCategory: [this.servicio?.idCategory || this.categoria?.idCategory, Validators.required]
    });
  }

  guardar(): void {
    if (this.servicioForm.invalid) return;
    const datos = this.servicioForm.value;

    if (this.isEditMode && this.servicio?.idService) {
      // EDITAR
      this.apiService.update('services',this.servicio.idService, datos).subscribe(() => {
        console.log('Servicio actualizado');
      this.cerrar.emit(true);
      });
    } else {
      // CREAR
      this.apiService.save('services', datos).subscribe(() => {
        console.log('Servicio creado');
      this.cerrar.emit(true);
      });
    }
  }

  cancelar(): void {
    this.cerrar.emit(false);
  }
}
