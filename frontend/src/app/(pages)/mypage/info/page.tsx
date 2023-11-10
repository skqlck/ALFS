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
      {mode ? (
        <>
          <div className=" border-black border-b-[4px]">
            <p className="text-[30px] mb-[50px]">개인정보 수정</p>
          </div>
          <UpdateForm props={data} />
        </>
      ) : (
        <div>
          <div className=" border-black border-b-[4px]">
            <p className="text-[30px] mb-[26px]">개인정보 수정</p>
            <div>개인정보를 수정하기 위해 비밀번호를 입력해주세요.</div>
          </div>

          <form onSubmit={changeInfo} className="mt-[50px]">
            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[300px]">
                <Input
                  type="password"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="비밀번호를 입력해주세요"
                  onChange={(e: any) => setPw(e.target.value)}
                  id="password"
                />
              </div>
            </div>

            <div className="flex justify-evenly mt-[20px]">
              <Button width={300} variant="outline" colorScheme="whatsapp" type="submit">
                비밀번호 확인
              </Button>
            </div>
          </form>
        </div>
      )}
    </>
  );
}
