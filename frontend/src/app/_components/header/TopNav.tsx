"use client";

import React from "react";
import { Menu, MenuButton, MenuList, MenuItem, Button } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useSession, signOut } from "next-auth/react";

export default function TopNav() {
  const router = useRouter();
  const { data: session } = useSession();

  const handleLogout = () => {
    signOut({ redirect: false });
    localStorage.setItem("id", "null");
    router.push("/");
  };

  return (
    <div className="min-w-[1000px] h-[30px] mt-[10px] flex justify-center">
      <div className="min-w-[1000px] flex items-center justify-end">
        {session ? (
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
