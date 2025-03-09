import { Routes } from "@angular/router";
import { WhatsappLayoutComponent } from "./layouts/WhatsappLayout/WhatsappLayout.component";
import { ChatsPageComponent } from "./pages/chats-page/chats-page.component";

export const whatappRoutes: Routes = [
    {
        path: '',
        component: WhatsappLayoutComponent,
        children: [
            {
                path: 'chats',
                component: ChatsPageComponent,
            },
            {
                path: '**',
                redirectTo: 'chats',
            },
        ],
    }
];

export default whatappRoutes;