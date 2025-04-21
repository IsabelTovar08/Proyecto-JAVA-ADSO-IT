import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { FormPositionComponent } from '../form-position/form-position.component';
import Swal from 'sweetalert2';
import { MatCardModule } from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { ApiService } from '../../../../../services/api.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-list-position',
  imports: [MatCardModule, MatTableModule, MatIconModule, MatButtonModule, MatFormFieldModule, FormsModule, MatInputModule],
  templateUrl: './list-position.component.html',
  styleUrl: './list-position.component.css'
})
export class ListPositionComponent {
  cargos: [] = [];
  cargoSeleccionado?: any;
  nombreCargo: String = '';
  filteredCargos: any[] = [];


  displayedColumns: string[] = ['idPosition', 'namePosition', 'actions'];

  constructor(private dialog: MatDialog, private apiService: ApiService) { }

  ngOnInit(): void {
    this.cargarCargos();
  }
cargarCargos(){
  this.apiService.getAll('positions').subscribe(cargos => {
    this.cargos = cargos;
    console.log(cargos)
  })
}
  openForm(): void {
    const dialogRef = this.dialog.open(FormPositionComponent, {
      width: '400px',
      data: this.cargoSeleccionado
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarCargos(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  editCargo(cargo: any): void {
    // Abre el formulario con los datos del cargo
    const dialogRef = this.dialog.open(FormPositionComponent, {
      width: '400px',
      data: this.cargoSeleccionado
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarCargos(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  deleteCargo(cargo: any): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Quieres eliminar el cargo: ${cargo.namePosition}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#765dfb',
      cancelButtonColor: '#d5d7f9',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
       this.apiService.delete('positions', cargo.idPosition).subscribe(() => {
        this.cargarCargos();
       })
      }
    });
  }
  aplicarFiltros() {
    const filtros = {
      namePosition: this.nombreCargo || null
    };

    this.apiService.filter('positions', filtros).subscribe(result => {
      const agrupados: any = {};
      result.forEach((cargo: any) => {
        const catId = cargo.idPosition;
        if (!agrupados[catId]) {
          agrupados[catId] = {
            idPosition: catId,
            namePosition: cargo.namePosition
          };
        }
      });
      Error
      this.filteredCargos = Object.values(agrupados);
    });
  }

  limpiarFiltros() {
    this.nombreCargo = '';
    this.filteredCargos = [];

  }
}
