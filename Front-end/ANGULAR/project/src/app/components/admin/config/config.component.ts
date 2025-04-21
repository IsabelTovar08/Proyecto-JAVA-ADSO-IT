import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { ApiService } from '../../../services/api.service';
import { FormServiceComponent } from "./service/form-service/form-service.component";
import { MatFormField } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-config',
  imports: [MatCardModule, CommonModule, MatIconModule, MatMenuModule, FormServiceComponent, MatFormField, ReactiveFormsModule, MatButtonModule],
  templateUrl: './config.component.html',
  styleUrl: './config.component.css'
})
export class ConfigComponent {
  sections: { name: string; categories: string[] }[] = [];
  categories!: any[]
  categoriaSeleccionada: number | null = null;
  servicioSeleccionado: string | null = null;

  // Para el formulario de servicio
  mostrarFormularioServicio = false;
  servicioEditando: any = null;
  categoriaActual: any = null;

  constructor(private apiService: ApiService, private router: Router) {
    this.cargarCategorias();

  }

  ngOnInit(): void {
    // this.cargarCategorias();
  }

  cargarCategorias() {
    this.apiService.getAll('categories').subscribe(category => {
      this.categories = category;
      console.log(this.categories)

    })
  }

  // Funciones para abrir los formularios y manejar las categorías
  abrirFormularioNuevoServicio(categoria: any, servicio: any = null) {

    if (this.mostrarFormularioServicio) {
      this.cerrarFormularioServicio();
      setTimeout(() => {
        this.servicioEditando = servicio ? { ...servicio } : {};
        this.categoriaActual = categoria;
        this.mostrarFormularioServicio = true;
        console.log("cerrandi");
      });
    } else {
      this.servicioEditando = servicio ? { ...servicio } : {};
      this.categoriaActual = categoria;
      this.mostrarFormularioServicio = true;
    }
  }




  cerrarFormularioServicio(recargar: boolean = false) {
    this.mostrarFormularioServicio = false;
    this.servicioEditando = null;
    this.categoriaActual = null;

    if (recargar) {
      this.cargarCategorias(); // Recarga si se guardó algo
    }
  }

  editarCategoria(categoria: any) {
    console.log('Editar categoría', categoria);
    // Lógica para editar la categoría
  }

  eliminarCategoria(categoria: any) {
    console.log('Eliminar categoría', categoria);
    // Lógica para eliminar la categoría
  }

  eliminarServicio(servicio: any) {
    Swal.fire({
      title: `¿Estás seguro?`,
      html: `Vas a eliminar el servicio <strong>"${servicio.nameService}"</strong> de la categoría <strong>${servicio.nameCategory}</strong>.`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#3085d6',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
      reverseButtons: true,
      customClass: {
        title: 'swal2-title-custom',
        htmlContainer: 'swal2-html-custom',
      }
    }).then((result) => {
      if (result.isConfirmed) {
        // Aquí llamas a tu método de eliminación
        this.apiService.delete('services', servicio.idService).subscribe(() => {
          this.cargarCategorias();
        })
        Swal.fire({
          title: '¡Eliminado!',
          text: 'El servicio ha sido eliminado correctamente.',
          icon: 'success',
          timer: 2000,
          showConfirmButton: false,
        });
      }
    });

  }
}
