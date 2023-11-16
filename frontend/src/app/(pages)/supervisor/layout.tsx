"use client";

import LoginModal from "@/app/_components/needLogin/LoginModal";
import { Button } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter, usePathname } from "next/navigation";
import React, { useEffect, useState } from "react";
import { AiOutlineRight } from "react-icons/ai";

export default function RootLayout({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const [show, setShow] = useState(false);

  useEffect(() => {
    const supervisor = localStorage.getItem("supervisorId");
    if (!supervisor) {
      setShow(true);
    }
  }, []);

  return (
    <div className="flex justify-center mt-[50px]">
      <div className="w-[1000px] flex">
        <div className="w-[200px] mr-[50px]">
          <p className="text-[30px] mb-[50px]">관리자</p>
          <div className="w-full border-[1px] border-gray-300">
            <Link href="/supervisor/register">
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
            </Link>
            <Link href="/supervisor/product">
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
            </Link>
            <Link href="/supervisor/special-register">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/supervisor/special-register" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  특가상품등록 <AiOutlineRight />
                </p>
              </Button>
            </Link>
            <Link href="/supervisor/special-all">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/supervisor/special-all" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  특가상품관리 <AiOutlineRight />
                </p>
              </Button>
            </Link>
            <Link href="/supervisor/board">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/supervisor/board" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  문의내역관리 <AiOutlineRight />
                </p>
              </Button>
            </Link>
          </div>
        </div>

        <div className="w-[750px]">
          <div className=" border-black border-b-[4px] mb-[50px]">
            <p className="text-[30px] mb-[50px]">
              {pathname === "/supervisor/product" && "상품관리"}
              {pathname === "/supervisor/register" && "상품등록"}
              {pathname === "/supervisor/special-register" && "특가상품등록"}
              {pathname === "/supervisor/special-all" && "특가상품관리"}
              {pathname === "/supervisor/board" && "문의내역관리"}
            </p>
          </div>
          <div>{children}</div>
        </div>
      </div>
      {show && <LoginModal />}
    </div>
  );
}
