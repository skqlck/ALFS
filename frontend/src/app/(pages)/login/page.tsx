"use client";

import { UserLogin } from "@/app/apis/user/user";
import { Button, FormControl, Input, useToast } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import React from "react";
import { useForm } from "react-hook-form";
import { signIn } from "next-auth/react";

type Inputs = {
  id: string;
  password: string;
};

export default function Page() {
  const { register, handleSubmit } = useForm<Inputs>();
  const toast = useToast();
  const router = useRouter();

  const handleLogin = async (e: any) => {
    const res = await UserLogin(e);

    if (res === null) {
      toast({
        title: "잘못된 아이디 혹은 비밀번호 입니다.",
        status: "error",
        duration: 3000,
        isClosable: true,
      });
    } else {
      await signIn("credentials", {
        identifier: e.id,
        password: e.password,
        userId: res,
        redirect: false,
      });

      router.push("/");
    }
  };
  return (
    <div className="min-w-[650px] flex justify-center">
      <form onSubmit={handleSubmit(handleLogin)}>
        <FormControl width={650}>
          <div>
            <div className="text-center text-[30px] mt-[50px] mb-[50px]">로그인</div>
          </div>

          <div className="flex justify-evenly my-[20px]">
            <div className="w-[300px]">
              <Input
                focusBorderColor="green.500"
                borderColor="gray.300"
                placeholder="아이디를 입력해주세요"
                {...register("id")}
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

          <div className="flex justify-evenly mb-[20px]">
            <div className="w-[300px] flex justify-end">
              <p className="text-[13px]">아이디찾기 |&nbsp; </p>
              <p className="text-[13px]">비밀번호찾기 |&nbsp;</p>
              <Link href="/login/supervisor">
                <p className="text-[13px]">관리자 로그인</p>
              </Link>
            </div>
          </div>

          <div className="flex justify-evenly mt-[50px]">
            <Button width={300} variant="outline" colorScheme="whatsapp" type="submit">
              로그인
            </Button>
          </div>
          <Link href="/signup">
            <div className="flex justify-evenly mt-[20px]">
              <Button width={300} variant="outline" colorScheme="whatsapp" type="button">
                회원가입
              </Button>
            </div>
          </Link>
        </FormControl>
      </form>
    </div>
  );
}
