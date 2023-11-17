import { baseAxios } from "../../api/Api";

export async function GetBigSaleList() {
  try {
    const res = await baseAxios.get(`api/special/all`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function GetBigSaleFilteredList(member_id : string){
  try {
    const res = await baseAxios.get(`api/special/all/${member_id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}