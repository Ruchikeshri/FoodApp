import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Authenticate } from 'src/app/model/authenticate';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { DialogueboxComponent } from '../dialoguebox/dialoguebox.component';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  login: Authenticate = new Authenticate();
  loginform: FormGroup;
  hide = true;
  errorMsg: string = '';
  constructor(private formBuilder: FormBuilder,
    private authenticateSer: AuthenticationService,
    private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loginform = this.formBuilder.group({
      'email': [this.login.email, [
        Validators.required,
        Validators.email
      ]],
      'password': [this.login.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
    });

  }
  onLoginSubmit() {
    this.authenticateSer.login(this.login)
      .subscribe((data) => {
        if (data) {
          this.authenticateSer.storeToken(data.token);
          this.authenticateSer.storeEmail(this.login.email);
          this.openDialog("Loggedin Successfully", "homepage");
        }
        else {
          this.openDialog("Invalid Username or Password!! Register if new User", "login");
        }
      }, (error) => {
        this.errorMsg = error
        this.openDialog("Invalid Username or Password!! Register if new User!", "login");
      });
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
      setTimeout(()=>{ window.location.reload();}, 500)
    })
  }
}
