import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:9080/whatsapp/api/users/login'; // Cambia esta URL por la de tu endpoint

  constructor(private http: HttpClient) { }

  registrarUsuario(usuario: { email: string; pass: string; }): Observable<HttpResponse<User>> {
    // Asignar el valor por defecto 'hola' si photo no está definido
    const headers = { 'Content-Type': 'application/json' };
    return this.http.post<User>(this.apiUrl, usuario, { headers, observe: 'response' });
  }

}
