import axios from "axios";

export const baseAxios = axios.create({
  baseURL: "http://k9c204.p.ssafy.io:8080",
  //   baseURL: "https://k9c204.p.ssafy.io",
});
