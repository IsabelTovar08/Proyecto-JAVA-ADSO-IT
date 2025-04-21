import { Component } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { ApiService } from '../../../services/api.service';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-toolbar',
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatMenuModule, CommonModule, ReactiveFormsModule, MatInputModule, FormsModule ],
  templateUrl: './toolbar.component.html',
  styleUrl: './toolbar.component.css'
})
export class ToolbarComponent {
  entidadSeleccionada: string = 'services'; // por defecto
  query: string = '';
  resultados: any[] = [];
  constructor(private apiService: ApiService) {}

  // buscar() {
  //   if (!this.query || !this.entidadSeleccionada) return;

  //   this.apiService.search(this.entidadSeleccionada, this.query).subscribe({
  //     next: res => this.resultados = res,
  //     error: err => console.error(err)
  //   });
  // }
}
