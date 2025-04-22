import { trigger, transition, style, animate, state } from '@angular/animations';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';

@Component({
  selector: 'app-started',
  imports: [ CommonModule],
  templateUrl: './started.component.html',
  styleUrl: './started.component.css',
  animations: [
    trigger('iconExpand', [
      state('initial', style({ transform: 'scale(0.2)', opacity: 0 })),
      state('expanded', style({ transform: 'scale(1)', opacity: 1 })),
      transition('initial => expanded', animate('1000ms ease-out'))
    ]),
    trigger('fadeIn', [
      transition(':enter', [
        style({ opacity: 0, transform: 'scale(0.5)' }),
        animate('600ms 300ms ease-out', style({ opacity: 1, transform: 'scale(1)' }))
      ])
    ])
  ]
})
export class StartedComponent {
  iconState: 'initial' | 'expanded' = 'initial';
  showExtras = false;

  constructor(private router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.iconState = 'expanded';
      setTimeout(() => this.showExtras = true, 800); // ¡importante!
    }, 500);
  }
  

  selectRole(role: string) {
    console.log('Rol seleccionado:', role);
    if(role == 'admin'){
      this.router.navigate(['/dashboard'])
    }
    else{
      this.router.navigate(['/dashboard/resumen']);
    }
  }
}
