import { User } from "./user";

export interface PaginatedResponseUser {
    content: User[];
    totalPages: number;
    totalElements: number;
    size: number;
    // Outras propriedades, se necessário
  }