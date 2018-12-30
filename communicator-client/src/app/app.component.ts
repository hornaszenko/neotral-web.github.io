import {Component, OnInit} from '@angular/core';
import {AuthService} from "./services/AuthService";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'communicator-client';

  constructor(private authService: AuthService, private router: Router) {
  }

  /**
   * Call on init. Checks if user is authenticated. If no, then display login page.
   */
  ngOnInit(): void {
    if (!this.authService.isAuthenticated()) {
      this.router.navigateByUrl("/login");
    }
  }
}

