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
