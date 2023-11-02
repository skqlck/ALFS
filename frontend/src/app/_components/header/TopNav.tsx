"use client";

import React, { useEffect, useState } from "react";
import { Menu, MenuButton, MenuList, MenuItem, Button } from "@chakra-ui/react";
import Link from "next/link";
import { useRouter } from "next/navigation";

export default function TopNav() {
  const [id, setId] = useState(false);
  const router = useRouter();

  const handleLogout = () => {
    sessionStorage.clear();
    setId(false);
    router.push("/");
  };

  useEffect(() => {
    if (sessionStorage.getItem("id")) {
      setId(true);
    }
  }, [typeof window !== "undefined" && sessionStorage.getItem("id")]);

  return (
    <div className="min-w-[1000px] h-[30px] mt-[10px] flex justify-center">
      <div className="min-w-[1000px] flex items-center justify-end">
        {id ? (
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

        {/* <Button variant="unstyled">신대혁님</Button> */}

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
