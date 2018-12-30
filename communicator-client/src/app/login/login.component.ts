import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/AuthService";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user = {username: '', password: ''};

  constructor(private authService: AuthService) {
  }

  ngOnInit() {
  }

  /**
   * Send login form data to server for authentication.
   */
  login() {
    let body = new HttpParams()
      .set('username', this.user.username)
      .set('password', this.user.password);

    this.authService.login(body);
  }

  /**
   * Checks if login form data is valid.
   */
  isValid() {
    if (this.user.username == null || this.user.username.length < 6) {
      return false;
    }

    if (this.user.password == null || this.user.password.length < 6) {
      return false;
    }

    return true;
  }

}
