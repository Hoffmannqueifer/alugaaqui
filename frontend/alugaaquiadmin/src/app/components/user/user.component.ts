import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  user: User ={
    id: 0,
    nome: '',
    cpf: '',
    celular: '',
    usuario: {
      username: '',
      password: ''
    }
  }

  ELEMENT_DATA: User[] = [];

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email','celular', 'acoes'];
  dataSource = new MatTableDataSource<User>(this.ELEMENT_DATA);
  isLoading: boolean = false;
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  constructor(private service: UserService, private toast : ToastrService, private router: Router){}

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<User>(this.ELEMENT_DATA);
    this.dataSource.paginator = this.paginator;
    this.findAll();
  }

  findAll(){
    this.isLoading = true;
    this.service.findAll().subscribe( resposta => {
      console.log(resposta)
      this.ELEMENT_DATA = resposta.content;
      this.dataSource = new MatTableDataSource<User>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
      this.isLoading = false;
    })
  }
}