"use client";

import React, { useState } from "react";
import Image from "next/image";
import { BsCheckCircle, BsCheckCircleFill } from "react-icons/bs";
import { AiOutlineClose } from "react-icons/ai";
import { AddCount, RemoveCount } from "@/app/apis/cart/CartPage";

type CartItemProps = {
  isCheck: boolean;
  setIsCheck: React.Dispatch<React.SetStateAction<boolean>>;
  member_id: string;
  count: number;
  product: {
    id: number;
    name: string;
    title: string;
    price: number;
    sale: number;
    img: string;
  };
  pack: string;
  isProductVisible: boolean;
  basket_id: number;
};

export default function CartItem({
  isCheck,
  setIsCheck,
  count,
  product,
  member_id,
  isProductVisible,
  pack,
  basket_id,
}: CartItemProps) {
  const toggleCheck = () => {
    setIsCheck(!isCheck);
  };
  const increaseCount = async () => {
    try {
      const res: number = await AddCount(basket_id, member_id);
      setCnt(res);
    } catch (error) {
      console.error("상품 수량 증가 에러:", error);
    }
  };

  const decreaseCount = async () => {
    try {
      const res: number = await RemoveCount(basket_id, member_id);
      setCnt((prevCnt) => Math.max(res, 1));
    } catch (error) {
      console.error("상품 수량 감소 에러:", error);
    }
  };
  const [cnt, setCnt] = useState<number>(count);
  const formattedPrice = new Intl.NumberFormat().format(product.sale * cnt);
  return (
    <div>
      {isProductVisible && (
        <div className="ProductMain h-[112px] flex items-center ml-[10px]">
          {isCheck ? (
            <BsCheckCircleFill className="w-[30px] h-[30px]" style={{ color: "#21A71E" }} onClick={toggleCheck} />
          ) : (
            <BsCheckCircle className="w-[30px] h-[30px]" onClick={toggleCheck} />
          )}

          <Image src={product.img} width={84} height={112} alt="Test Image" className="ml-[36px]" />
          <span className="ml-[25px]">{product.name}</span>
          <div className="ButtonBox w-[114px] h-[38px] flex items-center ml-[36px]">
            <button
              onClick={decreaseCount}
              className="w-[27px] h-[27px] items-center justify-center border-t border-l border-b border-opacity-50"
            >
              -
            </button>
            <div className="Count w-[27px] h-[27px] border-t border-b border-opacity-50 flex items-center justify-center">
              {cnt}
            </div>
            <button
              onClick={increaseCount}
              className="w-[27px] h-[27px] items-center justify-center border-t border-b border-r border-opacity-50"
            >
              +
            </button>
          </div>
          <div className="Price">{formattedPrice}원</div>
          <AiOutlineClose className="ml-auto w-[30px] h-[30px]" />
        </div>
      )}
    </div>
  );
}
