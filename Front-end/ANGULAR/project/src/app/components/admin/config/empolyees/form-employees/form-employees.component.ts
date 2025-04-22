import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ApiService } from '../../../../../services/api.service';
import { FormPersonComponent } from '../../person/form-person/form-person.component';

@Component({
  selector: 'app-form-employees',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './form-employees.component.html',
  styleUrl: './form-employees.component.css'
})
export class FormEmployeesComponent {
  @Input() empleado: any;
  @Output() cerrar = new EventEmitter<boolean>();

  cargos?: any[];
  personas?: any[];


  empleadoForm!: FormGroup;
  isEditMode = false;
  constructor(private fb: FormBuilder, private apiService: ApiService, public dialogRef: MatDialogRef<FormPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.empleado = data;

  }

  cargarCargos() {
    this.apiService.getAll('positions').subscribe(cargos => {
      this.cargos = cargos;
      console.log(cargos)
    })
  }

  cargarPersonas() {
    this.apiService.getAll('person').subscribe(personas => {
      this.personas = personas;
      console.log(personas)
    })
  }


  ngOnInit(): void {
    this.cargarCargos();
    this.cargarPersonas();
    this.isEditMode = !!this.empleado;
    console.log(this.empleado)
    this.empleadoForm = this.fb.group({
      id_person: [this.empleado?.id_person || '', Validators.required],
      id_position: [this.empleado?.id_position || '', Validators.required],
      salaryEmployee: [this.empleado?.salaryEmployee || '', Validators.required],
    });
  }

  guardarempleado() {
    if (this.isEditMode) {
      this.apiService.update('employees', this.empleado.idEmployee, this.empleadoForm.value).subscribe(() => {
        console.log("update");
        this.dialogRef.close('reload');
      })
    }
    else {
      this.apiService.save('employees', this.empleadoForm.value).subscribe(() => {
        console.log("creado");
        this.dialogRef.close('reload');
      })
    }
  }
}
