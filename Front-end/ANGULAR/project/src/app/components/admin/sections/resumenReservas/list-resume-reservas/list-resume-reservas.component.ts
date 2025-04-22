import { CommonModule } from "@angular/common";
import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { MatButtonModule } from "@angular/material/button";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from "@angular/material/input";
import { MatMenuModule } from "@angular/material/menu";
import { MatSelectModule } from "@angular/material/select";
import Swal from "sweetalert2";
import { ApiService } from "../../../../../services/api.service";
import { MatDialog } from "@angular/material/dialog";
import { FormResumenReservasComponent } from "../form-resumen-reservas/form-resumen-reservas.component";

@Component({
  selector: 'app-list-reservation',
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
    // FormDetailReservaComponent
  ],
  templateUrl: './list-resume-reservas.component.html',
  styleUrl: './list-resume-reservas.component.css'
})
export class ListReservationComponent {
  reservas: any[] = [];
  detalles: any[] = [];


  constructor(private api: ApiService, private dialog: MatDialog) {
    this.cargarReservas();
    this.cargardetalles();

  }

  cargarReservas() {
    this.api.getAll('reservations').subscribe(res => {
      this.reservas = res;
    });
  }
  cargardetalles() {
    this.api.getAll('reservationDetails').subscribe(res => {
      this.detalles = res; // ← Ahora sí guardas los detalles
      console.log(res);
    });
  }
  

  abrirFormularioReserva(reserva: any = null) {
    const dialogRef = this.dialog.open(FormResumenReservasComponent, {
      width: '600px',
      data: { reserva }
    });

    dialogRef.afterClosed().subscribe(recargar => {
      if (recargar) this.cargarReservas();
    });
  }

  eliminarReserva(reserva: any) {
    Swal.fire({
      title: '¿Eliminar reserva?',
      text: `Esto eliminará la reserva y sus detalles.`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
    }).then(result => {
      if (result.isConfirmed) {
        this.api.delete('reservations', reserva.idReservation).subscribe(() => {
          this.cargarReservas();
          Swal.fire('Eliminado', 'Reserva eliminada', 'success');
        });
      }
    });
  }
}
