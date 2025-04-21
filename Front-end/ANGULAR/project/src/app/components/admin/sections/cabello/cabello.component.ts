import { Component } from '@angular/core';
import { CalendaryComponent } from "../../calendary/calendary.component";

@Component({
  selector: 'app-cabello',
  imports: [CalendaryComponent],
  templateUrl: './cabello.component.html',
  styleUrl: './cabello.component.css'
})
export class CabelloComponent {
  reservations: any[] = [];
  selectedDate: Date = new Date();

  ngOnInit(): void {
    this.loadMockReservations();
  }

  loadMockReservations(): void {
    // Datos quemados solo para probar
    this.reservations = [
      { time: '09:00 AM', clientName: 'Laura Martínez', service: 'Corte de cabello' },
      { time: '11:00 AM', clientName: 'Andrea Torres', service: 'Tinte' },
      { time: '02:00 PM', clientName: 'Carla Gómez', service: 'Peinado' }
    ];
  }

  onDateSelected(date: Date): void {
    this.selectedDate = date;
    // Podrías aquí aplicar lógica para cambiar reservas por fecha
    this.loadMockReservations(); // Para probar, seguimos usando los mismos datos
  }
  hairReservations = [
    { date: '2025-04-14', time: '08:00', service: 'Tinte', clientName: 'Ana', color: '#81c784' },
    { date: '2025-04-15', time: '09:30', service: 'Corte', clientName: 'Lucía', color: '#64b5f6' },
    { date: '2025-04-16', time: '10:00', service: 'Keratina', clientName: 'Sofía', color: '#e57373' },
    { date: '2025-04-17', time: '11:00', service: 'Peinado', clientName: 'Laura', color: '#fff176' },
  ];
  
}
