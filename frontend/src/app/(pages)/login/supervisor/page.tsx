"use client";

import { Button, FormControl, Input, useToast } from "@chakra-ui/react";
import { useRouter } from "next/navigation";
import React from "react";
import { useForm } from "react-hook-form";

import { SupervisorLogin } from "@/app/apis/supervisor/supervisor";

type Inputs = {
  identifier: string;
  password: string;
};

export default function Page() {
  const { register, handleSubmit } = useForm<Inputs>();
  const toast = useToast();
  const router = useRouter();

  const handleLogin = async (e: any) => {
    const res = await SupervisorLogin(e);
    console.log(res);
    if (!res) {
      toast({
        title: "잘못된 아이디 혹은 비밀번호 입니다.",
        status: "error",
        duration: 3000,
        isClosable: true,
      });
    } else {
      // await signInWithEmailAndPassword(auth, e.email, e.password);
      // localStorage.setItem("id", res);
      router.push("/supervisor/register");
    }
  };
  return (
    <div className="min-w-[650px] flex justify-center">
      <form onSubmit={handleSubmit(handleLogin)}>
        <FormControl width={650}>
          <div>
            <div className="text-center text-[30px] mt-[50px] mb-[50px]">관리자 로그인</div>
          </div>

          <div className="flex justify-evenly my-[20px]">
            <div className="w-[300px]">
              <Input
                focusBorderColor="green.500"
                borderColor="gray.300"
                placeholder="아이디를 입력해주세요"
                {...register("identifier")}
              />
            </div>
          </div>

          <div className="flex justify-evenly mb-[20px]">
            <div className="w-[300px]">
              <Input
                type="password"
                focusBorderColor="green.500"
                borderColor="gray.300"
                placeholder="비밀번호를 입력해주세요"
                {...register("password")}
                id="password"
              />
            </div>
          </div>

          <div className="flex justify-evenly mt-[50px]">
            <Button width={300} variant="outline" colorScheme="whatsapp" type="submit">
              관리자 로그인
            </Button>
          </div>
        </FormControl>
      </form>
    </div>
  );
}
