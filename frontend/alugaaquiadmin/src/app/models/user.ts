export interface User {
    id: number;
    nome: string;
    cpf: string;
    celular: string;
    usuario: {
        username: string;
        password: string;
    };
}
