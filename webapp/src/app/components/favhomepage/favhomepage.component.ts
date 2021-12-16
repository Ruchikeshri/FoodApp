import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Favorites } from 'src/app/model/favorites';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { FavouriteService } from 'src/app/services/favourite.service';
import { DialogueboxComponent } from '../dialoguebox/dialoguebox.component';

@Component({
  selector: 'app-favhomepage',
  templateUrl: './favhomepage.component.html',
  styleUrls: ['./favhomepage.component.css']
})
export class FavhomepageComponent implements OnInit {

  constructor(private favoriteSer:FavouriteService,
    private authenticationSer:AuthenticationService , 
    private router:Router, private dialog:MatDialog) { 
      this.fav = new Favorites();
    }
    
  p:number=1;
  fav : Favorites;
  email:string='';
  errorMsg:string='';
  

  ngOnInit(): void {
  this.email=this.authenticationSer.getEmail();
  this.favoriteSer.getAllFavorites(this.email)
  .subscribe((data)=>{this.fav=data; console.log("Restaurant data.....",data) }, 
  (error)=>{this.errorMsg=error; console.log("error while adding", error)});
  }

  deleteFavorite(id){
    this.favoriteSer.deleteFavorite(this.email , id)
    .subscribe((data)=>{console.log(data); 
      this.openDialog("Deleted Successfully", "favorite"); 
      this.ngOnInit();}, 
      (error)=>this.errorMsg=error );
    
  }

  openDialog(message, route)
{
const dialogRef= this.dialog.open(DialogueboxComponent,
  {

    width:"450px",
    data:message,
    panelClass:"modalbox"
  });
  dialogRef.afterClosed().subscribe(result=>{
    this.router.navigate(['/'+route])

  })
}

}
