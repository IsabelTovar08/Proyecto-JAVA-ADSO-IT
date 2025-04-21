import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatCalendar } from '@angular/material/datepicker';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core'; // Necesario para el DateAdapter
import { MatInputModule } from '@angular/material/input';
import { CommonModule } from '@angular/common';
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
import Swal from 'sweetalert2';
import { startOfWeek, addDays, format, addWeeks, subWeeks, eachDayOfInterval, endOfMonth, startOfMonth } from 'date-fns';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-calendary',
  imports: [MatCardModule, MatCalendar, MatDatepickerModule, SweetAlert2Module, MatIconModule,
    MatInputModule, // Si necesitas un input para el Datepicker
    MatNativeDateModule, CommonModule],
  templateUrl: './calendary.component.html',
  styleUrl: './calendary.component.css'
})
export class CalendaryComponent {
  @Input() reservations: any[] = [];
  
  currentWeekStart!: Date;
  weekDays: { label: string, date: Date }[] = [];
  selectedDate: Date = new Date();
  isDayView: boolean = false;  // Definimos isDayView como false por defecto
  isMonthView: boolean = false;
  daysInMonth: any[] = [];
  currentMonth: Date = new Date(); // Definimos la variable para el mes actual
  dayNames: string[] = ['Lun', 'Mar', 'Mié', 'Jue', 'Vie', 'Sáb', 'Dom'];
  today: Date = new Date();

  showModal: boolean = false;

  // Función para abrir el modal del calendario
  openModal() {
    this.showModal = true;
  }

  // Función para cerrar el modal
  closeModal() {
    this.showModal = false;
  }

  
  ngOnInit() {
    this.setCurrentWeek(new Date());
    this.setDaysInMonth(new Date());
  }

  setCurrentWeek(date: Date) {
    this.currentWeekStart = startOfWeek(date, { weekStartsOn: 1 });
    this.weekDays = Array.from({ length: 7 }, (_, i) => {
      const day = addDays(this.currentWeekStart, i);
      return { label: format(day, "EEEE dd MMM"), date: day };
    });
    this.currentMonth = startOfMonth(date); // Actualizamos el mes al inicio de la semana
    console.log(date.getDay)
  }

  setDaysInMonth(date: Date) {
    const start = startOfMonth(date);
    const end = endOfMonth(date);
    this.daysInMonth = eachDayOfInterval({ start, end }).map(day => {
      return { date: day, label: format(day, 'dd') };
    });
  }

  nextWeek() {
    this.setCurrentWeek(addWeeks(this.currentWeekStart, 1));
  }

  previousWeek() {
    this.setCurrentWeek(subWeeks(this.currentWeekStart, 1));
  }

  getHourSlots() {
    return ['08:00', '09:00', '09:30', '10:00', '10:30', '11:00', '12:00'];
  }

  getReservationsByTime(day: Date, time: string) {
    const formattedDate = format(day, 'yyyy-MM-dd');
    return this.reservations.filter(res => res.date === formattedDate && res.time === time);
  }

  getReservationsByDay(day: any) {
    return this.reservations.filter(res => format(new Date(res.date), 'yyyy-MM-dd') === format(day.date, 'yyyy-MM-dd'));
  }

  onDayClick(day: any) {
    this.selectedDate = day.date;
  }

  showReservationDetails(reservation: any) {
    Swal.fire({
      title: reservation.service,
      html: `
        <b>Cliente:</b> ${reservation.clientName}<br>
        <b>Fecha:</b> ${reservation.date}<br>
        <b>Hora:</b> ${reservation.time}
      `,
      icon: 'info'
    });
  }

  toggleView() {
    this.isDayView = !this.isDayView;  // Cambiar entre vista de día y mes
  }

  changeToDayView() {
    this.isDayView = true;  // Forzar vista de día
  }

  changeToMonthView() {
    this.isDayView = false;  // Forzar vista de mes
  }

  getFormattedDate(date: Date, formatStr: string): string {
    return format(date, formatStr);
  }
    // Método para manejar la selección de una fecha
    onDateSelected(date: Date) {
      this.selectedDate = date;
      console.log('Fecha seleccionada:', this.selectedDate);
  
      // Si quieres hacer algo con la fecha seleccionada, como obtener reservas de ese día
      const selectedDayReservations = this.getReservationsByDay({ date: this.selectedDate });
      console.log('Reservas para este día:', selectedDayReservations);
    }
   // Obtener las semanas del mes actual
   getWeeksInMonth() {
    const start = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth(), 1);
    const end = new Date(this.currentMonth.getFullYear(), this.currentMonth.getMonth() + 1, 0);
    
    // Ajustar al lunes más cercano hacia atrás
    const startDay = start.getDay() === 0 ? 6 : start.getDay() - 1;
    const calendarStart = new Date(start);
    calendarStart.setDate(start.getDate() - startDay);
  
    // Ajustar al domingo más cercano hacia adelante
    const endDay = end.getDay() === 0 ? 6 : end.getDay() - 1;
    const calendarEnd = new Date(end);
    calendarEnd.setDate(end.getDate() + (6 - endDay));
  
    const weeks = [];
    let currentWeek = [];
  
    const currentDate = new Date(calendarStart);
  
    while (currentDate <= calendarEnd) {
      currentWeek.push({
        date: new Date(currentDate),
        isCurrentMonth: currentDate.getMonth() === this.currentMonth.getMonth()
      });
  
      if (currentWeek.length === 7) {
        weeks.push(currentWeek);
        currentWeek = [];
      }
  
      currentDate.setDate(currentDate.getDate() + 1);
    }
  
    return weeks;
  }
  
}
