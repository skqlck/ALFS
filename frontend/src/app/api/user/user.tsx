import axios from "axios";
import { baseAxios } from "../Api";

export async function CheckId(props: String) {
  try {
    const res = await baseAxios.get(`api/member/check/identifier/${props}`);

    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function CheckEmail(props: String) {
  try {
    const res = await baseAxios.get(`api/member/check/email/${props}`);

    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function CheckPhone(props: String) {
  try {
    const res = await baseAxios.get(`api/member/check/phoneNumber/${props}`);

    return res.data.data;
  } catch (e) {
    console.error(e);
  }
}

export async function UserSignup(props: any) {
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
  try {
    const res = await baseAxios.post(`api/address/delete`, {
      member_id: props[0],
      address_id: props[1],
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UpdateAddress(props: any) {
  try {
    const res = await baseAxios.put(`api/address/update`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function RegisterAllergy(props: any) {
  try {
    const res = await baseAxios.post(`api/allergy/check/${props.memberId}/1`, props.allergy);

    return res;
  } catch (e) {
    console.error(e);
  }
}
export async function RegisterHate(props: any) {
  try {
    const res = await baseAxios.post(`api/allergy/check/${props.memberId}/0`, props.hate);

    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function MyAllergy(props: any) {
  try {
    const res = await baseAxios.get(`api/member_allergy/${props}/1`);

    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function MyHate(props: any) {
  try {
    const res = await baseAxios.get(`api/member_allergy/${props}/0`);

    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UserInfo() {
  const id = localStorage.getItem("id");
  try {
    const res = await baseAxios.get(`api/member/${id}`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UserUpdate(props: any) {
  const id = localStorage.getItem("id");
  try {
    const res = await baseAxios.put(`api/member/update`, { member_id: id, member: props });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UserDelete(props: any) {
  const id = localStorage.getItem("id");
  try {
    const res = await baseAxios.put(`api/member/delete`, { member_id: id, password: props });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function UserOrder() {
  const id = localStorage.getItem("id");
  try {
    const res = await baseAxios.get(`api/basket/purchase/${id}`);
    return res;
  } catch (e) {
    console.error(e);
  }
}
