import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatDTO } from '../interfaces/MessageDTO';

@Injectable({
  providedIn: 'root'
})
export class ChatsService {

  private baseUrl = 'http://localhost:9080/whatsapp/api/chats';

  email: string = '';

  constructor(private http: HttpClient) { }

  getChatsByEmail(): Observable<ChatDTO[]> {
    const url = `${this.baseUrl}/email/${this.email}`;
    return this.http.get<ChatDTO[]>(url);
  }

}
