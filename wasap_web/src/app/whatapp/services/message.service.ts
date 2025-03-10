import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Message } from '../interfaces/MessageDTO';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MessageService {
  getMessagesByEmail() {
    throw new Error('Method not implemented.');
  }

  private apiUrl = 'http://localhost:9080/whatsapp/api/messages'; // Cambia esta URL por la de tu endpoint

  constructor(private http: HttpClient) { }

  registrarMessage(message: {
    idAuthor: number;
    text: string;
    date: string;
    dest: number;
  }): Observable<HttpResponse<Message>> {

    const headers = { 'Content-Type': 'application/json' };

    return this.http.post<Message>(this.apiUrl, message, { headers, observe: 'response' });
  }

  getMsgByIds(id: string): Observable<Message[]> {
    const url = `${this.apiUrl}/conversation/${id}`;
    return this.http.get<Message[]>(url);
  }
}
