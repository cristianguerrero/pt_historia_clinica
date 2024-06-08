import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HistoriaClinicaService {

  private baseUrl = 'http://localhost:8080'; // URL base del servicio REST de Spring Boot

  constructor(private http: HttpClient) { }

  getHistoriasClinicas(): Observable<any> {
    // return this.http.get<any>(this.apiUrl);
    return this.http.get(`${this.baseUrl}/api/historias-clinicas`);
  }

  createHistoriaClinica(data: any): Observable<any> {
    // return this.http.post<any>(this.baseUrl, data);
    return this.http.post(`${this.baseUrl}/api/historias-clinicas`, data);
  }

  updateHistoriaClinica(id: number, data: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/api/historias-clinicas/${id}`, data);
  }

  deleteHistoriaClinica(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/api/historias-clinicas/${id}`);
  }

}
