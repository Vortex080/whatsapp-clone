import { ChangeDetectionStrategy, Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Router, RouterLink } from '@angular/router';
import { RegisterService } from '../../service/register.service';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'registro-form',
  imports: [CommonModule, ReactiveFormsModule, RouterLink, HttpClientModule],
  templateUrl: './form.component.html',
  styleUrl: './form.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [RegisterService]
})
export class FormComponent {
  registrationForm: FormGroup;
  submitted = false;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private registerService: RegisterService, private router: Router) {
    this.registrationForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      name: ['', Validators.required],
      pass: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.registrationForm.valid) {
      const usuarioData = this.registrationForm.value;
      this.registerService.registrarUsuario(usuarioData).subscribe(
        response => {
          console.log('Usuario registrado exitosamente:', response);
          this.router.navigate(['/']);
        },
        error => {
          if (error.status === 409) {
            // Aquí manejas el conflicto: el usuario o email ya existe
            console.error('Error 409: El usuario o email ya existe.');
            // Puedes asignar un mensaje de error para mostrar en la vista, por ejemplo:
            this.errorMessage = 'El usuario o email ya existe.';
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
    return this.registrationForm.controls;
  }
}
