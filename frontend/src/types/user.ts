import { Role } from "./role"

export type User = {
    id: number;
    email: string;
    roles:  Role[];
}