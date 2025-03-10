import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatDTO } from '../interfaces/MessageDTO';

@Injectable({
  providedIn: 'root'
})
export class ChatsService {

  private baseUrl = 'http://localhost:9080/whatsapp/api/chats';

  email: string = sessionStorage.getItem('user')|| '';

  constructor(private http: HttpClient) { }

  getChatsByEmail(): Observable<ChatDTO[]> {
    const url = `${this.baseUrl}/email/${this.email.replace(/"/g, '')}`;
    return this.http.get<ChatDTO[]>(url);
  }

  registrarChat(chat: {
    users:    number[];
  }): Observable<HttpResponse<ChatDTO>> {

    const headers = { 'Content-Type': 'application/json' };

    return this.http.post<ChatDTO>(this.baseUrl, chat, { headers, observe: 'response' });
  }

}
