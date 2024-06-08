import { Component, OnInit } from '@angular/core';
import { HistoriaClinicaService } from '../services/historiaClinica/historia-clinica.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-historia-clinica',
  templateUrl: './historia-clinica.component.html',
  styleUrls: ['./historia-clinica.component.css']
})
export class HistoriaClinicaComponent implements OnInit {
  historiasClinicas: any[] = [];
  fechaCreacion: Date;
  mascotaId: number;
  responseMessage: string = '';
  errorMessage: string = '';
  cols: any[] = [];
  updateId: number;
  deleteId: number;

  constructor(private historiaClinicaService: HistoriaClinicaService) {
    this.fechaCreacion = new Date(); // Inicializar la variable con una fecha
    this.mascotaId = 0; // Inicializar la variable de mascotaId
    this.updateId = 0; // Inicializar la variable updateId
    this.deleteId = 0; // Inicializar la variable deleteId
  }

  ngOnInit(): void {
    this.getHistoriasClinicas();
    this.setupColumns();
  }

  getHistoriasClinicas(): void {
    this.historiaClinicaService.getHistoriasClinicas()
      .subscribe(historiasClinicas => this.historiasClinicas = historiasClinicas);
  }
  // configurar las columnas
  setupColumns(): void {
    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'fechaCreacion', header: 'Fecha Creacion' },
      { field: 'idMascota', header: 'Id Mascota' }
    ];
  }

  createHistoriaClinica(): void {
    const fechaEntero = this.fechaCreacion.getFullYear() * 10000 + (this.fechaCreacion.getMonth() + 1) * 100 + this.fechaCreacion.getDate();
    const newHistoria = {
      // fechaCreacion: 20210606,
      // mascota: {
      //   id: 1
      // }
      fechaCreacion: fechaEntero,
      mascota: {
        id: this.mascotaId
      }
    };
    this.historiaClinicaService.createHistoriaClinica(newHistoria)
      .subscribe(
        historia => {
          this.historiasClinicas.push(historia)
          this.responseMessage = 'Historia creada exitosamente';
        },
        error => {
          if (error instanceof HttpErrorResponse) {
            if (error.status === 500) {
              this.errorMessage = 'Ya existe historia clinica para esa mascota.';
            } else {
              this.errorMessage = 'Error al crear historia clinica.';
            }
          } else {
            this.errorMessage = 'Error al crear historia clinica.';
          }
        }
      );
  }

  updateHistoriaClinica(): void {
    if (this.updateId) {
      const fechaEntero = this.fechaCreacion.getFullYear() * 10000 + (this.fechaCreacion.getMonth() + 1) * 100 + this.fechaCreacion.getDate();
      const updatedHistoria = {
        fechaCreacion: fechaEntero,
        mascota: {
          id: this.mascotaId
        }
      };
      this.historiaClinicaService.updateHistoriaClinica(this.updateId, updatedHistoria)
        .subscribe(() => {this.getHistoriasClinicas()
        this.responseMessage = 'Historia actualizada exitosamente';});
    } else {
      console.error('No se recibió un ID.');
    }
  }

  deleteHistoriaClinica(): void {
    this.historiaClinicaService.deleteHistoriaClinica(this.deleteId)
      .subscribe(() => {this.getHistoriasClinicas()
        this.responseMessage = 'Historia eliminada exitosamente';});
  }

}
