import axios, { AxiosRequestConfig } from "axios";
import { getSessionData } from "./auth";

const baseURL =
  process.env.REACT_APP_BASE_URL ?? "http://localhost:8080";
export const AUTH_TOKEN = "Basic ZHNjYXRhbG9nOmRzY2F0YWxvZzEyMw==";
export const CLIENT_ID = process.env.REACT_APP_CLIENT_ID ?? "letmebuy";
export const CLIENT_SECRET =
  process.env.REACT_APP_CLIENT_SECRET ?? "letmebuy123";

export const api = (params: AxiosRequestConfig) => {
  return axios({
    ...params,
    baseURL,
  });
};

export const authenticatedRequest = (params: AxiosRequestConfig) => {
  const sessionData = getSessionData();
  const headers = {
    Auhtorization: `Bearer ${sessionData}`,
  };

  return api({ ...params, headers });
};
