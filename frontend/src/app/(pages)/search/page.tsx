"use client";

import Carousel from "@/app/_components/banner/Banner";
import Card from "@/app/_components/card/Card";
import { ProductSearch } from "@/app/api/list/ListPage";
import { useSearchParams } from "next/navigation";
import React, { useEffect, useState } from "react";

type Props = {};

export default function Page({}: Props) {
  const params = useSearchParams();
  const search = params.get("data");
  const [data, setData] = useState<any>();

  const GetData = async (e: string) => {
    const res = await ProductSearch(e);
    setData(res);
  };

  useEffect(() => {
    if (search) {
      GetData(search);
    }
  }, [search]);

  return (
    <div className="min-w-[1000px] flex flex-col items-center">
      <Carousel />
      <div className="flex flex-col justify-center items-center w-[1000px] h-auto">
        {data?.length > 0 ? (
          <>
            <p className="w-[800px] mt-[50px] mb-[50px] text-start text-[30px] border-b-[1px]">
              <b>{`'${search}'`}</b> 에 대한 검색결과 <b>{data.length}</b> 건의 상품이 검색되었습니다.
            </p>

            <div className=" grid grid-cols-3">
              {data.map((item: any) => (
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
                    isSpecial={item.isSpecial}
                  />
                </div>
              ))}
            </div>
          </>
        ) : (
          <div className="h-[500px] flex justify-center items-center">검색된 상품이 없습니다.</div>
        )}
      </div>
    </div>
  );
}
