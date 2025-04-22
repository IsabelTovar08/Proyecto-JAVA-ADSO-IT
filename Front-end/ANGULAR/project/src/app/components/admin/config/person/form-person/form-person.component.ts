import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ApiService } from '../../../../../services/api.service';

@Component({
  selector: 'app-form-person',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './form-person.component.html',
  styleUrl: './form-person.component.css'
})
export class FormPersonComponent {
  @Input() persona: any;
  @Output() cerrar = new EventEmitter<boolean>();

  municipios?: any[];

  personaForm!: FormGroup;
  isEditMode = false;
  constructor(private fb: FormBuilder, private apiService: ApiService, public dialogRef: MatDialogRef<FormPersonComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.persona = data;

  }

  cargarMunicipios() {
    this.apiService.getAll('municipality').subscribe(municipios => {
      this.municipios = municipios;
    })
  }

  ngOnInit(): void {
    this.cargarMunicipios();
    this.isEditMode = !!this.persona;
    console.log(this.persona)
    this.personaForm = this.fb.group({
      namesPerson: [this.persona?.namesPerson || '', Validators.required],
      lastNamePerson: [this.persona?.lastNamePerson || '', Validators.required],
      documentPerson: [this.persona?.documentPerson || '', Validators.required],
      phonePerson: [this.persona?.phonePerson || '', Validators.required],
      emailPerson: [this.persona?.emailPerson || '', Validators.required],
      addressPerson: [this.persona?.addressPerson || '', Validators.required],
      idMunicipality: [this.persona?.idMunicipality || '', Validators.required]
    });
  }

  guardarpersona() {
    if (this.isEditMode) {
      this.apiService.update('person', this.persona.idPerson, this.personaForm.value).subscribe(() => {
        console.log("update");
        this.dialogRef.close('reload');
      })
    }
    else {
      this.apiService.save('person', this.personaForm.value).subscribe(() => {
        console.log("creado");
        this.dialogRef.close('reload');
      })
    }
  }
}
