import { Component } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(private authenticationSer: AuthenticationService) { }
  title = 'FoodieApp';
  isPresent() {
    return this.authenticationSer.isAuthenticated()
  }
}
