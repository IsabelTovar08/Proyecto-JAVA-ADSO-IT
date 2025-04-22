import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatTableModule } from '@angular/material/table';
import Swal from 'sweetalert2';
import { ApiService } from '../../../../../services/api.service';
import { FormPaymentMethodComponent } from '../form-payment-method/form-payment-method.component';

@Component({
  selector: 'app-list-payment-method',
  imports: [MatCardModule, MatTableModule, MatIconModule, MatButtonModule, MatFormFieldModule, FormsModule, MatInputModule],
  templateUrl: './list-payment-method.component.html',
  styleUrl: './list-payment-method.component.css'
})
export class ListPaymentMethodComponent {
  metodosPago: [] = [];
  metodoPagoSeleccionado?: any;
  nombreMetodoPago: String = '';
  filteredmetodosPago: any[] = [];


  displayedColumns: string[] = ['idPaymentMethod', 'namePaymentMethod', 'actions'];

  constructor(private dialog: MatDialog, private apiService: ApiService) { }

  ngOnInit(): void {
    this.cargarmetodosPago();
  }
cargarmetodosPago(){
  this.apiService.getAll('paymentMethods').subscribe(metodosPago => {
    this.metodosPago = metodosPago;
    console.log(metodosPago)
  })
}
  openFormCreate(): void {
    const dialogRef = this.dialog.open(FormPaymentMethodComponent, {
      width: '400px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarmetodosPago(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  editmetodosPago(metodosPago: any): void {
    // Abre el formulario con los datos del metodosPago
    const dialogRef = this.dialog.open(FormPaymentMethodComponent, {
      width: '400px',
      data: this.metodoPagoSeleccionado
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarmetodosPago(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  deletemetodosPago(metodosPago: any): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Quieres eliminar el metodosPago: ${metodosPago.namePaymentMethod}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#765dfb',
      cancelButtonColor: '#d5d7f9',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
       this.apiService.delete('paymentMethods', metodosPago.idPaymentMethod).subscribe(() => {
        this.cargarmetodosPago();
       })
      }
    });
  }
  aplicarFiltros() {
    const filtros = {
      namePaymentMethod: this.nombreMetodoPago || null
    };

    this.apiService.filter('paymentMethods', filtros).subscribe(result => {
      const agrupados: any = {};
      result.forEach((metodosPago: any) => {
        const catId = metodosPago.idPaymentMethod;
        if (!agrupados[catId]) {
          agrupados[catId] = {
            idPaymentMethod: catId,
            namePaymentMethod: metodosPago.namePaymentMethod
          };
        }
      });
      Error
      this.filteredmetodosPago = Object.values(agrupados);
    });
  }

  limpiarFiltros() {
    this.nombreMetodoPago = '';
    this.filteredmetodosPago = [];

  }
}
