import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FavhomepageComponent } from './components/favhomepage/favhomepage.component';
import { HomepageComponent } from './components/homepage/homepage.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { RestaurantCardComponent } from './components/restaurant-card/restaurant-card.component';
import { AuthenticationGuard } from './guard/authentication.guard';

const routes: Routes = [
  { path: '', redirectTo: '/homepage', pathMatch: 'full' },
  { path: 'homepage', component: RestaurantCardComponent },
  { path: 'favorite', component: FavhomepageComponent, canActivate: [AuthenticationGuard] },
  { path: 'register', component: RegisterComponent },
  { path: 'login', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
