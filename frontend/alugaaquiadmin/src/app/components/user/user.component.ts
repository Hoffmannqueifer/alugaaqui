import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {

  ELEMENT_DATA: User[] = [
    { id: 1, nome: 'Ana Silva', cpf: '123.456.789-00', email: 'ana.silva@example.com', senha: '123' },
    { id: 2, nome: 'Carlos Pereira', cpf: '987.654.321-00', email: 'carlos.pereira@example.com', senha: '123' },
    { id: 3, nome: 'Beatriz Souza', cpf: '111.222.333-44', email: 'beatriz.souza@example.com', senha: '123' },
    { id: 4, nome: 'Daniel Rocha', cpf: '444.555.666-77', email: 'daniel.rocha@example.com', senha: '123' },
    { id: 5, nome: 'Fernanda Lima', cpf: '888.999.000-11', email: 'fernanda.lima@example.com', senha: '123' },
    { id: 6, nome: 'teste', cpf: '888.999.000-14', email: 'teste@example.com', senha: '123' }
  ];

  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'acoes'];
  dataSource = new MatTableDataSource<User>(this.ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;

  ngOnInit(): void {
    this.dataSource = new MatTableDataSource<User>(this.ELEMENT_DATA);
    this.dataSource.paginator = this.paginator;
  }
}
