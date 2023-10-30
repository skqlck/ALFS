"use client";

import { Button } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter, usePathname } from "next/navigation";
import React, { useEffect, useState } from "react";
import { AiOutlineRight } from "react-icons/ai";

export default function RootLayout({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();

  return (
    <div className="flex justify-center mt-[50px]">
      <div className="w-[1000px] flex">
        <div className="w-[200px] mr-[50px]">
          <p className="text-[30px] mb-[50px]">MY</p>
          <div className="w-full border-[1px] border-gray-300">
            <a href="/mypage/order">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/mypage/order" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  주문내역 <AiOutlineRight />
                </p>
              </Button>
            </a>
            <a href="/mypage/home">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/mypage/home" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  배송지 관리 <AiOutlineRight />
                </p>
              </Button>
            </a>
            <a href="/mypage/allergy">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/mypage/allergy" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  알러지 관리 <AiOutlineRight />
                </p>
              </Button>
            </a>
            <a href="/mypage/info">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === ("/mypage/info" || "/mypage/info/modify") ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  개인정보수정 <AiOutlineRight />
                </p>
              </Button>
            </a>
          </div>
        </div>

        <div className="w-[750px]">{children}</div>
      </div>
    </div>
  );
}