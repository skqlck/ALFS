import { baseAxios } from "../Api";

export async function AlterList(idList: Array<string>) {
  try {
    const res = await baseAxios.post(`api/alternative/category`, {
      idList: idList,
    });
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
