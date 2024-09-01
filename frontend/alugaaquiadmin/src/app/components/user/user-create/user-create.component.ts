import { Component } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent {
  hide = true;
  user: any = {
    nome: '',
    cpf: '',
    celular: '',
    usuario: {
      username: '',
      password: ''
    }
  }

  nome: FormControl = new FormControl(null, Validators.minLength(5));
  cpf: FormControl = new FormControl(null, Validators.required);
  email: FormControl = new FormControl(null, Validators.email);
  senha: FormControl = new FormControl(null, Validators.minLength(6));
  celular: FormControl = new FormControl(null, Validators.minLength(11));

  constructor(private service: UserService, private toast : ToastrService, private router: Router){}

  create(){
    this.user.usuario.username = this.email.value;
    this.user.usuario.password = this.senha.value;
    this.user.nome = this.nome.value;
    this.user.cpf = this.cpf.value;
    this.user.celular = this.celular.value;
    this.service.create(this.user).subscribe( () =>{
      this.toast.success('Funcionario cadastrado com sucesso', 'Cadastro');
      this.router.navigate(['users']);
    }, ex =>{
      console.log(ex)
      if(ex.error.errors){
        ex.error.errors.forEach((element: { message: string | undefined; }) => {
          this.toast.error(element.message);
        });
      }else{
        this.toast.error(ex.error.message);
      }
    })
  }
  validarCampos() : boolean{
    return this.nome.valid && this.cpf.valid && this.email.valid && this.celular.valid && this.senha.valid;
  }
}
