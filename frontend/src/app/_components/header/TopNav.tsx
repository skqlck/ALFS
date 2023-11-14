"use client";

import React, { useEffect, useState } from "react";
import { Button, useToast } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function TopNav() {
  const router = useRouter();
  const toast = useToast({ position: "top" });
  const [memberId, setMemberId] = useState<any>();
  const [superId, setSuperId] = useState<any>();
  const handleLogout = () => {
    toast({
      title: "로그아웃 되었습니다.",
      status: "success",
      duration: 3000,
      isClosable: true,
    });
    localStorage.removeItem("id");
    localStorage.removeItem("supervisorId");
    setMemberId("");
    setSuperId("");
    router.push("/");
  };

  useEffect(() => {
    const id = localStorage.getItem("id");
    const sup = localStorage.getItem("supervisorId");
    setMemberId(id);
    setSuperId(sup);
  }, []);

  return (
    <div className="min-w-[1000px] h-[30px] mt-[10px] flex justify-center">
      <div className="min-w-[1000px] flex items-center justify-end">
        {memberId ? (
          <>
            <Button variant="unstyled" marginRight="10px" onClick={handleLogout}>
              로그아웃
            </Button>
            <Link href="/mypage/order">
              <Button variant="unstyled">마이페이지</Button>
            </Link>
          </>
        ) : superId ? (
          <>
            <Button variant="unstyled" marginRight="10px" onClick={handleLogout}>
              로그아웃
            </Button>
            <Link href="/supervisor/register">
              <Button variant="unstyled">관리자페이지</Button>
            </Link>
          </>
        ) : (
          <>
            <Link href="/login">
              <Button variant="unstyled" marginRight="10px">
                로그인
              </Button>
            </Link>
            <Link href="/signup">
              <Button variant="unstyled">회원가입</Button>
            </Link>
          </>
        )}
      </div>
    </div>
  );
}
