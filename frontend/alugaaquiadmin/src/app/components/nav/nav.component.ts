import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent {

  isAdmin: boolean = false;
  isFuncionario: boolean = false;

  constructor(private authService: AuthService, private router: Router, private toast : ToastrService){

  }

  ngOnInit(): void {
    this.router.navigate(['home']);
    this.isAdmin = this.authService.isAdmin();
    this.isFuncionario = this.authService.isFuncionario();
  }

  logout(){
    console.log(document.querySelector('.toast'));
    this.toast.info('Saida Realizada com Sucesso', 'Logout', {timeOut: 7000});
    this.router.navigate(['login']);
  }
}
