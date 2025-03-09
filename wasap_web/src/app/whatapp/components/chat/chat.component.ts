import { CommonModule } from '@angular/common';
import {Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

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
  messages = [
    { text: '¡Hola!', sent: true },
    { text: '¡Hola! ¿Cómo estás?', sent: false },
    // Puedes añadir más mensajes aquí para probar
  ];

  newMessage: string = '';
  binding: string = '';
  sendMessage() {
    if (this.newMessage.trim()) {
      this.messages.push({ text: this.newMessage, sent: true });
      this.newMessage = '';
      // Puedes añadir aquí la lógica para enviar el mensaje al servidor
    }
  }
}
