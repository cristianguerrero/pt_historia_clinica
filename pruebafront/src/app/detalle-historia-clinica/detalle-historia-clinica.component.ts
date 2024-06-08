import { Component, OnInit } from '@angular/core';
import { DetalleHistoriaClinicaService } from '../services/detalleHistoriaClinica/detalle-historia-clinica.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-detalle-historia-clinica',
  templateUrl: './detalle-historia-clinica.component.html',
  styleUrls: ['./detalle-historia-clinica.component.css']
})
export class DetalleHistoriaClinicaComponent implements OnInit {
  detallesHistoriasClinicas: any[] = [];
  temperatura: string = '';
  peso: number = 0;
  frecuenciaCardiaca: number = 0;
  frecuenciaRespiratoria: number = 0;
  fechaHora: Date;
  alimentacion: string = '';
  habitad: string = '';
  observacion: string = '';
  colaboradorId: number;
  historiaClinicaId: number;

  responseMessage: string = '';
  errorMessage: string = '';
  cols: any[] = [];
  updateId: number;
  deleteId: number;

  constructor(private detalleHistoriaClinicaService: DetalleHistoriaClinicaService) {
    this.fechaHora = new Date(); // Inicializar la variable con una fecha
    this.colaboradorId = 0; // Inicializar la variable de colaboradorId
    this.historiaClinicaId = 0; // Inicializar la variable de historiaClinicaId
    this.updateId = 0; // Inicializar la variable updateId
    this.deleteId = 0; // Inicializar la variable deleteId
  }

  ngOnInit(): void {
    this.getDetallesHistoriasClinicas();
    this.setupColumns();
  }

  getDetallesHistoriasClinicas(): void {
    this.detalleHistoriaClinicaService.getDetallesHistoriasClinicas()
      .subscribe(detallesHistoriasClinicas => this.detallesHistoriasClinicas = detallesHistoriasClinicas);
  }

  // getDetallesHistoriasClinicas(): void {
  //   this.detalleHistoriaClinicaService.getDetallesHistoriasClinicas()
  //     .subscribe((detallesHistoriasClinicas: DetalleHistoriaClinica[]) => {
  //       this.detallesHistoriasClinicas = detallesHistoriasClinicas.map((detalle: DetalleHistoriaClinica) => {
  //         return {
  //           ...detalle,
  //           fechaHora: this.formatFechaHora(detalle.fechaHora)
  //         };
  //       });
  //     });
  // }

  // formatFechaHora(fechaHora: string): string {
  //   const date = new Date(fechaHora);
  //   const formattedDate = date.toLocaleDateString();
  //   const formattedTime = date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  //   return `${formattedDate} ${formattedTime}`;
  // }

  // configurar las columnas
  setupColumns(): void {
    this.cols = [
      { field: 'id', header: 'ID' },
      { field: 'temperatura', header: 'Temperatura' },
      { field: 'peso', header: 'Peso' },
      { field: 'frecuenciaCardiaca', header: 'Frecuencia Cardiaca' },
      { field: 'frecuenciaRespiratoria', header: 'Frecuencia Respiratoria' },
      { field: 'fechaHora', header: 'Fecha Hora' },
      { field: 'alimentacion', header: 'Alimentacion' },
      { field: 'habitad', header: 'Habitad' },
      { field: 'observacion', header: 'Observacion' },
      { field: 'colaboradorId', header: 'Colaborador Id' },
      { field: 'historiaClinicaId', header: 'HistoriaClinica Id' }
    ];
  }

  createDetalleHistoriaClinica(): void {
    const newDetalleHistoria = {
      temperatura: this.temperatura,
      peso: this.peso,
      frecuenciaCardiaca: this.frecuenciaCardiaca,
      frecuenciaRespiratoria: this.frecuenciaRespiratoria,
      fechaHora: this.fechaHora,
      alimentacion: this.alimentacion,
      habitad: this.habitad,
      observacion: this.observacion,
      colaborador: {
        id: this.colaboradorId
      },
      historiaClinica: {
        id: this.historiaClinicaId
      }
    };
    this.detalleHistoriaClinicaService.createDetalleHistoriaClinica(newDetalleHistoria)
      .subscribe(
        detalleHistoria => {
          this.detallesHistoriasClinicas.push(detalleHistoria)
          this.responseMessage = 'Detalle de Historia creado exitosamente';
        },
        error => {
          if (error instanceof HttpErrorResponse) {
            if (error.status === 500) {
              this.errorMessage = 'Ya existe detalle de historia clinica.';
            } else {
              this.errorMessage = 'Error al crear detalle de historia.';
            }
          } else {
            this.errorMessage = 'Error al crear detalle de historia.';
          }
        }
      );
  }

  updateDetalleHistoriaClinica(): void {
    if (this.updateId) {
      const updatedDetalleHistoria = {
        temperatura: this.temperatura,
        peso: this.peso,
        frecuenciaCardiaca: this.frecuenciaCardiaca,
        frecuenciaRespiratoria: this.frecuenciaRespiratoria,
        fechaHora: this.fechaHora,
        alimentacion: this.alimentacion,
        habitad: this.habitad,
        observacion: this.observacion,
        colaborador: {
          id: this.colaboradorId
        },
        historiaClinica: {
          id: this.historiaClinicaId
        }
      };
      this.detalleHistoriaClinicaService.updateDetalleHistoriaClinica(this.updateId, updatedDetalleHistoria)
        .subscribe(() => {this.getDetallesHistoriasClinicas()
        this.responseMessage = 'Detalle de Historia actualizado exitosamente';});
    } else {
      console.error('No se recibió un ID.');
    }
  }

  deleteDetalleHistoriaClinica(): void {
    this.detalleHistoriaClinicaService.deleteDetalleHistoriaClinica(this.deleteId)
      .subscribe(() => {this.getDetallesHistoriasClinicas()
        this.responseMessage = 'Detalle de Historia eliminada exitosamente';});
  }

}
