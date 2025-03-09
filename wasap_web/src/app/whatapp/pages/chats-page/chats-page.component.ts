import { Component, OnInit } from '@angular/core';
import { ListComponent } from "../../components/list/list.component";
import { ChatComponent } from "../../components/chat/chat.component";
import { ChatsService } from '../../services/chats.service';
import { UserService } from '../../services/user.service';
import { ChatDTO } from '../../interfaces/MessageDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'chats-page',
  imports: [ListComponent, ChatComponent, CommonModule],
  templateUrl: './chats-page.component.html',
  styleUrl: './chats-page.component.css'
})
export class ChatsPageComponent implements OnInit {
  chats: ChatDTO[] = [];
  dataLoaded = false; // Flag para indicar que se cargaron los datos

  mensa: string = 'esto es un mensaje';

  constructor(private chatService: ChatsService, private userService: UserService) { }
  ngOnInit(): void {
    this.chatService.getChatsByEmail().subscribe({
      next: (response) => {
        this.chats = response;
        console.log(this.chats);
      },
      error: (error) => {
        console.error('Error fetching chats:', error);
        this.dataLoaded = true; // Aunque ocurra un error, se puede marcar como completado
      },
    });
  }
}
