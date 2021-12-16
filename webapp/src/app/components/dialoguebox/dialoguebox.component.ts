import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { RegisterComponent } from '../register/register.component';

@Component({
  selector: 'app-dialoguebox',
  templateUrl: './dialoguebox.component.html',
  styleUrls: ['./dialoguebox.component.css']
})
export class DialogueboxComponent implements OnInit {

  constructor(private matDialog: MatDialog,
    public dialogRef: MatDialogRef<DialogueboxComponent>,
    @Inject(MAT_DIALOG_DATA) public message: any) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.dialogRef.close();
    }, 2000
    )
  }
  onNoclick(): void {
    this.dialogRef.close();
  }


}
