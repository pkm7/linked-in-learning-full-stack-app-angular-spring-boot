import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs';
// Statics
import 'rxjs/add/observable/throw';
// Operators
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/toPromise';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    
  constructor(private http:Http){
  }
  
  private baseUrl:string = 'http://localhost:8080';
  roomsearch : FormGroup;
  public submitted : boolean;
  rooms : Room[];
    
  ngOnInit(){
      this.roomsearch = new FormGroup({
          checkin : new FormControl(''),
          checkout : new FormControl('')
      });
      
  }
    
  onSubmit({value,valid}:{value:Roomsearch, valid:boolean}){
      console.log(1);
      this.getAll()
      .subscribe(
          rooms => this.rooms,
          err => {
                console.log(err);  
            }
          );
  }
    
  reserveRoom(value:string){
      console.log(value);
  }
    
  getAll():Observable<Room[]>{
            console.log(2);
   return this.http.get(this.baseUrl + '/room/reservation/v1/?checkin=2017-12-31&checkout=2018-01-01')
    .map(this.mapRoom);
  }
    
  
  mapRoom(response:Response):Room[]{
    return response.json().content;
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


