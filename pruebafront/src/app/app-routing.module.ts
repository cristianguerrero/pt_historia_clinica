import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { HistoriaClinicaComponent } from './historia-clinica/historia-clinica.component';
import { DetalleHistoriaClinicaComponent } from './detalle-historia-clinica/detalle-historia-clinica.component';
import { MascotasComponent } from './mascotas/mascotas.component';

const routes: Routes = [
  { path: '', redirectTo: '/historia-clinica', pathMatch: 'full' },
  { path: 'historia-clinica', component: HistoriaClinicaComponent },
  { path: 'detalle-historia-clinica', component: DetalleHistoriaClinicaComponent },
  { path: 'mascotas', component: MascotasComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
