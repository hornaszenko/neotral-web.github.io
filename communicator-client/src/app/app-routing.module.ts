import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegisterComponent} from "./register/register.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuardService} from "./services/AuthGuardService";
import {GroupsModalComponent} from "./groups-modal/groups-modal.component";
import {ConversationsComponent} from "./conversations/conversations.component";

const routes: Routes = [
  {path: 'rejestracja', component: RegisterComponent},
  {path: 'login', component: LoginComponent},
  {
    path: '',
    component: ConversationsComponent,
    canActivate: [AuthGuardService]
  },
  {path: 'add-group', component: GroupsModalComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
