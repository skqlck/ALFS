import { baseAxios } from "../Api";

export async function GetList(member_id: string) {
  try {
    const res = await baseAxios.get(`api/product/all/${Number(member_id)}`);
    console.log(res.data.data);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
