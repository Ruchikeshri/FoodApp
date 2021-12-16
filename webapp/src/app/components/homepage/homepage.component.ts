import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { DialogueboxComponent } from '../dialoguebox/dialoguebox.component';
import { RestaurantCardComponent } from '../restaurant-card/restaurant-card.component';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(private restaurantCard: RestaurantCardComponent,
    private formBuilder: FormBuilder, private dialog: MatDialog) { }

  searchDetails = this.formBuilder.group({
    city: ['Select City'],
    key: ['']
  })

  ngOnInit(): void {
    this.searchDetails.valueChanges.subscribe(value => {
    })
  }

  onSubmit() {
    console.log(this.searchDetails.value);
    let city = this.searchDetails.get("city").value;
    let key = this.searchDetails.get("key").value;
    if (key == "" && city != "Select City") {
      //get by city logic
      this.restaurantCard.getByCity(parseInt(city));
    }
    else if (key != "" && city == "Select City") {
      //get by key for city 4
      this.restaurantCard.getByKey(key);
    }
    else if (key != "" && city != "Select City") {
      //get by city and key
      this.restaurantCard.getByCityAndKey(parseInt(city), key);
    }
    else {
      //displays the default page 
      this.restaurantCard.getAllRestaurants();
    }
  }

  openDialog(message) {
    const dialogRef = this.dialog.open(DialogueboxComponent,
      {
        width: "450px",
        data: message,
        panelClass: "modalbox"
      });
    dialogRef.afterClosed().subscribe(result => {
    })
  }

}
