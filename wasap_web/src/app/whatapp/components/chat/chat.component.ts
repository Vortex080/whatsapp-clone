import { CommonModule } from '@angular/common';
import { Component, effect, input, Input, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatDTO, Message } from '../../interfaces/MessageDTO';
import { User } from '../../../shared/interface/user';
import { MessageService } from '../../services/message.service';
import { ChatsService } from '../../services/chats.service';

@Component({
  selector: 'whatsapp-chat',
  imports: [
    CommonModule,
    FormsModule,
  ],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css',
})
export class ChatComponent {

  ownuser = input<User>();

  chatselectedInput = input<number | undefined>(undefined);

  chatmessagesInput = input<Message[]>();

  chatselected = signal<number | undefined>(undefined);

  chatmessages = signal<Message[]>([]);


  get ids(): string | undefined {
    const chatId = this.chatselected();
    const userId = this.ownuser()?.id;
    return chatId && userId ? String(chatId) + ',' + String(userId) : undefined;
  }
  constructor(private messageService: MessageService, private chatService: ChatsService) {
    effect(() => {
      const mensajes = this.chatmessagesInput() ?? [];
      this.chatmessages.set([...mensajes]);
    });
  }

  intervalId: any;

  timeoutSubscription: any;

  ngOnInit(): void {
    this.cargarMensajesConTimeout();
  }

  cargarMensajesConTimeout(): void {
    this.cargarMensajes(); // primera llamada inmediatamente
    this.timeoutSubscription = setTimeout(() => {
      this.cargarMensajesConTimeout();
    }, 5000);
  }

  ngOnDestroy(): void {
    clearTimeout(this.timeoutSubscription);
  }


  intervalSubscription: any;


  text: string = '';
  enviarMensaje() {
    this.messageService.registrarMessage({
      idAuthor: this.ownuser()!.id,
      text: this.text,
      date: new Date().toISOString(),
      dest: this.chatselected() ?? 0
    }).subscribe({
      next: (response) => {
        console.log(response);
      },
      error: (error) => {
        console.error('Error fetching chats:', error);
      },
    });
    this.text = '';
  }

  cargarMensajes(): void {
    if (this.ids) {
      this.messageService.getMsgByIds(this.ids).subscribe({
        next: (response: any) => {
          this.chatmessages.set(response);
        },
        error: (error: any) => {
          if (error.status !== 404) { // Solo mostrar errores diferentes de 401
            console.error('Error fetching messages:', error);
          } else {
            // Opcional: aquí puedes manejar específicamente el error 401
            console.info('No hay mensajes disponibles.');
            this.chatmessages.set([]); // por ejemplo, vaciar mensajes
          }
        },
      });
    }
  }

}
