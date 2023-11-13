import { baseAxios } from "../Api";

export async function GetList(member_id: string, page: number) {
  try {
    const res = await baseAxios.get(`api/product/all/${Number(member_id)}/${page}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function ProductCnt() {
  try {
    const res = await baseAxios.get(`api/product/cnt`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
