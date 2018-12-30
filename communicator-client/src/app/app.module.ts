import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavigationComponent} from './navigation/navigation.component';
import {RegisterComponent} from './register/register.component';
import {AuthService} from "./services/AuthService";
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {LoginComponent} from './login/login.component';
import {JWTInterceptor} from "./JWTInterceptor";
import {HomeComponent} from './home/home.component';
import {JwtHelperService, JwtModule} from "@auth0/angular-jwt";
import {AuthGuardService} from "./services/AuthGuardService";
import {ConversationsComponent} from './conversations/conversations.component';
import {GroupsModalComponent} from './groups-modal/groups-modal.component';
import {NgbModalModule, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {MessagesComponent} from './messages/messages.component';
import {CreateGroupComponent} from './create-group/create-group.component';

export function getToken(): string {
  return localStorage.getItem('token');
}

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    RegisterComponent,
    LoginComponent,
    HomeComponent,
    ConversationsComponent,
    GroupsModalComponent,
    MessagesComponent,
    CreateGroupComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    NgbModalModule,
    JwtModule.forRoot({
      config: {
        tokenGetter: getToken
      }
    })
  ],
  entryComponents: [
    CreateGroupComponent
  ],
  providers: [AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JWTInterceptor,
      multi: true
    },
    JwtHelperService,
    AuthGuardService,],
  bootstrap: [AppComponent],
})
export class AppModule {
}
