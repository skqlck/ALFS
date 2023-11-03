import { baseAxios } from "../Api";

export async function CheckId(props: String) {
  try {
    const res = await baseAxios.get(`api/member/check/identifier/${props}`);
    console.log(res.data.data);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function CheckEmail(props: String) {
  try {
    const res = await baseAxios.get(`api/member/check/email/${props}`);
    console.log(res.data.data);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function CheckPhone(props: String) {
  try {
    const res = await baseAxios.get(`api/member/check/phoneNumber/${props}`);
    console.log(res.data.data);
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function UserLogin(props: any) {
  try {
    const res = await baseAxios.post(`api/member/login`, {
      identifier: props.id,
      password: props.password,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function UserLogout(props: any) {
  try {
    const res = await baseAxios.post(`api/member/logout`, {
      identifier: props,
    });
    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function AddressAll(props: any) {
  try {
    const res = await baseAxios.get(`api/address/${props}`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function ChangeStatus(props: any) {
  try {
    const res = await baseAxios.put(`api/address/`, {
      member_id: props[0],
      address_id: props[1],
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function PlusAddress(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.post(`api/address/`, {
      member_id: localStorage.getItem("id"),
      address: {
        address_1: props.address_1,
        address_2: props.address_2,
        alias: props.alias,
      },
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}
