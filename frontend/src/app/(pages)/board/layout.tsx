"use client";

import { Button, useToast } from "@chakra-ui/react";
import Link from "next/link";
import { usePathname } from "next/navigation";
import React, { useEffect, useState } from "react";
import { AiOutlineRight } from "react-icons/ai";
import LoginModal from "@/app/_components/needLogin/LoginModal";

export default function RootLayout({ children }: { children: React.ReactNode }) {
  const pathname = usePathname();
  const [show, setShow] = useState(false);

  useEffect(() => {
    const id = localStorage.getItem("id");
    if (!id) {
      setShow(true);
    }
  }, []);

  return (
    <div className="min-w-[1000px] flex justify-center mt-[50px]">
      <div className="w-[1000px] flex">
        <div className="w-[200px] mr-[50px]">
          <p className="text-[30px] mb-[50px]">고객센터</p>
          <div className="w-full border-[1px] border-gray-300">
            <Link href="/board/all">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/board/all" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  1:1 문의 <AiOutlineRight />
                </p>
              </Button>
            </Link>

            <Link href="/board/register">
              <Button
                width={200}
                height={50}
                variant="ghost"
                color={pathname === "/board/register" ? "green.500" : "your-default-color"}
              >
                <p className="w-full flex text-[20px] justify-between">
                  문의작성 <AiOutlineRight />
                </p>
              </Button>
            </Link>
          </div>
          {show && <LoginModal />}
        </div>

        <div className="w-[750px]">{children}</div>
      </div>
    </div>
  );
}
