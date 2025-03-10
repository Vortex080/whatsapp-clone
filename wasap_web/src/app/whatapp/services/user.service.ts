import { Injectable } from '@angular/core';
import { User } from '../../shared/interface/user';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://localhost:9080/whatsapp/api/';

  constructor(private http: HttpClient) { }

  getUsersById(id: number): Observable<User[]> {
    const url = `${this.baseUrl}users/${id}`;
    return this.http.get<User[]>(url);
  }

  getUsersByCorreo(correo: string): Observable<User> {
    const url = `${this.baseUrl}users/email/${correo}`;
    return this.http.get<User>(url);
  }

  getUsers(): Observable<User[]> {
    const url = `${this.baseUrl}users`;
    return this.http.get<User[]>(url);
  }

}
