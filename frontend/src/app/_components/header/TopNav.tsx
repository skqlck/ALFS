"use client";

import React, { useEffect, useState } from "react";
import { Menu, MenuButton, MenuList, MenuItem, Button, useToast } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { getCookie, deleteCookie } from "cookies-next";

export default function TopNav() {
  const router = useRouter();
  const toast = useToast();
  const [id, setId] = useState<any>();
  const handleLogout = () => {
    toast({
      title: "로그아웃 되었습니다.",
      status: "success",
      duration: 3000,
      isClosable: true,
    });
    localStorage.setItem("id", "null");
    window.location.replace("/");
  };

  useEffect(() => {
    const id = localStorage.getItem("id");
    setId(id);
  }, []);

  return (
    <div className="min-w-[1000px] h-[30px] mt-[10px] flex justify-center">
      <div className="min-w-[1000px] flex items-center justify-end">
        {id !== "null" ? (
          <>
            <Button variant="unstyled" marginRight="10px" onClick={handleLogout}>
              로그아웃
            </Button>
            <Link href="/mypage/order">
              <Button variant="unstyled" marginRight="10px">
                마이페이지
              </Button>
            </Link>
          </>
        ) : (
          <>
            <Link href="/login">
              <Button variant="unstyled" marginRight="10px">
                로그인
              </Button>
            </Link>
            <Link href="signup">
              <Button variant="unstyled" marginRight="10px">
                회원가입
              </Button>
            </Link>
          </>
        )}

        <Menu>
          <MenuButton>고객센터</MenuButton>
          <MenuList>
            <MenuItem>1:1 문의</MenuItem>
            <MenuItem>상품 문의</MenuItem>
          </MenuList>
        </Menu>
      </div>
    </div>
  );
}
