import { baseAxios } from "../Api";

export async function AddProductToCart(id: string, cnt: number, member_id: string) {
  try {
    const res = await baseAxios.post(`api/basket/add`, {
      member_id: Number(member_id),
      product_id: id,
      count: cnt,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function CartList(member_id: string) {
  try {
    const res = await baseAxios.get(`api/basket/${member_id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AddCount(id: number, member_id: string) {
  try {
    const res = await baseAxios.put(`api/basket/addCount`, {
      member_id: Number(member_id),
      basket_id: id,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function RemoveCount(id: number, member_id: string) {
  try {
    const res = await baseAxios.put(`api/basket/removeCount`, {
      member_id: Number(member_id),
      basket_id: id,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function PurchaseItem(id: string, member_id: string) {
  try {
    const res = await baseAxios.put(`api/basket/purchase`, {
      member_id: Number(member_id),
      product_id: id,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
