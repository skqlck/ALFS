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
          <p className="text-[30px] mb-[50px]">관리자</p>
          <div className="w-full border-[1px] border-gray-300">
            <a href="/supervisor/register">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/supervisor/register" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  상품등록 <AiOutlineRight />
                </p>
              </Button>
            </a>
            <a href="/supervisor/product">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/supervisor/product" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  상품관리 <AiOutlineRight />
                </p>
              </Button>
            </a>
          </div>
        </div>

        <div className="w-[750px]">
          <div className=" border-black border-b-[4px] mb-[50px]">
            <p className="text-[30px] mb-[50px]">{pathname === "/supervisor/product" ? "상품관리" : "상품등록"}</p>
          </div>
          <div>{children}</div>
        </div>
      </div>
    </div>
  );
}
