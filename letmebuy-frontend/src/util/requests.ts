import axios from "axios";
import qs from "qs";
import { AxiosRequestConfig } from 'axios';
import history from './history';

type LoginResponse = {
    access_token: string;
    token_type: string;
    //refresh_token: string,;
    expires_in: number;
    scope: string;
    userFirstName: string;
    userId: number;
}

export const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

const tokenKey = 'authData';
const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? 'letmebuy';
const CLIENT_SECRET = process.env.REACT_APP_CLIENT_SECRET ?? 'letmebuy123';

//gera a criptografia de (authorization) do header no login
//const basicHeader = () => 'Basic ' + window.btoa(CLIENT_ID + ':' + CLIENT_SECRET);

type LoginData = {
    username: string;
    password: string;
};

export const requestBackendLogin = (loginData: LoginData) => {
    const headers = {
        'Content-Type': 'application/x-www-form-urlencoded',
        Authorization: 'Basic ' + window.btoa(CLIENT_ID + ':' + CLIENT_SECRET),
    };

    //(qs.strindify) monta um QueryString de requisição apartir de um objeto.
    const data = qs.stringify({
        ...loginData,
        grant_type: 'password',
    });

    return axios({
        method: 'POST',
        baseURL: BASE_URL,
        url: '/oauth/token',
        data,
        headers,
    });
}

//(requestBackend) responsavel por efetuar a requisição de rotas
export const requestBackend = (config: AxiosRequestConfig) => {

    const headers = config.withCredentials
        ? {
            ...config.headers,
            Authorization: "Bearer " + getAuthData().access_token
        }
            : config.headers;

    return axios({ ...config, baseURL: BASE_URL, headers });
}

export const saveAuthData = (obj: LoginResponse) => {
    localStorage.setItem(tokenKey, JSON.stringify(obj)); //(JSON.stringify) converte o obj em string.
}

export const getAuthData = () => {
    //"{}" Pega o obj e converte em string com "JSON.parse" e o cast "as LoginResponse" garante o tipo de dado
    const str = localStorage.getItem(tokenKey) ?? "{}";
    return JSON.parse(str) as LoginResponse;
}

// Add a request interceptor
axios.interceptors.request.use(function (config) {

    return config;
}, function (error) {

    return Promise.reject(error);
});

// Add a response interceptor
axios.interceptors.response.use(function (response) {

    return response;
}, function (error) {
    if (error.response.status === 401 || error.response.status === 403) {
        history.push('/dashboard/auth');
    }
    return Promise.reject(error);
});