import { baseAxios } from "../Api";

export async function GetList(member_id: string) {
  try {
    const res = await baseAxios.get(`api/product/all/${Number(member_id)}`);
    console.log("전체리스트 요청", res.data.data, "memberid :", member_id);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
