import jwtDecode from 'jwt-decode';
import { getAuthData } from './storage';


export type Role = 'ROLE_OPERATOR' | 'ROLE_ADMIN';

export type TokenData = {
    exp: number;
    user_name: string;
    authorities: Role[];
}

export const getTokenData = (): TokenData | undefined => {
    
    try {
        return jwtDecode(getAuthData().access_token) as TokenData;
        
    } catch (error) {
        return undefined;
    } 
}

// Verifica a autenticação do (Usuário) analisando o exp do futuro para expiração
export const isAuthenticated = (): boolean => {
    const tokenData = getTokenData();
    return (tokenData && tokenData.exp * 1000 > Date.now()) ? true : false;
}

export const hasAnyRoles = (roles: Role[]): boolean => {
    if (roles.length === 0) {
        return true;
    }

    //Percorre os roles do type(tokenData.authorities) e verifica se contem o role do usuário logado
    const tokenData = getTokenData();

    // Metodo (some) função de alta ordem, testando o elemento de uma lista
    if (tokenData !== undefined) {
        return roles.some(role => tokenData.authorities.includes(role));
    }

    /*
    // Metodo alternativo percorrento com for
    if (tokenData !== undefined) {
        for (var i = 0; i < roles.length; i++){
            if (tokenData.authorities.includes(roles[i])) {
                return true;
            }
        }
    }
    */

    return false;
    
}