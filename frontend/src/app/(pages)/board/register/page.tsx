"use client";

import PropsModal from "@/app/_components/modal/PropsModal";
import { BoardAdd } from "@/app/api/board/Board";
import { Button, Input, Textarea } from "@chakra-ui/react";
import React, { useState } from "react";
import { useForm } from "react-hook-form";

type Inputs = {
  title: string;
  content: string;
};

export default function Page() {
  const { register, handleSubmit } = useForm<Inputs>();
  const [show, setShow] = useState(false);

  const sendData = async (e: any) => {
    const res = await BoardAdd(e);
    setShow(true);
  };

  return (
    <>
      <div className=" border-black border-b-[4px] mb-[30px]">
        <p className="text-[30px] mb-[50px]">문의 작성</p>
      </div>
      <div className="w-[750px] flex justify-center ">
        <form onSubmit={handleSubmit(sendData)}>
          <div className="w-[600px]">
            <div className="flex justify-center my-[20px]">
              <div className="w-[100px] flex items-center">
                제목<p className="text-red-500">*</p>
              </div>
              <Input
                placeholder="제목을 입력해주세요."
                width={500}
                focusBorderColor="green.500"
                {...register("title")}
              />
            </div>
            <div className="flex justify-center border-b-[1px] ">
              <div className="w-[100px] flex">
                내용<p className="text-red-500">*</p>
              </div>
              <Input
                placeholder="내용을 입력해주세요."
                width={500}
                height={300}
                focusBorderColor="green.500"
                marginBottom="20px"
                {...register("content")}
              />
            </div>
            <div className="flex justify-center mt-[30px]">
              <Button variant="outline" colorScheme="whatsapp" type="submit">
                문의 등록
              </Button>
            </div>
          </div>
        </form>
      </div>
      {show && <PropsModal props="문의가 작성되었습니다." />}
    </>
  );
}
