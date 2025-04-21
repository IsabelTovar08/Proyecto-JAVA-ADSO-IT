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
  selector: 'app-list-branch',
  imports: [MatCardModule, MatTableModule, MatIconModule, MatButtonModule, MatFormFieldModule, FormsModule, MatInputModule],
  templateUrl: './list-branch.component.html',
  styleUrl: './list-branch.component.css'
})
export class ListBranchComponent {
  sucursales: [] = [];
  sucursalSeleccionada?: any;
  nombresucursal: String = '';
  filteredsucursales: any[] = [];


  displayedColumns: string[] = ['idBranch', 'nameBranch', 'actions'];

  constructor(private dialog: MatDialog, private apiService: ApiService) { }

  ngOnInit(): void {
    this.cargarsucursales();
  }
cargarsucursales(){
  this.apiService.getAll('branchs').subscribe(sucursales => {
    this.sucursales = sucursales;
    console.log(sucursales)
  })
}
  openForm(): void {
    const dialogRef = this.dialog.open(FormPositionComponent, {
      width: '400px',
      data: this.sucursalSeleccionada
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarsucursales(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  editCargo(cargo: any): void {
    // Abre el formulario con los datos del cargo
    const dialogRef = this.dialog.open(FormPositionComponent, {
      width: '400px',
      data: this.sucursalSeleccionada
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarsucursales(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  deleteCargo(cargo: any): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Quieres eliminar el cargo: ${cargo.nameBranch}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#765dfb',
      cancelButtonColor: '#d5d7f9',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
       this.apiService.delete('positions', cargo.idBranch).subscribe(() => {
        this.cargarsucursales();
       })
      }
    });
  }
  aplicarFiltros() {
    const filtros = {
      nameBranch: this.nombresucursal || null
    };

    this.apiService.filter('positions', filtros).subscribe(result => {
      const agrupados: any = {};
      result.forEach((cargo: any) => {
        const catId = cargo.idBranch;
        if (!agrupados[catId]) {
          agrupados[catId] = {
            idBranch: catId,
            nameBranch: cargo.nameBranch
          };
        }
      });
      Error
      this.filteredsucursales = Object.values(agrupados);
    });
  }

  limpiarFiltros() {
    this.nombresucursal = '';
    this.filteredsucursales = [];

  }
}
