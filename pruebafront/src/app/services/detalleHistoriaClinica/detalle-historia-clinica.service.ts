import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DetalleHistoriaClinicaService {

  private baseUrl = 'http://localhost:8080'; // URL base del servicio REST de Spring Boot

  constructor(private http: HttpClient) { }

  getDetallesHistoriasClinicas(): Observable<any> {
    // return this.http.get<any>(this.apiUrl);
    return this.http.get(`${this.baseUrl}/api/detalles-historia-clinica`);
  }

  createDetalleHistoriaClinica(data: any): Observable<any> {
    // return this.http.post<any>(this.baseUrl, data);
    return this.http.post(`${this.baseUrl}/api/detalles-historia-clinica`, data);
  }

  updateDetalleHistoriaClinica(id: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/detalles-historia-clinica/${id}`, data);
  }

  deleteDetalleHistoriaClinica(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/api/detalles-historia-clinica/${id}`);
  }

}
