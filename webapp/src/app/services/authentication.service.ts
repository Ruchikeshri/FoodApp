import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Authenticate } from '../model/authenticate';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  token: string = "";
  private _url = "http://localhost:8081/api/v1"
  constructor(private http: HttpClient) {
  }

  regitser(register: Authenticate): Observable<any> {
    return this.http.post<Authenticate>(`${this._url}/register`, register)
      .pipe(catchError(error => { return throwError(error.message) }));
  }

  login(user: Authenticate): Observable<any> {
    return this.http.post<any>(`${this._url}/login`, user)
      .pipe(catchError(error => { return throwError(error.message) }));
  }

  storeToken(token: string) {
    localStorage.setItem('token', token);
  }
  storeEmail(email: string) {
    localStorage.setItem('email', email);
  }

  getToken() {
    return localStorage.getItem('token');
  }
  getEmail() {
    return localStorage.getItem('email');
  }

  deleteToken() {
    localStorage.removeItem('token');
  }
  deleteEmail() {
    localStorage.removeItem('email');
  }

  isAuthenticated() {
    if (this.getToken()) {
      return true;
    }
    else {
      return false;
    }
  }
}
