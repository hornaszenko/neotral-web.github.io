import {Component, OnInit} from '@angular/core';
import {AuthService} from "../services/AuthService";
import {UserModel} from "../models/UserModel";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  userModel: UserModel = new UserModel();

  constructor(private authService: AuthService) { }
  /**
   * Performs registration process.
   */
  register() {
    let data = JSON.stringify(this.userModel);
    this.authService.register(data);
  }
}
