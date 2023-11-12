import { baseAxios } from "../Api";

export async function SupervisorLogin(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.post("api/supervisor/login", {
      identifier: props.identifier,
      password: props.password,
    });

    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function AllProduct() {
  try {
    const res = await baseAxios.get(`api/product/all`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function DeleteProduct(props: number) {
  const data = String(props);
  try {
    const res = await baseAxios.delete(`api/product/${data}`);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterProduct(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.post(`api/product`, props);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UpdateProduct(props: any) {
  try {
    const res = await baseAxios.patch(`api/product`, props);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}
