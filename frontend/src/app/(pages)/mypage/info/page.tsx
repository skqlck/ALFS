"use client";

import { Button, Input } from "@chakra-ui/react";
import React, { useState, useEffect } from "react";
import { UserInfo } from "@/app/api/user/user";
import UpdateForm from "./_components/UpdateForm";

export default function Page() {
  const [pw, setPw] = useState();
  const [data, setData] = useState<any>([]);
  const [mode, setMode] = useState(false);
  const changeInfo = (e: any) => {
    e.preventDefault();
    setMode(true);
  };
  const GetData = async () => {
    const res = await UserInfo();
    setData(res?.data.data);
  };
  useEffect(() => {
    GetData();
  }, []);
  return (
    <>
      <div className=" border-black border-b-[4px]">
        <p className="text-[30px] mb-[50px]">개인정보 수정</p>
      </div>
      <UpdateForm props={data} />
    </>
  );
}
