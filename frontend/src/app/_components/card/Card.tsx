"use client";

import React from "react";
import Image from "next/image";
import { Button } from "@chakra-ui/react";
import { LuShoppingCart } from "react-icons/lu";
import { BsChatLeftDots } from "react-icons/bs";
import { useRouter } from "next/navigation";
import Link from "next/link";

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
  const formattedSale = new Intl.NumberFormat().format(sale);
  const formattedPrice = new Intl.NumberFormat().format(price);
  const discount = Math.round(((price - sale) / price) * 100);
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

      <Link href={{ pathname: `/cart` }}>
        <Button className="w-[178px] h-[32px] rounded-none mt-[18px] border-2 text-[15px] flex items-center justify-center">
          <LuShoppingCart className="mr-2" />
          담기
        </Button>
      </Link>

      <div className="CardFooter w-[178px] h-[80px] mt-[4px]">
        <div className="CardTitle w-[full] text-[13px]">{name}</div>
        <div className="CardSubtitle w-[full] opacity-[0.3] mt-[7px] text-[10px]">{title}</div>
        {price !== sale && (
          <div className="Cost w-[full] h-[14px] mt-[7px] text-[13px] opacity-[0.3] line-through">
            {formattedPrice}원
          </div>
        )}
        <div className="CardPrice w-[full] text-[14px] font-bold mt-[9px]">
          {discount !== 0 && (
            <span className="Discount mr-[5px]" style={{ color: "red" }}>
              {discount}%
            </span>
          )}
          {formattedSale}원
        </div>
      </div>
    </div>
  );
}
