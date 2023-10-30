"use client";

import React, { useState } from "react";
import { BsCheckCircle, BsCheckCircleFill } from "react-icons/bs";
import { GrLocation } from "react-icons/gr";
import { IoIosArrowUp, IoIosArrowDown } from "react-icons/io";
import { AiOutlineClose } from "react-icons/ai";
import SnowImage from "../../_asset/img/SnowImage.svg";
import Image from "next/image";
import TestDetail from "../../_asset/img/TestDetail.png";
type Props = {};

export default function Page({}: Props) {
  const [count, setCount] = useState<number>(1);
  const [isProductVisible, setProductVisible] = useState<boolean>(true);
  const changeCount = (operator: string) => {
    if (operator === "+") {
      setCount(count + 1);
    } else if (operator === "-" && count > 1) {
      setCount(count - 1);
    }
  };
  const [selectedAll, setSelectedAll] = useState<boolean>(true);
  const toggleProductVisibility = () => {
    setProductVisible(!isProductVisible);
  };
  return (
    <div className="Container mt-[76px] flex flex-col items-center">
      <span className="text-[36px]">장바구니</span>
      <div className="MainBox w-[1097px] mt-[20px] ml-[149px] flex">
        <div className="PdBox w-[800px] h-[3000px] mr-[25px]">
          <div className="SelectAllBox flex items-center text-[20px] ml-[10px] mb-[10px]">
            <BsCheckCircle className="w-[30px] h-[30px]" />
            <span className="ml-[15px] mr-[20px]">전체선택(1/2)</span>
            <span className="opacity-30">|</span>
            <span className="ml-[20px]">선택삭제</span>
          </div>
          <hr className="border-black" />
          <div className="Product flex items-center justify-between">
            <div className="flex items-center">
              <Image src={SnowImage} width={50} height={50} className="SnowImg" alt="snow Img" />
              <span className="ml-[20px]">냉장상품</span>
            </div>
            <div onClick={toggleProductVisibility}>
              {isProductVisible ? (
                <IoIosArrowUp className="w-[30px] h-[30px]" />
              ) : (
                <IoIosArrowDown className="w-[30px] h-[30px]" />
              )}
            </div>
          </div>
          {isProductVisible && (
            <div className="ProductMain h-[112px] flex items-center ml-[10px]">
              <BsCheckCircleFill className="w-[30px] h-[30px]" style={{ color: "#21A71E" }} />
              <Image src={TestDetail} width={84} height={112} alt="Test Image" className="ml-[36px]" />
              <span className="ml-[25px]">[압구정주꾸미] 주꾸미 볶음 300g</span>
              <div className="ButtonBox w-[114px] h-[38px] flex items-center ml-[36px]">
                <button
                  onClick={() => changeCount("-")}
                  className="w-[27px] h-[27px] items-center justify-center border-t border-l border-b border-opacity-50"
                >
                  -
                </button>
                <div className="Count w-[27px] h-[27px] border-t border-b border-opacity-50 flex items-center justify-center">
                  {count}
                </div>
                <button
                  onClick={() => changeCount("+")}
                  className="w-[27px] h-[27px] items-center justify-center border-t border-b border-r border-opacity-50"
                >
                  +
                </button>
              </div>
              <div className="Price">6,300원</div>
              <AiOutlineClose className="ml-auto w-[30px] h-[30px]" />
            </div>
          )}
          {/* <BsCheckCircleFill style={{ color: "#21A71E" }} /> */}
        </div>
        <div className="orderBox w-[272px] h-[463px] sticky left-[1100px] top-[100px]">
          <div className="RightBox w-[272px] border border-opacity-30">
            <div className="OrderTop w-[272px] p-[20px]">
              <div className="Address text-[20px] flex items-center">
                <GrLocation />
                <span className="ml-[5px]">배송지</span>
              </div>
              <div className="text-[20px]">
                광주 광산구 하남산단 <br />
                6번로 107
              </div>
              <button className="SubmitBtn w-[225px] h-[42px] mt-[11px] mb-[11px] flex items-center justify-center text-[#33C130] text-[12px] border border-[#33C130]">
                배송지 변경
              </button>
            </div>
            <hr />
            <div className="OrderBottom w-[272px] p-[20px] text-[16px]">
              <div className="flex justify-between mb-[10px]">
                <span>상품금액</span>
                <span>26,700원</span>
              </div>
              <div className="flex justify-between mb-[10px]">
                <span>상품할인금액</span>
                <span>-8,010원</span>
              </div>
              <div className="flex justify-between">
                <span>배송비</span>
                <span>+3,000원</span>
              </div>
            </div>
            <hr />
            <div className="OrderPrice w-[272px] p-[20px] text-[16px]">
              <div className="flex justify-between">
                <span>결제예정금액</span>
                <b>
                  <span>21,690원</span>
                </b>
              </div>
            </div>
          </div>
          <button className="Submit w-[272px] h-[62px] mt-[11px] flex items-center justify-center bg-[#33C130] text-white">
            장바구니 담기
          </button>
        </div>
      </div>
    </div>
  );
}
