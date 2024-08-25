import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { PaginatedResponseUser } from '../models/PaginatedResponseUser';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  findAll(): Observable<PaginatedResponseUser>{
    return this.http.get<PaginatedResponseUser>('/api/v1/funcionarios');
  }

  create(user : User): Observable<User>{
    return this.http.post<User>('/api/v1/usuarios/funcionario', user);
  }
}
