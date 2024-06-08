import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MascotasService {

  private baseUrl = 'http://localhost:8080'; // URL base del servicio REST de Spring Boot

  constructor(
    private http: HttpClient
  ) { }

  getMascotas(): Observable<any> {
    // return this.http.get<any>(this.apiUrl);
    return this.http.get(`${this.baseUrl}/api/mascotas`);
  }

  createMascotas(data: any): Observable<any> {
    // return this.http.post<any>(this.baseUrl, data);
    return this.http.post(`${this.baseUrl}/api/mascotas`, data);
  }

}
