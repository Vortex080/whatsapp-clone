import { Component, OnInit, signal } from '@angular/core';
import { ListComponent } from "../../components/list/list.component";
import { ChatComponent } from "../../components/chat/chat.component";
import { ChatsService } from '../../services/chats.service';
import { UserService } from '../../services/user.service';
import { ChatDTO, Message } from '../../interfaces/MessageDTO';
import { CommonModule } from '@angular/common';
import { User } from '../../../shared/interface/user';
import { MessageService } from '../../services/message.service';

@Component({
  selector: 'chats-page',
  imports: [ListComponent, ChatComponent, CommonModule],
  templateUrl: './chats-page.component.html',
  styleUrl: './chats-page.component.css'
})
export class ChatsPageComponent {
  chats = signal<ChatDTO[]>([]);
  dataLoaded = false; // Flag para indicar que se cargaron los datos
  email = sessionStorage.getItem('user')!.replace(/"/g, '') || '';
  chatselected = signal<ChatDTO | undefined>(undefined);
  ownuser = signal<User | undefined>(undefined);
  chatid = signal<number | undefined>(undefined);
  get ids(): string | undefined {
    const chatId = this.chatid();
    const userId = this.ownuser()?.id;
    return chatId && userId ? String(chatId) + ',' + String(userId) : undefined;
  }


  chatMessages = signal<Message[]>([]);


  constructor(private userService: UserService, private messageService: MessageService) {


    this.userService.getUsersByCorreo(sessionStorage.getItem('user')!.replace(/"/g, '')).subscribe({
      next: (response) => {
        this.ownuser.set(response);
        this.cargarMensajes(); // Llamar después de definir usuario
      },
      error: (error) => {
        console.error('Error fetching chats:', error);
      },
    });
  }

  cargarMensajes(): void {
    if (this.ids) {
      this.messageService.getMsgByIds(this.ids).subscribe({
        next: (response: any) => {
          this.chatMessages.set(response);
        },
        error: (error: any) => {
          if (error.status !== 404) { // Solo mostrar errores diferentes de 401
            console.error('Error fetching messages:', error);
          } else {
            // Opcional: aquí puedes manejar específicamente el error 401
            console.info('No hay mensajes disponibles.');
            this.chatMessages.set([]); // por ejemplo, vaciar mensajes
          }
        },
      });
    }
  }



  selectedChat(id: number) {
    this.chatid.set(id);

    this.cargarMensajes(); // Llamar después de definir chat
  }




}
