import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Request, RequestMethod} from '@angular/http';
import {Observable} from "rxjs";


import { map } from 'rxjs/operators';
import { catchError } from 'rxjs/operators';

import { HttpClient, HttpHeaders } from '@angular/common/http';
//import {HttpClientModule,HttpClient,HttpHeaders} from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    
  constructor(private http:Http){
  }
  
  private baseUrl : string = 'http://localhost:8080';
  private getUrl:string = this.baseUrl + '/room/reservation/v1/';
  private postUrl:string = this.baseUrl + '/room/reservation/v1/';
  roomsearch : FormGroup;
  public submitted : boolean;
  rooms : Room[];
  currentCheckInVal : string;
  currentCheckOutVal : string;
  request : ReserveRoomRequest;
    
  ngOnInit(){
      this.roomsearch = new FormGroup({
          checkin : new FormControl(''),
          checkout : new FormControl('')
      });
      
    const roomsearchValueChanges$ = this.roomsearch.valueChanges;
        
    roomsearchValueChanges$.subscribe( valChange => {
            console.log(valChange)
            this.currentCheckInVal = valChange.checkin;
            this.currentCheckOutVal = valChange.checkout;
        }
       )
  }
    
    
  onSubmit({value,valid}:{value:Roomsearch, valid:boolean}){
     // this.currentCheckInVal = value.checkin;
     // this.currentCheckOutVal = value.checkout;
      this.getAll()
      .subscribe(
          rooms => this.rooms=rooms,
          err => {
                console.log(err);  
            }
          );
      console.log(1.1);
  }
    
  reserveRoom(value:string){
      console.log("reserveRoom:"+value+"-"+this.currentCheckInVal+"-"+this.currentCheckOutVal);
      this.request = new ReserveRoomRequest(value, this.currentCheckInVal, this.currentCheckOutVal);
      this.createReservation(this.request);
  }
    
  getAll():Observable<Room[]>{
            console.log("getAll:" + this.currentCheckInVal + "-" + this.currentCheckOutVal);
   return this.http.get(this.getUrl + '?checkin=' + this.currentCheckInVal + '&checkout=' + this.currentCheckOutVal)
        .pipe(map(this.mapRoom));
  }
    
  
  createReservation(body: ReserveRoomRequest){
      console.log("createReservation");
//       const headerss = new Headers();
//        headerss.append('Content-Type', 'application/json;charset=UTF-8');
////        headerss.append('Accept', 'application/json');
////        headerss.append('Access-Control-Allow-Headers', 'Content-Type');
//
//       const options = new RequestOptions({headers: headerss});
//      
//       this.http.post(
//           this.postUrl,
//           JSON.stringify(body),
//           options
//       ).subscribe(res => console.log(res));
      
       let bodyString = JSON.stringify(body); // Stringify payload
    let headers = new Headers({'Content-Type': 'application/json'}); // ... Set content type to JSON
    let options = new RequestOptions({headers: headers}); // Create a request option

    this.http.post(this.postUrl, body, options)
      .subscribe(res => console.log(res));
  }
    
  mapRoom(response:Response):Room[]{
     console.log(response);
     console.log(response.json().content);
    return response.json().content;
  }
    
}


export interface Roomsearch {
    checkin : string;
    checkout : string;
}

export interface Room {
    id : string;
    roomNumber : string;    
    price : string;    
    links : string;    
}

export class ReserveRoomRequest {
    roomId:string;
    checkin:string;
    checkout:string;
    
    constructor(roomId:string, checkin:string, checkout:string){
        this.roomId = roomId;
        this.checkin = checkin;
        this.checkout = checkout;
    }
}


