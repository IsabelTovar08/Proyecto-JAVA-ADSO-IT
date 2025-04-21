import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { ApiService } from '../../../../../services/api.service';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-form-branch',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './form-branch.component.html',
  styleUrl: './form-branch.component.css'
})
export class FormBranchComponent {
  @Input() sucursal: any;
  @Output() cerrar = new EventEmitter<boolean>();

  municipios?: any[];

  sucursalForm!: FormGroup;
  isEditMode = false;
  constructor(private fb: FormBuilder, private apiService: ApiService, public dialogRef: MatDialogRef<FormBranchComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.sucursal = data;

  }

  cargarMunicipios() {
    this.apiService.getAll('municipality').subscribe(municipios => {
      this.municipios = municipios;
    })
  }

  ngOnInit(): void {
    this.cargarMunicipios();
    this.isEditMode = !!this.sucursal;
    console.log(this.sucursal)
    this.sucursalForm = this.fb.group({

      nameBranch: [this.sucursal?.nameBranch || '', Validators.required],
      idMunicipality: [this.sucursal?.idMunicipality || '', Validators.required]
    });
  }

  guardarsucursal() {
    if (this.isEditMode) {
      this.apiService.update('branchs', this.sucursal.idBranch, this.sucursalForm.value).subscribe(() => {
        console.log("update");
        this.dialogRef.close('reload');
      })
    }
    else {
      this.apiService.save('branchs', this.sucursalForm.value).subscribe(() => {
        console.log("creado");
        this.dialogRef.close('reload');
      })
    }
  }
}
