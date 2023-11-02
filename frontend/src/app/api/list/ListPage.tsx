import { baseAxios } from "../Api";

export async function GetList() {
  try {
    const res = await baseAxios.get(`api/product/all`);
    console.log(res.data.data);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
