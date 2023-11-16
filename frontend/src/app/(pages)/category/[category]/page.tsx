"use client";

import { CategorizedList, CategorizedFilterdList } from "@/app/api/categorizedlist/CategorizedList";
import { useParams, usePathname } from "next/navigation";
import React, { useEffect, useState } from "react";
import Card from "@/app/_components/card/Card";
import Carousel from "@/app/_components/banner/Banner";
import Link from "next/link";

export default function Page() {
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
  const pathname = usePathname();
  const params = useParams();
  const [memberId, setMemberId] = useState<string>("");
  const [categoryId, setcategoryId] = useState<string>("");
  const [categorizedList, setCategorizedList] = useState<any>([]);
  useEffect(() => {
    const initialize = async () => {
      const member_id = localStorage.getItem("id")!;
      const response: any = !member_id
        ? await CategorizedList(params.category[0])
        : await CategorizedFilterdList(member_id, params.category[0]);
      setMemberId(member_id);
      setcategoryId(params.category[0]);
      setCategorizedList(response);
    };
    initialize();
  }, []);
  return (
    <div className="flex flex-col items-center">
      <div className="Container flex flex-col justify-center items-center w-[1000px] h-auto">
        <Carousel></Carousel>
        <div className="flex mt-[50px]">
          <div className="mt-[68px]">
            <div className="h-auto border-[2px] sticky top-[100px] ">
              {category.map((item) => (
                <Link href={`/category/${item.idx}`} key={item.idx}>
                  <p
                    key={item.idx}
                    className="hover:text-green-500 ml-[10px] my-[10px] mr-[10px]"
                    style={{ color: pathname === `/category/${item.idx}` ? "green" : "your-default-color" }}
                  >
                    {item.title}
                  </p>
                </Link>
              ))}
            </div>
          </div>

          <div className="w-[800px]">
            <div className="mt-3 mb-4">
              <h1 className=" text-center text-4xl">
                <b>{categoryId && category[Number(categoryId)].title}</b>
              </h1>
            </div>
            {categorizedList && (
              <div className=" grid grid-cols-3">
                {categorizedList.map((item: any) => (
                  <div key={item.id} className="w-[178px] h-[450px] ml-[44px]">
                    <Card
                      name={item.name}
                      image={item.img}
                      id={item.id}
                      title={item.title}
                      price={item.price}
                      sale={item.sale}
                      filterCode={!item.filterCode ? [] : item.filterCode}
                      hates={item.hates}
                      allergies={item.allergies}
                    />
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      </div>
    </div>
  );
}
