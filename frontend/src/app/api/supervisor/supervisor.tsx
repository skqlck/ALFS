import { baseAxios } from "../Api";

export async function SupervisorLogin(props: any) {
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
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterProduct(props: any) {
  try {
    const res = await baseAxios.post(`api/product`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UpdateProduct(props: any) {
  try {
    const res = await baseAxios.patch(`api/product`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterIngredient(props: any, id: any) {
  try {
    const res = await baseAxios.post(`api/product_ingredient/${id}`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterManufacturing(props: any) {
  try {
    const res = await baseAxios.post(`api/manufacturing-allergy`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialRegister(props: any) {
  try {
    const res = await baseAxios.post(`api/special`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialAll() {
  try {
    const res = await baseAxios.get(`api/special/all`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialDelete(props: number) {
  const supervisorId = localStorage.getItem("supervisorId");
  try {
    const res = await baseAxios.post(`api/special/${props}`, { supervisorId: supervisorId });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function SpecialUpdate(props: any, id: number) {
  try {
    const res = await baseAxios.patch(`api/special/${id}`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}
