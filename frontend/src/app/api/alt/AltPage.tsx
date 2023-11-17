import { baseAxios } from "../Api";

export async function AlterList() {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.get(`api/alternative/category/list/${member_id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AlterDetail(catName: string) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.get(`api/alternative/category/${catName}/${member_id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AlterAll(props: any) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.post(`api/alternative/all/${member_id}`, {
      alternativeCategoryList: props,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
