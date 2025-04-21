import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { ApiService } from '../../../../../services/api.service';

@Component({
  selector: 'app-form-category',
  imports: [CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule],
  templateUrl: './form-category.component.html',
  styleUrl: './form-category.component.css'
})
export class FormCategoryComponent {
  @Input() categoria: any;
  @Output() cerrar = new EventEmitter<boolean>();

  categoriaForm!: FormGroup;
  isEditMode = false;

  constructor(private fb: FormBuilder, private apiService: ApiService) {}

  ngOnInit(): void {
    this.isEditMode = !!this.categoria;
    this.categoriaForm = this.fb.group({
      nameCategory: [this.categoria?.nameCategory || '', Validators.required],
      descriptionCategory: [this.categoria?.descriptionCategory || '']
    });
  }

  guardar(): void {
    if (this.categoriaForm.invalid) return;
    const datos = this.categoriaForm.value;

    if (this.isEditMode && this.categoria?.idCategory) {
      // EDITAR
      this.apiService.update('categories',this.categoria.idCategory, datos).subscribe(() => {
        console.log('Servicio actualizado');
      this.cerrar.emit(true);
      });
    } else {
      // CREAR
      this.apiService.save('categories', datos).subscribe(() => {
        console.log('Servicio creado');
      this.cerrar.emit(true);
      });
    }
  }

  cancelar(): void {
    this.cerrar.emit(false);
  }
}
