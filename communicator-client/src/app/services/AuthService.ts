import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";

const headers = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/json'}
  )
};

const formHeader = {
  headers: new HttpHeaders(
    {'Content-Type': 'application/x-www-form-urlencoded'}
  )
};

@Injectable()
export class AuthService {

  public ws: any;
  registerUrl = 'http://localhost:8080/communicator/api/auth/sign-up';
  loginUrl = 'http://localhost:8080/communicator/login';
  username: string;

  constructor(private http: HttpClient, private router: Router, private jwtHelper: JwtHelperService) {
  }

  /**
   * Registers new user to system.
   *
   * @param data user form data.
   */
  register(data) {
    this.http.post(this.registerUrl, data, headers).subscribe(() => {
      this.router.navigate(['login']);
    });
  }

  /**
   * Performs user authentication. If user credentials are valid then server should return generated JSON Web Token
   * for this user. If this token exists in http header, then save it to local browser storage.
   *
   * @param data login form data.
   */
  login(data: HttpParams) {
    this.http.post(this.loginUrl, data, formHeader).subscribe((resp: Response) => {
      localStorage.setItem('token', JSON.stringify(resp['jwt']).replace(/\"/g, ""));
      this.router.navigate(['']);
      this.username = data.get('username');
    })
  }

  /**
   * Logout mechanism. It only deletes JSON Web Token from browser local storage.
   * Every request made after logout will be redirected to login page because server will not receive token in http
   * request header.
   */
  logout() {
    localStorage.removeItem('token');
  }

  /**
   * Checks if JSON Web Token is saved in local browser storage. If it is saved then user is authenticated.
   */
  isAuthenticated() {
    const token = localStorage.getItem('token');

    return !this.jwtHelper.isTokenExpired(token);
  }
}
