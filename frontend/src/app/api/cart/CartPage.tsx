import { baseAxios } from "../Api";

export async function AddProductToCart(id: string, cnt: number) {
  try {
    const member_id = localStorage.getItem("id");
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

export async function CartList() {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.get(`api/basket/${member_id}`);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AddCount(id: number) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.put(`api/basket/addCount`, {
      member_id: Number(member_id),
      basket_id: id,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function RemoveCount(id: number) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.put(`api/basket/removeCount`, {
      member_id: Number(member_id),
      basket_id: id,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function RemoveBasket(ids: Array<number>) {
  try {
    const member_id = localStorage.getItem("id");
    const res = await baseAxios.put(`api/basket/removeBasket`, {
      member_id: Number(member_id),
      basket_ids: ids,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function PurchaseItem(props: any) {
  const member_id = localStorage.getItem("id");
  try {
    const res = await baseAxios.put(`api/basket/purchase`, {
      member_id: member_id,
      basket_ids: props,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}
