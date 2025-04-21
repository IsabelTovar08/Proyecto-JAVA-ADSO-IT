import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatMenuModule } from '@angular/material/menu';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { ApiService } from '../../../../../services/api.service';
import { FormServiceComponent } from '../../service/form-service/form-service.component';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { FormCategoryComponent } from "../form-category/form-category.component";

@Component({
  selector: 'app-list-category',
  standalone: true,
  imports: [
    MatCardModule,
    CommonModule,
    MatInputModule,
    MatIconModule,
    MatFormFieldModule,
    MatSelectModule,
    MatMenuModule,
    FormServiceComponent,
    MatButtonModule,
    FormsModule,
    MatOptionModule,
    FormCategoryComponent
  ],
  templateUrl: './list-category.component.html',
  styleUrl: './list-category.component.css'
})
export class ListCategoryComponent {
  categories: any[] = [];
  filteredCategories: any[] = [];

  nombreServicio: string = '';
  categoriaId: number | null = null;
  precioMin: number | null = null;
  precioMax: number | null = null;

  mostrarFormularioServicio = false;
  mostrarFormularioCategoria = false;

  servicioEditando: any = null;
  categoriaActual: any = null;
  categoriaEditando: any = null;

  constructor(private apiService: ApiService, private router: Router) {
    this.cargarCategorias();
  }

  cargarCategorias() {
    this.apiService.getAll('categories').subscribe(category => {
      this.categories = category;
    });
  }

  aplicarFiltros() {
    const filtros = {
      name: this.nombreServicio || null,
      categoryId: this.categoriaId || null,
      minPrice: this.precioMin || null,
      maxPrice: this.precioMax || null
    };

    this.apiService.filter('services', filtros).subscribe(result => {
      const agrupados: any = {};
      result.forEach((servicio: any) => {
        const catId = servicio.idCategory;
        if (!agrupados[catId]) {
          agrupados[catId] = {
            idCategory: catId,
            nameCategory: servicio.nameCategory,
            services: [],
          };
        }
        agrupados[catId].services.push(servicio);
      });
      this.filteredCategories = Object.values(agrupados);
    });
  }

  limpiarFiltros() {
    this.nombreServicio = '';
    this.categoriaId = null;
    this.precioMin = null;
    this.precioMax = null;
    this.filteredCategories = [];
  }

  abrirFormularioNuevoServicio(categoria: any, servicio: any = null) {
    // Cierra el formulario de categoría si está abierto
    this.mostrarFormularioCategoria = false;
    this.categoriaEditando = null;
  
    // Abre el formulario de servicio
    if (this.mostrarFormularioServicio) {
      this.cerrarFormularioServicio();
      setTimeout(() => {
        this.servicioEditando = servicio;
        this.categoriaActual = categoria;
        this.mostrarFormularioServicio = true;
      });
    } else {
      this.servicioEditando = servicio;
      this.categoriaActual = categoria;
      this.mostrarFormularioServicio = true;
    }
  }
  

  cerrarFormularioServicio(recargar: boolean = false) {
    this.mostrarFormularioServicio = false;
    this.servicioEditando = null;
    this.categoriaActual = null;
    if (recargar) this.cargarCategorias();
  }

  abrirFormularioCategoria(categoria: any = null) {
    // Cierra el formulario de servicio si está abierto
    this.mostrarFormularioServicio = false;
    this.servicioEditando = null;
    this.categoriaActual = null;
  
    // Abre el formulario de categoría
    this.categoriaEditando = categoria;
    this.mostrarFormularioCategoria = true;
  }
  

  cerrarFormularioCategoria(recargar: boolean = false) {
    this.mostrarFormularioCategoria = false;
    this.categoriaEditando = null;
    if (recargar) this.cargarCategorias();
  }

  eliminarCategoria(categoria: any) {
    Swal.fire({
      title: '¿Eliminar categoría?',
      text: `Esta acción eliminará la categoría "${categoria.nameCategory}" y sus servicios.`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar',
    }).then(result => {
      if (result.isConfirmed) {
        this.apiService.delete('categories', categoria.idCategory).subscribe({
          next: () => {
            this.cargarCategorias();
            Swal.fire('Eliminado', 'La categoría ha sido eliminada.', 'success');
          },
          error: (error) => {
            if (error.status === 500 || error.status === 400) {
              Swal.fire({
                title: 'No se puede eliminar',
                text: 'Debes eliminar primero los servicios asociados a esta categoría.',
                icon: 'error'
              });
            } else {
              Swal.fire('Error', 'Ocurrió un error inesperado al eliminar.', 'error');
            }
          }
        });
      }
    });
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
    }).then((result) => {
      if (result.isConfirmed) {
        this.apiService.delete('services', servicio.idService).subscribe(() => {
          this.cargarCategorias();
          Swal.fire('¡Eliminado!', 'El servicio ha sido eliminado correctamente.', 'success');
        });
      }
    });
  }
}
