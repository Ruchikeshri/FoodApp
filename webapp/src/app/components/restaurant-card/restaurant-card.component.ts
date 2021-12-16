import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Favorites, Restaurant } from 'src/app/model/favorites';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { ExternalApiService } from 'src/app/services/external-api.service';
import { FavouriteService } from 'src/app/services/favourite.service';
import { DialogueboxComponent } from '../dialoguebox/dialoguebox.component';
import { FormsModule, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-restaurant-card',
  templateUrl: './restaurant-card.component.html',
  styleUrls: ['./restaurant-card.component.css']
})
export class RestaurantCardComponent implements OnInit {

  constructor(private externalService: ExternalApiService,
    private authenticationSer: AuthenticationService,
    private favoriteSer: FavouriteService,
    private router: Router, private dialog: MatDialog) {
    this.favrest = new Favorites();
    this.restaurant = new Restaurant();
  }

  p: number = 1;
  fav: any;
  favrest: Favorites;
  restaurant: Restaurant;
  restaurantList: Restaurant[] = [];
  isFavorite: boolean[] = [];
  errorMsg: string = '';

  ngOnInit(): void {
    this.getAllRestaurants();
  }
  getAllRestaurants() {
    this.externalService.getRestaurentsByCity(1, 4).subscribe(data => {
      this.fav = data.restaurants
    });
  }

  getByCity(city: number) {
    this.externalService.getRestaurentsByCity(1, city)
      .subscribe(data => { this.fav = data.restaurants; }, (error) => { this.errorMsg = error; })
  }

  getByKey(key: string) {
    this.externalService.getRestaurentsByKey(1, 4, key)
      .subscribe(data => {
        if (data.restaurants.length == 0) {
          this.openDialog("No results found!", "");
        } else {
          this.fav = data.restaurants
        }
      }, (error) => {
        this.errorMsg = error;
      })
  }
  getByCityAndKey(city: number, key: string) {
    this.externalService.getRestaurentsByKey(1, city, key)
      .subscribe(data => {
        if (data.restaurants.length == 0) {
        this.openDialog("No results found!" , "");
      } else {
        this.fav = data.restaurants
      } }, (error) => { this.errorMsg = error; })
  }
  addFavorite(value) {
    if (this.authenticationSer.isAuthenticated()) {
      this.restaurantList = [];
      this.favrest.email = this.authenticationSer.getEmail();
      this.restaurant.id = value.id;
      this.restaurant.name = value.name;
      this.restaurant.url = value.url;
      this.restaurant.address = value.location.address;
      this.restaurant.cuisines = value.cuisines;
      this.restaurant.featured_image = value.featured_image;
      this.restaurant.timings = value.timings;
      this.restaurant.menu_url = value.menu_url;
      this.restaurant.phone_numbers = value.phone_numbers;
      this.restaurantList.push(this.restaurant);
      this.favrest.restaurants = this.restaurantList;
      console.log(this.favrest);
      this.favoriteSer.addToFavorite(this.favrest)
        .subscribe((data) => {
          console.log(data);
          this.openDialog("Added To Favorites" , "");
          this.isFavorite[value.id] = !this.isFavorite[value.id];
        }
          , error => {
            this.errorMsg = error;
            this.openDialog("Restaurant Already Exists!" , "");
          });
    }
    else {
      this.openDialog("User not Logged In" , "login")
    }
  }
  openDialog(message , nav) {
    const dialogRef = this.dialog.open(DialogueboxComponent,
      {
        width: "450px",
        data: message,
        panelClass: "modalbox"
      });
    dialogRef.afterClosed().subscribe(result => {
    if(nav!=""){
    this.router.navigate(['/' +nav]);
    }
    })
  }

}
