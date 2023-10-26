"use client";

import React from "react";
import Image from "next/image";
import { Button } from "@chakra-ui/react";
import { LuShoppingCart } from "react-icons/lu";
import { BsChatLeftDots } from "react-icons/bs";
import { useRouter } from "next/navigation";

type CardProps = {
  name: string;
  image: string;
  id: number;
  title: string;
  price: number;
  sale: number;
  delivery: string;
};

export default function Card({ name, image, id, title, price, sale, delivery }: CardProps) {
  const formattedPrice = new Intl.NumberFormat().format(price);
  const router = useRouter();
  const moveDetail = (id: number) => {
    router.push(`/detail/${id}`);
  };
  return (
    <div className="w-[178px] h-[450px]">
      <Image
        src={image}
        width={178}
        height={240}
        className={`${id}`}
        alt="product image"
        onClick={() => {
          moveDetail(id);
        }}
      />
      <Button className="w-[178px] h-[32px] rounded-none mt-[18px] border-2 text-[15px] flex items-center justify-center">
        <LuShoppingCart className="mr-2" />
        담기
      </Button>
      <div className="CardFooter w-[178px] h-[80px] mt-[4px]">
        <div className="CardTitle w-[full] text-[13px]">{name}</div>
        <div className="CardSubtitle w-[full] opacity-[0.3] mt-[7px] text-[10px]">{title}</div>
        <div className="CardPrice w-[full] text-[14px] font-bold mt-[9px]">{formattedPrice}원</div>
        <div className="CardComment w-[full] text-[6px] mt-[7px] opacity-0.3 flex items-center">
          <BsChatLeftDots className="mr-0.5" />
          9,999+
        </div>
      </div>
    </div>
  );
}
