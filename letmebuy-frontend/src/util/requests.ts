import axios from "axios";
import qs from "qs";
import { AxiosRequestConfig } from 'axios';
import history from './history';
import { getAuthData } from "./storage";


export const BASE_URL = process.env.REACT_APP_BACKEND_URL ?? 'http://localhost:8080';

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
    if (error.response.status === 401) {
        history.push('/dashboard/auth');
    }
    return Promise.reject(error);
});



