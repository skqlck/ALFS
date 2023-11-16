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

export async function RegisterIngredient(props: any, id: any) {
  try {
    const res = await baseAxios.post(`api/product_ingredient/${id}`, props);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterManufacturing(props: any) {
  try {
    const res = await baseAxios.post(`api/manufacturing-allergy`, props);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialRegister(props: any) {
  try {
    const res = await baseAxios.post(`api/special`, props);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialAll() {
  try {
    const res = await baseAxios.get(`api/special/all`);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialDelete(props: number) {
  const supervisorId = localStorage.getItem("supervisorId");
  try {
    const res = await baseAxios.post(`api/special/${props}`, { supervisorId: supervisorId });
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialUpdate(props: any, id: number) {
  console.log(props);
  console.log(id);
  try {
    const res = await baseAxios.patch(`api/special/${id}`, props);
    console.log(res);
    return res;
  } catch (e) {
    console.error(e);
  }
}
