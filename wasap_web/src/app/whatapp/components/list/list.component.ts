import { Component, input, Input, output } from '@angular/core';
import { ModalComponent } from "../modal/modal.component";
import { ChatDTO } from '../../interfaces/MessageDTO';
import { User } from '../../../shared/interface/user';
import { UserService } from '../../services/user.service';


@Component({
  selector: 'whatapp-list',
  imports: [ModalComponent],
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {
  chats = input<ChatDTO[]>();
  mensaje = input<string>();
  chatSelected = output<number>();
  users: User[] = [];
  ownuser = input<User>();

  constructor(private userService: UserService) {
    this.userService.getUsers().subscribe({
      next: (response) => {
        this.users = response;
      },
      error: (error) => {
        console.error('Error fetching chats:', error);
      },
    });


  }




  print() {
    console.log('List component:', this.chats);
  }


  enviarPadre(id: number) {
    console.log(id);
    this.chatSelected.emit(id);
  }
}

