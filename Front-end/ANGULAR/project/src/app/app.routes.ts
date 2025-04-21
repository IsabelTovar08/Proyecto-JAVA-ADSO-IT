import { Routes } from '@angular/router';
import { StartedComponent } from './pages/started/started/started.component';
import { DashboardComponent } from './pages/dashboard/dashboard/dashboard.component';
import { ConfigComponent } from './components/admin/config/config.component';
import { CabelloComponent } from './components/admin/sections/cabello/cabello.component';
import { ListCategoryComponent } from './components/admin/config/category/list-category/list-category.component';
import { ListPositionComponent } from './components/admin/config/position/list-position/list-position.component';
import { ListPersonComponent } from './components/admin/config/person/list-person/list-person.component';
import { ListBranchComponent } from './components/admin/config/branch/list-branch/list-branch.component';

export const routes: Routes = [
    {path : '', component : StartedComponent},
    {path : 'dashboard', component : DashboardComponent,
        children: [
            {path : 'category', component : ListCategoryComponent},
            {path : 'position', component : ListPositionComponent},
            {path : 'person', component : ListPersonComponent},
            {path : 'branch', component : ListBranchComponent},


            { path: 'cabello', component: CabelloComponent },


            // { path: 'unas', component: UnasComponent },
            // { path: 'cejas', component: CejasComponent },
            // { path: 'pestanas', component: PestanasComponent },
            // { path: 'maquillaje', component: MaquillajeComponent },
            { path: '', redirectTo: 'cabello', pathMatch: 'full' }
          ]
    },
    
    // { path: 'dashboard/cabello', component: CabelloComponent }

];
