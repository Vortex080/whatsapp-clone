import { Routes } from '@angular/router';
import { HomePageComponent } from './shared/pages/home-page/home-page.component';
import { RegisterPageComponent } from './shared/pages/register-page/register-page.component';

export const routes: Routes = [
    {
        path: '',
        component: HomePageComponent,
    },
    {
        path: 'register',
        component: RegisterPageComponent,
    },
    {
        path: 'whatsapp',
        loadChildren: () => import('./whatapp/whatsapp.routes'),
    },
    {
        path: '**',
        redirectTo: '',
    },

];
