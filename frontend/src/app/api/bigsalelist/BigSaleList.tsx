import { baseAxios } from "../../api/Api";

export async function GetBigSaleList() {
  try {
    const res = await baseAxios.get(`api/special/all`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}