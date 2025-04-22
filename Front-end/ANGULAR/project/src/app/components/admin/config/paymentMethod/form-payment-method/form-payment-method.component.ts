import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Inject, Input, Output } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormField, MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { ApiService } from '../../../../../services/api.service';

@Component({
  selector: 'app-form-payment-method',
  imports: [    CommonModule,
    ReactiveFormsModule,
    MatFormField,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule],
  templateUrl: './form-payment-method.component.html',
  styleUrl: './form-payment-method.component.css'
})
export class FormPaymentMethodComponent {
  @Input() metodoPago: any;
  @Output() cerrar = new EventEmitter<boolean>();

  municipios?: any[];

  metodoPagoForm!: FormGroup;
  isEditMode = false;
  constructor(private fb: FormBuilder, private apiService: ApiService, public dialogRef: MatDialogRef<FormPaymentMethodComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
    this.metodoPago = data;

  }

  ngOnInit(): void {
    this.isEditMode = !!this.metodoPago;
    console.log(this.metodoPago)
    this.metodoPagoForm = this.fb.group({

      namePaymentMethod: [this.metodoPago?.namePaymentMethod || '', Validators.required]
    });
  }

  guardarmetodoPago() {
    if (this.isEditMode) {
      this.apiService.update('paymentMethods', this.metodoPago.idPaymentMethod, this.metodoPagoForm.value).subscribe(() => {
        console.log("update");
        this.dialogRef.close('reload');
      })
    }
    else {
      this.apiService.save('paymentMethods', this.metodoPagoForm.value).subscribe(() => {
        console.log("creado");
        this.dialogRef.close('reload');
      })
    }
  }
}
