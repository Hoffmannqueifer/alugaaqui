import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Credenciais } from 'src/app/models/credenciais';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  creds: Credenciais = {
    email: '',
    senha: ''
  }

  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(8));

  constructor(private toast: ToastrService, private service: AuthService, private router: Router){

  }
  logar(){
    this.service.authenticate(this.creds).subscribe(resposta => {
      const authToken = resposta.headers?.get('Authorization')?.substring(7);
      if(authToken){
        this.service.sucessFullLogin(authToken)
        this.router.navigate([''])
      }else{
        this.toast.error('Token de autenticação não encontrado', 'Login');
      }
    }, () => {
      this.toast.error('Usuário e/ou senha inválidos!!', 'Login')
    });
  }

  validaCampos(): boolean{
    return this.email.valid && this.senha.valid;
  }

}
