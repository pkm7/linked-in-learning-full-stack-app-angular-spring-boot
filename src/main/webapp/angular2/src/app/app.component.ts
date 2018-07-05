import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  roomsearch : FormGroup;
  public submitted : boolean;
  rooms : Room[];
    
  ngOnInit(){
      this.roomsearch = new FormGroup({
          checkin : new FormControl(''),
          checkout : new FormControl('')
      });
      
      this.rooms = ROOMS;
      
  }
    
  onSubmit({value,valid}:{value:Roomsearch, valid:boolean}){
      console.log(value);
  }
    
  reserveRoom(value:string){
      console.log(value);
  }
}


export interface Roomsearch {
    checkin : string;
    chekcout : string;    
}

export interface Room {
    id : string;
    roomNumber : string;    
    price : string;    
    links : string;    
}

var ROOMS:Room[] = [
{
    "id" : "1234",
    "roomNumber" : "409",
    "price" : "29",
    "links" : ""
},
{
    "id" : "1235",
    "roomNumber" : "309",
    "price" : "19",
    "links" : ""
},
{
    "id" : "1236",
    "roomNumber" : "101",
    "price" : "35",
    "links" : ""
}
]
