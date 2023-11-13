"use client";

import React, { useState, useEffect } from "react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import Link from "next/link";
import AddToCartFromList from "../modal/AddToCartFromList";
import { BsShieldFillCheck, BsShieldFillExclamation, BsShieldFillMinus, BsShieldFillX } from "react-icons/bs";
type CardProps = {
  name: string;
  image: string;
  id: number;
  title: string;
  price: number;
  sale: number;
  delivery: string;
  member_id: string;
  filterCode: Array<number>;
};

export default function Card({ name, image, id, title, price, sale, delivery, member_id, filterCode }: CardProps) {
  const formattedSale = new Intl.NumberFormat().format(sale);
  const formattedPrice = new Intl.NumberFormat().format(price);
  const discount = Math.round(((price - sale) / price) * 100);
  const router = useRouter();
  const [filter, setFilter] = useState<Array<number>>([0, 0, 0, 0]);
  const [filtered, setFiltered] = useState<boolean>(false);
  const moveDetail = (id: number) => {
    router.push(`/detail/${id}`);
  };
  useEffect(() => {
    console.log(filterCode);
    const updatedFilter = [...filter];
    filterCode.forEach((index) => {
      updatedFilter[index] = 1;
    });
    setFilter(updatedFilter);
    setFiltered(true);
  }, []);
  return (
    <div className="w-[178px] h-[450px]">
      <Link href={{ pathname: `/detail/${id}` }}>
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
      </Link>
      <div className="AddToCartFromList">
        <AddToCartFromList id={id} image={image} name={name} price={price} sale={sale} member_id={member_id} />
      </div>

      <Link href={{ pathname: `/detail/${id}` }}>
        <div className="CardFooter w-[178px] h-[80px] mt-[4px]">
          <div className="flex">
            {filtered && filter[3] === 1 && (
              <span className="mr-[5px]">
                <BsShieldFillCheck style={{ fontSize: "20px", color: "#008000" }} />
              </span>
            )}
            {filtered && filter[0] === 1 && (
              <span className="mr-[5px]" style={{ fontSize: "20px", color: "#ffff00" }}>
                <BsShieldFillExclamation />
              </span>
            )}
            {filtered && filter[2] === 1 && (
              <span className="mr-[5px]" style={{ fontSize: "20px", color: "#ff8c00" }}>
                <BsShieldFillMinus />
              </span>
            )}
            {filtered && filter[1] === 1 && (
              <span className="mr-[5px]" style={{ fontSize: "20px", color: "#ff0000" }}>
                <BsShieldFillX />
              </span>
            )}
          </div>
          <div className="CardTitle w-[full] text-[13px] mt-[5px]">{name}</div>
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
      </Link>
    </div>
  );
}
