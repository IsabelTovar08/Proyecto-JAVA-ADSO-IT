import { Component } from '@angular/core';
import { CalendaryComponent } from "../../calendary/calendary.component";
import { ApiService } from '../../../../services/api.service';
import { MatDialog } from '@angular/material/dialog'; // Asegúrate de tener este componente
import { FormResumenReservasComponent } from '../resumenReservas/form-resumen-reservas/form-resumen-reservas.component';

@Component({
  selector: 'app-cabello',
  standalone: true,
  imports: [CalendaryComponent],
  templateUrl: './cabello.component.html',
  styleUrl: './cabello.component.css'
})
export class CabelloComponent {
  reservations: any[] = [];
  selectedDate: Date = new Date();

  constructor(private apiService: ApiService, private dialog: MatDialog) {}

  ngOnInit(): void {
    this.loadMockReservations();
    // this.cargarReservas();
  }

  loadMockReservations(): void {
    this.reservations = [
      { time: '09:00 AM', clientName: 'Laura Martínez', service: 'Corte de cabello' },
      { time: '11:00 AM', clientName: 'Andrea Torres', service: 'Tinte' },
      { time: '02:00 PM', clientName: 'Carla Gómez', service: 'Peinado' }
    ];
  }

  onDateSelected(date: Date): void {
    this.selectedDate = date;
    this.loadMockReservations();
  }

  hairReservations = [
    { date: '2025-04-14', time: '08:00', service: 'Tinte', clientName: 'Ana', color: '#81c784' },
    { date: '2025-04-15', time: '09:30', service: 'Corte', clientName: 'Lucía', color: '#64b5f6' },
    { date: '2025-04-16', time: '10:00', service: 'Keratina', clientName: 'Sofía', color: '#e57373' },
    { date: '2025-04-17', time: '11:00', service: 'Peinado', clientName: 'Laura', color: '#fff176' },
  ];

  // cargarReservas(): void {
  //   this.apiService.getAll('reservationDetails').subscribe(rd => {
  //     this.hairReservations = rd;
  //     console.log(rd);
  //   });
  // }

  

}
