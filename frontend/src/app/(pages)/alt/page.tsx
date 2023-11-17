"use client";

import { AlterList, AlterDetail, AlterAll } from "@/app/api/alt/AltPage";
import React, { useState, useEffect } from "react";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";
import Carousel from "@/app/_components/banner/Banner";
import { isStyleProp } from "@chakra-ui/react";

type Props = {};

const Card = dynamic(() => import("../../_components/card/Card"), {
  loading: () => <Loading />,
  ssr: false,
});

export default function Page({}: Props) {
  const [alterList, setAlterList] = useState<Array<string>>([]);
  const [selectedButtonIndices, setSelectedButtonIndices] = useState<number>(0);
  const [totalCnt, setTotalCnt] = useState<number>(0);
  const [alterProduct, setAlterProduct] = useState<any>([]);
  useEffect(() => {
    const fetchAlterData = async () => {
      const res: any = await AlterList();
      setAlterList(res);
      const CatName: Array<string> = res.map((item: any) => item.alternativeName);
      const Allres: Array<string> = await AlterAll(CatName);
      setAlterProduct(Allres);
      setTotalCnt(Allres.length);
    };
    fetchAlterData();
  }, []);

  const handleButtonClick = async (index: number, alternativeName: string) => {
    setSelectedButtonIndices(index);
    const encodedAlternativeName = encodeURIComponent(alternativeName);
    try {
      if (alternativeName !== "전체") {
        const SelectedRes: any = await AlterDetail(encodedAlternativeName);
        setAlterProduct(SelectedRes);
        setTotalCnt(SelectedRes.length);
      } else {
        const CatName: Array<string> = alterList.map((item: any) => item.alternativeName);
        const Allres: Array<string> = await AlterAll(CatName);
        setAlterProduct(Allres);
        setTotalCnt(Allres.length);
      }
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="flex flex-col items-center">
      <Carousel></Carousel>
      <span className="text-[30px]">대체식품</span>
      <span className="text-[20px] opacity-30 mb-[10px]">
        회원님의 알러지 및 기피식품의 영양소를 기반한 대체 식품이에요
      </span>
      <div className="grid grid-cols-5 gap-4 w-800 border border-[#33C130] p-4">
        <button
          key={0}
          onClick={() => handleButtonClick(0, "전체")}
          className={`text-center ${selectedButtonIndices === 0 ? "text-[#33C130]" : ""}`}
        >
          <div>전체보기</div>
        </button>
        {alterList &&
          alterList.map((item: any, index: number) => (
            <button
              key={index + 1}
              onClick={() => handleButtonClick(index + 1, item.alternativeName)}
              className={`text-center ${selectedButtonIndices === index + 1 ? "text-[#33C130]" : ""}`}
            >
              <div>{item.alternativeName}</div>
            </button>
          ))}
      </div>

      <div className="flex flex-col justify-center w-[1000px] h-auto mt-[124px]">
        총 {totalCnt}건
        <hr />
        <div className="grid grid-cols-3 mx-auto mt-[10px]">
          {alterProduct.length > 0 ? (
            alterProduct.map((item: any, index: number) => (
              <div key={item.id} className="w-[178px] h-[450px] ml-[44px]">
                <Card
                  name={item.name}
                  image={item.img}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  sale={item.sale}
                  filterCode={item.filterCode}
                  hates={item.hates}
                  allergies={item.allergies}
                  isSpecial={item.isSpecial}
                />
              </div>
            ))
          ) : (
            <p>데이터가 없습니다.</p>
          )}
        </div>
      </div>
    </div>
  );
}
