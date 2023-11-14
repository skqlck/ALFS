"use client";

import { UserOrder } from "@/app/api/user/user";
import Image from "next/image";
import React, { useEffect, useRef, useState } from "react";

interface OrderItem {
  basket_id: number;
  count: number;
  purchase: number;
  date: string;
  img: string;
  name: string;
  sale: number;
}

export default function Page() {
  const [dateGroups, setDateGroups] = useState<{ [date: string]: OrderItem[] }>({});

  const GetOrder = async () => {
    try {
      const res = await UserOrder();
      const data = res?.data.data || [];

      const updatedDateGroups: { [date: string]: OrderItem[] } = { ...dateGroups };

      data.reverse().forEach((item: OrderItem) => {
        const date = item.date;
        if (date in updatedDateGroups) {
          updatedDateGroups[date].push(item);
        } else {
          updatedDateGroups[date] = [item];
        }
      });
      console.log(updatedDateGroups);
      setDateGroups(updatedDateGroups);
    } catch (error) {
      console.error("Error fetching user orders:", error);
    }
  };

  useEffect(() => {
    GetOrder();
  }, []);

  return (
    <div>
      <div className=" border-black border-b-[4px]">
        <p className="text-[30px] mb-[50px]">나의 주문내역</p>
      </div>

      {Object.entries(dateGroups).map(([date, items]) => (
        <>
          <p className="text-[20px] mt-[20px] border-b-[2px]">{date}</p>
          <div key={date} className="mt-[30px]">
            {items.map((item: any) => (
              <div key={item.basket_id} className="border-[1px] mb-[30px] shadow-sm flex items-center">
                <Image src={item.product.img} alt="" width={100} height={100}></Image>
                <div className="ml-[20px]">
                  <p>{item.product.name}</p>
                  <p>구매 수량 : {item.count}</p>
                  <p>구매 가격 : {item.purchase}원</p>
                  <p>구매 날짜 : {item.date}</p>
                </div>
              </div>
            ))}
          </div>
        </>
      ))}
    </div>
  );
}
