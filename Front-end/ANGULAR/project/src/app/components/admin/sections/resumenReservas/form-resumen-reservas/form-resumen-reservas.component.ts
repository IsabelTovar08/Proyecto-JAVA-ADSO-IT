import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ApiService } from '../../../../../services/api.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-form-resumen-reservas',
  imports: [
    CommonModule,
    MatCardModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    MatMenuModule,
    MatButtonModule,
    MatSelectModule,
    FormsModule,
  ],
  templateUrl: './form-resumen-reservas.component.html',
  styleUrl: './form-resumen-reservas.component.css'
})
export class FormResumenReservasComponent {
  reserva: any = {
    idReservation: null,
    idClient: null,
    idPaymentMethod: null,
    creationDate: new Date(),
    detalles: []
  };

  nuevoDetalle: any = {
    idService: null,
    idEmployee: null,
    reservationDate: null,
    reservationTime: null,
    discount: 0
  };

  clientes: any[] = [];
  metodosPago: any[] = [];
  servicios: any[] = [];
  empleados: any[] = [];

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<FormResumenReservasComponent>,
    private api: ApiService
  ) { }

  ngOnInit(): void {
    if (this.data?.reserva) this.reserva = { ...this.data.reserva };

    this.api.getAll('clients').subscribe(c => (this.clientes = c));
    this.api.getAll('paymentMethods').subscribe(p => (this.metodosPago = p));
    this.api.getAll('services').subscribe(s => (this.servicios = s));
    this.api.getAll('employees').subscribe(e => (this.empleados = e));
  }

  agregarDetalle() {
    this.reserva.detalles.push({ ...this.nuevoDetalle });
    this.nuevoDetalle = {
      idService: null,
      idEmployee: null,
      reservationDate: null,
      reservationTime: null,
      discount: 0
    };
  }

  eliminarDetalle(index: number) {
    this.reserva.detalles.splice(index, 1);
  }

  guardar() {
    this.api.save('reservations', this.reserva).subscribe((reservaCreada: any) => {
      const detalles = this.reserva.detalles;
      const idReserva = reservaCreada.idReservation;
  
      const enviarDetalles = async () => {
        for (let detalle of detalles) {
          const detalleConFormato = {
            ...detalle,
            reservationTime: this.formatearHora(detalle.reservationTime),
            idReservation: idReserva
          };
          await this.api.save('reservationDetails', detalleConFormato).toPromise();
        }
        this.dialogRef.close(true);
      };
  
      enviarDetalles();
    });
  }
  
  // Método de ayuda
  formatearHora(hora: any): string {
    if (typeof hora === 'string') return hora.length === 5 ? `${hora}:00` : hora;
    const date = new Date(hora);
    return date.toTimeString().split(' ')[0]; // HH:mm:ss
  }
  
  
  
  cerrar() {
    this.dialogRef.close(false);
  }
}


