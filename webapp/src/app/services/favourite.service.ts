import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Favorites, Restaurant } from '../model/favorites';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  private _url = "http://localhost:8082/api/v1"


  constructor(private http: HttpClient, private authService: AuthenticationService) {
  }

  getAllFavorites(email): Observable<any> {
    const httpOptions = {
      headers: { 'authorization': 'Bearer ' + this.authService.getToken() },
      params: { email: email }
    };
    console.log("Token........", httpOptions);
    return this.http.get<any>(`${this._url}/favorites`, httpOptions)
      .pipe(catchError(error => { return throwError(error.message) }));
  }

  addToFavorite(favorites: Favorites): Observable<any> {
    console.log(favorites)
    const httpOptions = {
      headers: { 'authorization': 'Bearer ' + this.authService.getToken() },
    };
    return this.http.post<Favorites>(`${this._url}/favoriteadd`, favorites, httpOptions)
      .pipe(catchError(error => { return throwError(error.message) }));
  }

  deleteFavorite(email, id): Observable<any> {
    const httpOptions = {
      headers: { 'authorization': 'Bearer ' + this.authService.getToken() },
      params: { email: email, id: id }
    };
    return this.http.delete<Restaurant>(`${this._url}/favoritedelete`, httpOptions)
      .pipe(catchError(error => { return throwError(error.message) }));
  }
}
