"use client";

import { AlterList, AlterDetail, AlterAll } from "@/app/api/alt/AltPage";
import React, { useState, useEffect } from "react";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";

type Props = {};

const Card = dynamic(() => import("../../_components/card/Card"), {
  loading: () => <Loading />,
  ssr: false,
});

export default function Page({}: Props) {
  const [alterList, setAlterList] = useState<Array<string>>([]);
  const [selectedButtonIndices, setSelectedButtonIndices] = useState<number[]>([]);
  const [totalCnt, setTotalCnt] = useState<number>(0);
  const [alterProduct, setAlterProduct] = useState<any>();
  useEffect(() => {
    const fetchAlterData = async () => {
      const res: any = await AlterList();
      console.log(res);
      setAlterList(res);
      const CatName: Array<string> = res.map((item: any) => item.alternativeName);
      console.log("카테고리이름", CatName);
      const Allres: Array<string> = await AlterAll(CatName);
      console.log("대체식품 전체조회", Allres, Allres);
      setAlterProduct(Allres);
      setTotalCnt(Allres.length);
    };
    fetchAlterData();
  }, []);

  const handleButtonClick = (index: number) => {
    if (selectedButtonIndices.includes(index)) {
      setSelectedButtonIndices(selectedButtonIndices.filter((i) => i !== index));
    } else {
      setSelectedButtonIndices([...selectedButtonIndices, index]);
    }
  };

  return (
    <div className="flex flex-col items-center">
      <span className="text-[30px]">대체식품</span>
      <div className="grid grid-cols-5 gap-4 w-800 border border-[#33C130] p-4">
        {alterList &&
          alterList.map((item: any, index: number) => (
            <button
              key={index}
              onClick={() => handleButtonClick(index)}
              className={`text-center ${selectedButtonIndices.includes(index) ? "text-[#33C130]" : ""}`}
            >
              <div>{item.alternativeName}</div>
            </button>
          ))}
      </div>
      {alterProduct && (
        <div className="Container flex flex-col justify-center w-[1000px] h-auto mt-[124px]">
          총 {totalCnt}건
          <hr />
          <div className="grid grid-cols-3 mx-auto mt-[10px]">
            {alterProduct.map((item: any) => {
              <div key={item.id} className="w-[178px] h-[450px] ml-[44px]">
                <Card
                  name={item.name}
                  image={item.img}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  sale={item.sale}
                  filterCode={item.filterCode}
                />
              </div>;
            })}
          </div>
        </div>
      )}
    </div>
  );
}
