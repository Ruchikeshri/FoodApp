import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class ExternalApiService {
  private _url = 'http://localhost:8083/api/v1/';

  constructor(private http: HttpClient) { }

  getRestaurentsByCity(start, cityid): Observable<any> {
    return this.http.get(`${this._url}/restaurantsbycity`, {
      params: { start: start, city: cityid }
    }).pipe(catchError(error => { return throwError(error.message) }))
  }

  getRestaurentsByKey(start, cityid, key): Observable<any> {
    return this.http.get(`${this._url}/restaurantsbykey`, {
      params: { start: start, city: cityid, q: key }
    }).pipe(catchError(error => { return throwError(error.message) }))
  }
}
