import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { ApiService } from '../../../../../services/api.service';

@Component({
  selector: 'app-form-position',
  imports: [    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule],
  templateUrl: './form-position.component.html',
  styleUrl: './form-position.component.css'
})
export class FormPositionComponent {
  @Input() cargo: any;
  @Output() cerrar = new EventEmitter<boolean>();

  cargoForm!: FormGroup;
  isEditMode = false;
  constructor(private fb: FormBuilder, private apiService: ApiService,public dialogRef: MatDialogRef<FormPositionComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      this.cargo = data;

  }



  ngOnInit(): void {
    this.isEditMode = !!this.cargo;
    console.log(this.cargo)
    this.cargoForm = this.fb.group({

      namePosition: [this.cargo?.namePosition || '', Validators.required],
    });
  }

  guardarCargo(){
    if (this.isEditMode) {
      this.apiService.update('positions', this.cargo.idPosition, this.cargoForm.value).subscribe(() => {
        console.log("update");
        this.dialogRef.close('reload');
      })
    }
    else{
      this.apiService.save('positions', this.cargoForm.value).subscribe(() => {
        console.log("creado");
        this.dialogRef.close('reload');
      })
    }
  }
}
