import { Component, OnInit } from '@angular/core';
import { MascotasService } from '../services/mascotas/mascotas.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-mascotas',
  templateUrl: './mascotas.component.html',
  styleUrls: ['./mascotas.component.css']
})
export class MascotasComponent implements OnInit {
  mascotas: any[] = [];
  cols: any[] = [];

  nombre: string = '';
  raza: string = '';
  usuarioId: number = 0;
  sexo: string = '';

  responseMessage: string = '';
  errorMessage: string = '';

  constructor(private mascotasService: MascotasService) { }

  ngOnInit(): void {
    this.getMascotas();
    this.setupColumns();
  }

  getMascotas(): void {
    this.mascotasService.getMascotas()
      .subscribe(mascotas => this.mascotas = mascotas);
  }

  // configurar las columnas
  setupColumns(): void {
    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'nombre', header: 'Nombre' },
      { field: 'raza', header: 'Raza' },
      { field: 'sexo', header: 'Sexo' }
    ];
  }

  createMascotas(): void {
    const newMascota = {
      nombre: this.nombre,
      raza: this.raza,
      usuario: {
        id: this.usuarioId
      },
      sexo: this.sexo,
    };
    this.mascotasService.createMascotas(newMascota)
      .subscribe(
        mascota => {
          this.mascotas.push(mascota)
          this.responseMessage = 'Mascota creada exitosamente';
        },
        error => {
          if (error instanceof HttpErrorResponse) {
            if (error.status === 500) {
              this.errorMessage = 'Ya existe Mascota.';
            } else {
              this.errorMessage = 'Error al crear Mascota.';
            }
          } else {
            this.errorMessage = 'Error al crear Mascota.';
          }
        }
      );
  }

}
