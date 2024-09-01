import { Injectable } from '@angular/core';
import { Credenciais } from '../models/credenciais';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { map } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  jwtService: JwtHelperService = new JwtHelperService();

  constructor(private http:HttpClient) { }

  authenticate(creds: Credenciais){
    return this.http.post<{token: string}>('/api/v1/auth', creds, {
      observe: 'response'
    }).pipe(map(response => {
      const token = response.body?.token; 
      if (token) {
        this.sucessFullLogin(token);
      }
      return token;
    }));
  }

  sucessFullLogin(authToken: string){
    localStorage.setItem('token', authToken);
  }

  isAuthenticated(){
    let token = localStorage.getItem('token')
    if(token != null){
      return !this.jwtService.isTokenExpired(token);
    }
    return false;
  }

  logout(){
    localStorage.clear();
  }

  getUserRole(): string | null {
    const token = localStorage.getItem('token');
    if (token) {
      const decodedToken = this.jwtService.decodeToken(token);
      return decodedToken.role; // Supondo que o papel esteja armazenado como 'role'
    }
    return null;
  }

  isAdmin(): boolean {
    const role = this.getUserRole();
    return role === 'ADMIN';
  }

  isFuncionario(): boolean {
    const role = this.getUserRole();
    return role === 'FUNCIONARIO';
  }
}
