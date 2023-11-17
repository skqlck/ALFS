import { baseAxios } from "../Api";

export async function GetProductDetail(id: number) {
  try {
    const member_id = localStorage.getItem("id") || 0;

    const res = await baseAxios.get(`api/product/${member_id}/${id}`);
    return res.data.data[0];
  } catch (e) {
    console.error(e);
  }
}
