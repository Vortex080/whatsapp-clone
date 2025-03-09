import { ChangeDetectionStrategy, Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { LoginService } from '../../service/login.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ChatsService } from '../../../whatapp/services/chats.service';

@Component({
  selector: 'app-home-page',
  imports: [CommonModule, RouterLink, FormsModule, HttpClientModule, ReactiveFormsModule],
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [LoginService]
})
export class HomePageComponent {

  loginForm: FormGroup;
  submitted = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private loginservice: LoginService, private router: Router, private chatService: ChatsService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      pass: ['', [Validators.required]]
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.loginForm.valid) {
      const usuarioData = this.loginForm.value;
      this.loginservice.registrarUsuario(usuarioData).subscribe(
        response => {
          this.router.navigate(['/whatsapp/chats']);
          this.chatService.email = usuarioData.email.replace(/"/g, '');
          sessionStorage.setItem('user', JSON.stringify(usuarioData.email));
        },
        error => {
          if (error.status === 401) {
            // Aquí manejas el conflicto: el usuario o email ya existe
            console.error('La contraseña o email no es correcto.');
            // Puedes asignar un mensaje de error para mostrar en la vista, por ejemplo:
            this.errorMessage = 'La contraseña o email no es correcto.';
          } else {
            // Manejar otros posibles errores
            console.error('Error inesperado', error);
            this.errorMessage = 'Ha ocurrido un error. Inténtalo de nuevo más tarde.';
          }
        }
      );
    }
  }

  get f() {
    return this.loginForm.controls;
  }
}
