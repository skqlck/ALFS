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

export async function UserSignup(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.post("/api/member/signup", {
      member: {
        identifier: props.id,
        password: props.password,
        passwordCheck: props.passwordCheck,
        name: props.name,
        email: props.email,
        phoneNumber: props.phone_number,
        birth: props.birth,
      },
      address: {
        address_1: props.address_1,
        address_2: props.address_2,
        alias: props.alias,
      },
    });
    console.log(res);
    return res.data.data;
  } catch (error) {
    console.error(error);
  }
}

export async function UserLogin(props: any) {
  try {
    const res = await baseAxios.post(`api/member/login`, {
      identifier: props.identifier,
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
      member_id: props.member_id,
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

export async function DeleteAddress(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.delete(`api/address/`, {
      data: {
        member_id: props[0],
        address_id: props[1],
      },
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterAllergy(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.post(`api/allergy/check/${props.memberId}/1`);
    return res;
  } catch (e) {
    console.error(e);
  }
}
export async function RegisterHate(props: any) {
  console.log(props);
  try {
    const res = await baseAxios.post(`api/allergy/check/${props.memberId}/0`, {
      data: {
        member_id: props[0],
        address_id: props[1],
      },
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}
