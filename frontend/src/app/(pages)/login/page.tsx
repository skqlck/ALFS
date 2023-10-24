"use client";

import React from "react";
import { Input } from "@chakra-ui/react";
import { useForm, SubmitHandler } from "react-hook-form";

type Inputs = {
  name: string;
  id: string;
  password: string;
  birth: number;
  address_1: string;
  address_2: string;
  email: string;
  phone_number: string;
};

export default function Page() {
  const {
    register,
    handleSubmit,
    watch,
    formState: { errors },
  } = useForm<Inputs>();

  const handleSignup = () => {};

  return (
    <div className="min-w-[1000px] flex justify-center m-[100px]">
      <form onSubmit={handleSubmit(handleSignup)}></form>
      <div className="min-w-[800px] h-[70px] flex">
        <div className="w-[100px]  flex items-center">아이디*</div>
        <div className="w-[500px] h-[100px]">
          <Input focusBorderColor="green.400" />
        </div>
        <div className="w-[200px]">asd</div>
      </div>
    </div>
  );
}
