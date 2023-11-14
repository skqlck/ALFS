"use client";

import React, { useEffect, useState } from "react";
import { GiHamburgerMenu } from "react-icons/gi";
import Link from "next/link";
import MiddleIcon from "./MiddleIcon";

type Props = {};

const category = [
  { idx: 0, title: "채소" },
  { idx: 1, title: "과일 · 견과 · 쌀" },
  { idx: 2, title: "수산 · 해산 · 건어물" },
  { idx: 3, title: "정육 · 계란" },
  { idx: 4, title: "국 · 반찬 · 메인요리" },
  { idx: 5, title: "샐러드 · 간편식" },
  { idx: 6, title: "면 · 양념 · 오일" },
  { idx: 7, title: "생수 · 음료 · 우유 · 커피" },
  { idx: 8, title: "간식 · 과자 · 떡" },
  { idx: 9, title: "베이커리 · 치즈 · 델리" },
];

export default function BottomNav({}: Props) {
  const [open, setOpen] = useState(false);
  const [menu, setMenu] = useState(false);
  const [isSticky, setIsSticky] = useState(false);

  const handleHover = () => {
    setOpen(true);
  };

  const handleLeave = () => {
    setOpen(false);
  };

  const handleMenu = () => {
    setMenu(true);
  };

  const handleMenuLeave = () => {
    setMenu(false);
  };
  useEffect(() => {
    const handleScroll = () => {
      setIsSticky(window.scrollY > 140);
    };

    window.addEventListener("scroll", handleScroll);

    return () => {
      window.removeEventListener("scroll", handleScroll);
    };
  }, []);

  return (
    <div className="min-w-[1000px] h-[70px] flex justify-center items-center border-b-2 z-[1] bg-white sticky top-0">
      <div className="w-[1000px] h-[70px] flex">
        <div className="flex w-[200px] relative">
          <div
            className="flex items-center hover:text-green-500 w-[100px]"
            onMouseEnter={handleHover}
            onMouseLeave={handleLeave}
          >
            <GiHamburgerMenu className="w-[20px] h-[20px] mb-[3px]" />
            <p className="ml-[10px]">카테고리</p>
          </div>
          {(menu || open) && (
            <div
              data-aos="fade-right"
              className="  bg-white absolute top-[69px] border-b-[2px] text-[12px] flex flex-col w-[150px] z-10"
              onMouseEnter={handleMenu}
              onMouseLeave={handleMenuLeave}
            >
              <div className="border-x-[2px]">
                {category.map((item) => (
                  <Link href={`/category/${item.idx}`}>
                    <p key={item.idx} className="hover:text-green-500 ml-[10px] my-[10px]">
                      {item.title}
                    </p>
                  </Link>
                ))}
              </div>
            </div>
          )}
        </div>
        <div className="flex w-[800px] justify-between items-center">
          <Link href="/list">
            <p className=" hover:text-green-500">전체상품</p>
          </Link>
          <p className=" hover:text-green-500">신상품</p>
          <p className=" hover:text-green-500">베스트</p>
          <p className=" hover:text-green-500">대체상품</p>
          <Link href="/bigsale">
            <p className=" hover:text-green-500">특가할인</p>
          </Link>
          <div className="w-[160px]">{isSticky && <MiddleIcon />}</div>
        </div>
      </div>
    </div>
  );
}
