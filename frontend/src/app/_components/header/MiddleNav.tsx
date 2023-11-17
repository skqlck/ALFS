"use client";

import React, { useState } from "react";
import Logo from "../../_asset/img/Logo.jpg";
import Image from "next/image";
import { AiOutlineSearch } from "react-icons/ai";
import { Button, Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import Link from "next/link";

import MiddleIcon from "./MiddleIcon";
import { useForm } from "react-hook-form";
import { useRouter } from "next/navigation";

type Inputs = {
  search: string;
};

export default function MiddleNav() {
  const { register, handleSubmit } = useForm<Inputs>();
  const router = useRouter();

  const sendData = (e: any) => {
    router.push(`/search?data=${e.search}`);
  };
  return (
    <div className="min-w-[1000px] h-[100px] justify-center flex ">
      <div className="min-w-[1000px] flex items-center justify-between">
        <Link href="/">
          <div className="flex justify-center items-center">
            <Image src={Logo} width={50} height={50} alt="" />
            <p className="text-[30px] ml-[10px]">
              <b style={{ fontFamily: "omyu_pretty" }}>ALFS</b>
            </p>
          </div>
        </Link>

        <form onSubmit={handleSubmit(sendData)}>
          <InputGroup size="lg" width={400}>
            <Input type="text" placeholder="검색어를 입력하세요" focusBorderColor="green.500" {...register("search")} />

            <InputRightElement display="flex" justifyContent="center" alignItems="center">
              <Button variant="unstyled" type="submit">
                <AiOutlineSearch className="w-[40px] h-[40px]" />
              </Button>
            </InputRightElement>
          </InputGroup>
        </form>

        <MiddleIcon></MiddleIcon>
      </div>
    </div>
  );
}
