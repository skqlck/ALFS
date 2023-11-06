import { baseAxios } from "../Api";

export async function GetProductDetail(id: number) {
  try {
    const res = await baseAxios.get(`api/product/${id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
