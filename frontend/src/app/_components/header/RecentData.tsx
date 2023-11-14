"use client";
import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import React, { useEffect, useRef, useState } from "react";
import { AiOutlineArrowUp, AiOutlineArrowDown } from "react-icons/ai";

type Props = {};

export default function RecentData({}: Props) {
  const [data, setData] = useState([]);
  const pathname = usePathname();
  const scrollContainerRef = useRef<any>(null);

  useEffect(() => {
    const existingDataString = sessionStorage.getItem("productId") || "[]";
    const existingData = JSON.parse(existingDataString);

    setData(existingData);
  }, [pathname]);

  const handleButtonDown = () => {
    // 버튼 클릭시 스크롤 위치 변경
    scrollContainerRef.current.scrollBy({
      top: 75, // 이동하고자 하는 거리
      behavior: "smooth", // 부드럽게 스크롤할지 여부
    });
  };

  const handleButtonUp = () => {
    // 버튼 클릭시 스크롤 위치 변경
    scrollContainerRef.current.scrollBy({
      top: -75, // 이동하고자 하는 거리
      behavior: "smooth", // 부드럽게 스크롤할지 여부
    });
  };

  return (
    <div className="w-[90px] h-[300px] border-[2px] fixed top-[35%] right-[20px] shadow-md bg-white">
      <p className="mt-[10px] flex justify-center text-[12px]">최근 조회 상품</p>

      <div className="flex justify-center my-[10px]">
        <button onClick={handleButtonUp}>
          <AiOutlineArrowUp />
        </button>
      </div>

      <div ref={scrollContainerRef} className=" h-[200px] overflow-hidden scrollbar-hide z-10">
        {data?.map((item: any, idx) => (
          <div key={idx} className="flex justify-center my-[5px]">
            <Link href={`/detail/${item.id}`}>
              <Image src={item.image} width={50} height={50} alt=""></Image>
            </Link>
          </div>
        ))}
      </div>

      <div className="flex justify-center mt-[10px]">
        <button onClick={handleButtonDown}>
          <AiOutlineArrowDown />
        </button>
      </div>
    </div>
  );
}
