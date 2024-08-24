import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<User[]>{
    return this.http.get<User[]>('/api/v1/usuarios');
  }

  create(user : User): Observable<User>{
    return this.http.post<User>('/api/v1/usuarios/funcionario', user);
  }
}
