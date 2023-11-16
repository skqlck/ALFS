import { baseAxios } from "../Api";

export async function BoardAll() {
  const member_id = localStorage.getItem("id") || 0;
  try {
    const res = await baseAxios.get(`api/board/${member_id}`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function BoardAdd(props: any) {
  const member_id = localStorage.getItem("id");
  try {
    const res = await baseAxios.post(`api/board/add`, {
      member_id: member_id,
      board: props,
    });
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function BoardDetail(board_id: string) {
  const member_id = localStorage.getItem("id");
  try {
    const res = await baseAxios.get(`api/board/${member_id}/${board_id}`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function BoardSupervisorAll() {
  try {
    const res = await baseAxios.get(`api/board/list`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function BoardSupervisorDetail(props: number) {
  try {
    const res = await baseAxios.get(`api/board/supervisor/${props}`);
    return res;
  } catch (e) {
    console.error(e);
  }
}

export async function BoardSupervisorComment(props: any) {
  try {
    const res = await baseAxios.post(`api/board/supervisor`, props);
    return res;
  } catch (e) {
    console.error(e);
  }
}
