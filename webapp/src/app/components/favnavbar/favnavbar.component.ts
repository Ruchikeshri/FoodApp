import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { DialogueboxComponent } from '../dialoguebox/dialoguebox.component';

@Component({
  selector: 'app-favnavbar',
  templateUrl: './favnavbar.component.html',
  styleUrls: ['./favnavbar.component.css']
})
export class FavnavbarComponent implements OnInit {

  name: string = "";
  email: string;
  constructor(private authenticationSer: AuthenticationService,
    private router: Router, private dialog: MatDialog) {
    if (authenticationSer.isAuthenticated()) {
      this.email = authenticationSer.getEmail();
      for (let i = 0; i < this.email.length; i++) {
        let char = this.email.charAt(i);
        if (char == '@') { break; }
        else { this.name += char; }
      }
      this.name = this.name.charAt(0).toUpperCase() + this.name.slice(1);
    }
  }

  ngOnInit(): void {
  }
  onLogout() {
    this.authenticationSer.deleteEmail();
    this.authenticationSer.deleteToken();
    this.openDialog("LoggedOut Successfully", "homepage");
  }
  openDialog(message, route) {
    const dialogRef = this.dialog.open(DialogueboxComponent,
      {
        width: "450px",
        data: message,
        panelClass: "modalbox"
      });
    dialogRef.afterClosed().subscribe(result => {
      this.router.navigate(["/" + route])
      setTimeout(()=>{ window.location.reload();}, 100)
    })
  }
}
