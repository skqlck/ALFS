import { baseAxios } from "../Api";

export async function AlterList() {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.get(`api/alternative/category/list/${member_id}`);
    console.log("대체식품 전체조회", res.data.data);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AlterDetail(catName: string) {
  try {
    const res = await baseAxios.get(`api/alternative/category/${catName}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AlterAll(alternativeCategoryList: Array<string>) {
  try {
    const res = await baseAxios.post(`api/alternative/all`, {
      alternativeCategoryList: alternativeCategoryList,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
