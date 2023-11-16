"use client";
import { Tooltip } from "@chakra-ui/react";
import Image from "next/image";
import Link from "next/link";
import { usePathname } from "next/navigation";
import React, { useEffect, useRef, useState } from "react";
import { AiOutlineArrowUp, AiOutlineArrowDown } from "react-icons/ai";
import { BsShieldFillCheck, BsShieldFillExclamation, BsShieldFillMinus, BsShieldFillX } from "react-icons/bs";
import { useMediaQuery } from "react-responsive";

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

  const isDesktop = useMediaQuery({
    query: "(min-width: 1520px)",
  });

  // 이후 useState 훅을 통해 해당 컴포넌트에서 desktop 뷰포트인지 식별할 수 있도록 상태를 선언한다.
  const [desktop, setDesktop] = useState(false);

  useEffect(() => {
    setDesktop(isDesktop);
  }, [isDesktop]);

  return (
    <>
      {desktop && (
        <div
          className="w-[90px] h-[400px] border-[2px] fixed top-[35%] right-[20px] shadow-md bg-white"
          style={{ zIndex: 10 }}
        >
          <div className="my-[10px] border-b-[1px]">
            <div className="flex justify-center text-[12px] mb-[10px]">
              <p>뱃지 정보</p>
            </div>
            <div className="flex justify-evenly">
              <Tooltip label="알러지가 포함되어 있지 않을 때" placement="left">
                <span>
                  <BsShieldFillCheck style={{ fontSize: "20px", color: "#008000" }} />
                </span>
              </Tooltip>
              <Tooltip label="기피식품이 포함되어 있을 때 " placement="left">
                <span style={{ fontSize: "20px", color: "#ffff00" }}>
                  <BsShieldFillExclamation />
                </span>
              </Tooltip>
            </div>
            <div className="flex justify-evenly my-[10px]">
              <Tooltip label="제조시설에 알러지가 포함되어 있을 때 " placement="left">
                <span style={{ fontSize: "20px", color: "#ff8c00" }}>
                  <BsShieldFillMinus />
                </span>
              </Tooltip>
              <Tooltip label="알러지가 포함되어 있을 때" placement="left">
                <span style={{ fontSize: "20px", color: "#ff0000" }}>
                  <BsShieldFillX />
                </span>
              </Tooltip>
            </div>
          </div>
          <p className="mt-[10px] flex justify-center text-[12px]">최근 조회 상품</p>

          <div className="flex justify-center my-[10px]">
            <button onClick={handleButtonUp}>
              <AiOutlineArrowUp />
            </button>
          </div>

          <div ref={scrollContainerRef} className=" h-[200px] overflow-hidden scrollbar-hide">
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
      )}
    </>
  );
}
