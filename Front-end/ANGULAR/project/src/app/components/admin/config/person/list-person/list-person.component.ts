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
import { FormPersonComponent } from '../form-person/form-person.component';
import { FormEmployeesComponent } from '../../empolyees/form-employees/form-employees.component';
import { FormClientComponent } from '../../clients/form-client/form-client.component';

@Component({
  selector: 'app-list-person',
  imports: [MatCardModule, MatTableModule, MatIconModule, MatButtonModule, MatFormFieldModule, FormsModule, MatInputModule],
  templateUrl: './list-person.component.html',
  styleUrl: './list-person.component.css'
})
export class ListPersonComponent {
  personas: [] = [];
  personaSeleccionada?: any;

  empleados: [] = [];
  empleadoSeleccionado?: any;

  clientes: [] = [];
  clienteSeleccionado?: any;

  nombrePersona: String = '';
  filteredpersonas: any[] = [];


  displayedColumns: string[] = ['idPerson', 'namesPerson', 'lastNamePerson', 'documentPerson', 'phonePerson', 'emailPerson', 'addressPerson', 'municipality_name', 'actions'];
  displayedColumnsEmployees: string[] = ['idEmployee', 'name_Person', 'name_position', 'salaryEmployee', 'actions'];
  displayedColumnsClients: string[] = ['idClient', 'person_name', 'registrationDateClient',  'actions'];

  constructor(private dialog: MatDialog, private apiService: ApiService) { }

  ngOnInit(): void {
    this.cargarpersonas();
    this.cargarempleados();
    this.cargarclientes();
  }
  cargarpersonas() {
    this.apiService.getAll('person').subscribe(personas => {
      this.personas = personas;
      console.log(personas)
    })
  }

  cargarempleados() {
    this.apiService.getAll('employees').subscribe(empleados => {
      this.empleados = empleados;
      console.log(empleados)
    })
  }

  cargarclientes() {
    this.apiService.getAll('clients').subscribe(clientes => {
      this.clientes = clientes;
      console.log(clientes)
    })
  }
  openFormCreate(): void {
    const dialogRef = this.dialog.open(FormPersonComponent, {
      width: '400px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarpersonas(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  openFormCreateEmplyee(): void {
    const dialogRef = this.dialog.open(FormEmployeesComponent, {
      width: '400px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarempleados(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  openFormCreateClient(): void {
    const dialogRef = this.dialog.open(FormClientComponent, {
      width: '400px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarclientes(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }

  editperson(person: any): void {
    // Abre el formulario con los datos del person
    const dialogRef = this.dialog.open(FormPersonComponent, {
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

  editemployee(employee: any): void {
    // Abre el formulario con los datos del employee
    const dialogRef = this.dialog.open(FormEmployeesComponent, {
      width: '400px',
      data: this.empleadoSeleccionado
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result === 'reload') {
        this.cargarempleados(); // Recarga la tabla
        console.log("cargar");
      }
    });
  }


  deleteperson(person: any): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Quieres eliminar el person: ${person.namePerson}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#765dfb',
      cancelButtonColor: '#d5d7f9',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.apiService.delete('person', person.idPerson).subscribe(() => {
          this.cargarpersonas();
        })
      }
    });
  }

  deleteemployee(employee: any): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Quieres eliminar el employee: ${employee.name_Person}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#765dfb',
      cancelButtonColor: '#d5d7f9',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.apiService.delete('employees', employee.idEmployee).subscribe(() => {
          this.cargarempleados();
        })
      }
    });
  }
  deleteclient(client: any): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `¿Quieres eliminar el client: ${client.name_Person}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#765dfb',
      cancelButtonColor: '#d5d7f9',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.apiService.delete('clients', client.idClient).subscribe(() => {
          this.cargarempleados();
        })
      }
    });
  }
  aplicarFiltros() {
    const filtros = {
      namePerson: this.nombrePersona || null
    };

    this.apiService.filter('Persons', filtros).subscribe(result => {
      const agrupados: any = {};
      result.forEach((person: any) => {
        const catId = person.idPerson;
        if (!agrupados[catId]) {
          agrupados[catId] = {
            idPerson: catId,
            namePerson: person.namePerson
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
