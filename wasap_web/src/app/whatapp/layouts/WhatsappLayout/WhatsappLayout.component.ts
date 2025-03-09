import { ChangeDetectionStrategy, Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-whatsapp-layout',
  imports: [RouterOutlet],
  templateUrl: './WhatsappLayout.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class WhatsappLayoutComponent { }
