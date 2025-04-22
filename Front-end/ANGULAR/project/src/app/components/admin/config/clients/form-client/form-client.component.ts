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
  selector: 'app-form-client',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './form-client.component.html',
  styleUrl: './form-client.component.css'
})
export class FormClientComponent {
  @Input() cliente: any;
  @Output() cerrar = new EventEmitter<boolean>();

  personas?: any[];


  clienteForm!: FormGroup;
  isEditMode = false;
  constructor(private fb: FormBuilder, private apiService: ApiService, public dialogRef: MatDialogRef<FormPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.cliente = data;

  }

  cargarPersonas() {
    this.apiService.getAll('person').subscribe(personas => {
      this.personas = personas;
      console.log(personas)
    })
  }


  ngOnInit(): void {
    this.cargarPersonas();
    this.isEditMode = !!this.cliente;
    console.log(this.cliente)
    this.clienteForm = this.fb.group({
      id_person: [this.cliente?.id_person || '', Validators.required]
      
    });
  }

  guardarcliente() {
    if (this.isEditMode) {
      this.apiService.update('clients', this.cliente.idEmployee, this.clienteForm.value).subscribe(() => {
        console.log("update");
        this.dialogRef.close('reload');
      })
    }
    else {
      this.apiService.save('clients', this.clienteForm.value).subscribe(() => {
        console.log("creado");
        this.dialogRef.close('reload');
      })
    }
  }
}
