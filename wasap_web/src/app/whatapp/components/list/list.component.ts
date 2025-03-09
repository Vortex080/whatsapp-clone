import { Component, Input } from '@angular/core';
import { ModalComponent } from "../modal/modal.component";
import { ChatDTO } from '../../interfaces/MessageDTO';


@Component({
  selector: 'whatapp-list',
  imports: [ModalComponent],
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent {
  @Input() chats: ChatDTO[] = [];
  @Input() mensaje: string = '';

  constructor() {
  }



  print() {
    console.log('List component:', this.chats);
  }
}

