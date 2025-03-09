import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../interface/user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiUrl = 'http://localhost:9080/whatsapp/api/users'; // Cambia esta URL por la de tu endpoint

  constructor(private http: HttpClient) { }

  registrarUsuario(usuario: { email: string; name: string; pass: string; photo?: string }): Observable<HttpResponse<User>> {
    // Asignar el valor por defecto 'hola' si photo no está definido
    const headers = { 'Content-Type': 'application/json' };
    if (!usuario.photo) {
      usuario.photo = 'http://www.w3.org/2000/svg';
    }
    return this.http.post<User>(this.apiUrl, usuario, { headers, observe: 'response' });
  }

}
