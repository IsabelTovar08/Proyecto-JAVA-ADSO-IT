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
import { FormPositionComponent } from '../../position/form-position/form-position.component';

@Component({
  selector: 'app-list-person',
  imports: [MatCardModule, MatTableModule, MatIconModule, MatButtonModule, MatFormFieldModule, FormsModule, MatInputModule],
  templateUrl: './list-person.component.html',
  styleUrl: './list-person.component.css'
})
export class ListPersonComponent {
  personas: [] = [];
  personaSeleccionada?: any;
  nombrePersona: String = '';
  filteredpersonas: any[] = [];


  displayedColumns: string[] = ['idPosition', 'namePosition', 'actions'];

  constructor(private dialog: MatDialog, private apiService: ApiService) { }

  ngOnInit(): void {
    this.cargarpersonas();
  }
cargarpersonas(){
  this.apiService.getAll('person').subscribe(personas => {
    this.personas = personas;
    console.log(personas)
  })
}
  openForm(): void {
    const dialogRef = this.dialog.open(FormPositionComponent, {
      width: '400px',
      data: this.personaSeleccionada
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarpersonas(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  editCargo(cargo: any): void {
    // Abre el formulario con los datos del cargo
    const dialogRef = this.dialog.open(FormPositionComponent, {
      width: '400px',
      data: this.personaSeleccionada
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarpersonas(); // Recarga la tabla
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
        this.cargarpersonas();
       })
      }
    });
  }
  aplicarFiltros() {
    const filtros = {
      namePosition: this.nombrePersona || null
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
      this.filteredpersonas = Object.values(agrupados);
    });
  }

  limpiarFiltros() {
    this.nombrePersona = '';
    this.filteredpersonas = [];

  }
}
