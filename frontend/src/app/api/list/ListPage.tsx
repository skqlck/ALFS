import { baseAxios } from "../Api";

export async function GetList(page: number, status: number) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.get(`api/product/all/${Number(member_id)}/${page}/${status}`);
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

export async function ProductSearch(props: string) {
  const id = localStorage.getItem("id") || 0;
  try {
    const res = await baseAxios.get(`api/product/search/${props}/${id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
