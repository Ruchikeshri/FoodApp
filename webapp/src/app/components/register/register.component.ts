import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Authenticate } from 'src/app/model/authenticate';
import { DialogueboxComponent } from '../dialoguebox/dialoguebox.component';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  register: Authenticate = new Authenticate();
  registerForm: FormGroup;
  hide = true;
  ConfirmPassword: any;
  errorMsg: string = '';

  constructor(private formBuilder: FormBuilder,
    private authenticateSer: AuthenticationService,
    private router: Router, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      'email': [this.register.email, [
        Validators.required,
        Validators.email
      ]],
      'password': [this.register.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]],
      'ConfirmPassword': [this.register.password, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(30)
      ]]
    }, { validator: this.checkPassword });

  }
  onRegisterSubmit() {

    this.authenticateSer.regitser(this.register)
      .subscribe(() => { }, (error) => { this.errorMsg = error; this.openDialog("Failed to add", "register"); },
        () => { this.openDialog("User Registered", "login"); });
  }

  checkPassword(registerForm) {
    let password = registerForm.get("password").value;
    let confirmPassword = registerForm.get("ConfirmPassword").value;
    return password === confirmPassword ? null : { notSame: true }
  }
  openDialog(message, route) {
    const dialogRef = this.dialog.open(DialogueboxComponent,
      {

        width: "450px",
        data: message,
        panelClass: "modalbox"
      });
    dialogRef.afterClosed().subscribe(result => {
      this.router.navigate(['/' + route])

    })
  }
}
