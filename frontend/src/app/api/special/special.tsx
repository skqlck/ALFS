import { baseAxios } from "../Api";

export async function AddCartSpecial(id: string) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.post(`api/special/queue`, {
      productId: Number(id),
      memberId: Number(member_id),
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}
