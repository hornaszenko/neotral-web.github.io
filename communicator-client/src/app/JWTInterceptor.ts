import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {AuthService} from "./services/AuthService";
import {Observable} from "rxjs";

/**
 * Http interceptor that add JSON Web Token to http request header every request.
 */
@Injectable()
export class JWTInterceptor implements HttpInterceptor {

  constructor(public authService: AuthService) {
  }

  /**
   * Checks if Json Web Token is saved in browser and add it to http request header for authentication purpose.
   * @param req http request.
   * @param next handler.
   */
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (localStorage.getItem('token') != null) {
      req = req.clone({
        setHeaders: {
          Authorization: `${localStorage.getItem("token").replace(/\\"/g, "")}`
        }
      });
    }

    return next.handle(req);
  }
}
