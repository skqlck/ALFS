"use client";

import React, { useState, useEffect } from "react";
import Link from "next/link";
import { GetList } from "../../api/list/ListPage";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";

const Card = dynamic(() => import("../../_components/card/Card"), {
  loading: () => <Loading />,
  ssr: false,
});

function GetListData() {
  const [memberId, setMemberId] = useState<string>("");
  const [response, setResponse] = useState<any>([]);
  useEffect(() => {
    const ListData = async () => {
      const member_id: string = localStorage.getItem("id")!;
      setMemberId(member_id);
      const res: any = await GetList(memberId);
      console.log("리스트 불러오기", response);
      setResponse(res);
    };
    ListData();
  }, []);

  return (
    <>
      {response && (
        <div className="Container flex flex-col justify-center w-[1000px] h-auto mt-[124px]">
          총 {response.length}건
          <>
            <span className="flex justify-end">가격높은순</span>
          </>
          <hr />
          <div className="grid grid-cols-3 mx-auto mt-[10px]">
            {response.map((item: any) => (
              <div key={item.id} className="w-[178px] h-[450px] ml-[44px]">
                <Card
                  name={item.name}
                  image={item.img}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  sale={item.sale}
                  delivery={item.delivery}
                  member_id={memberId}
                />
              </div>
            ))}
          </div>
        </div>
      )}
    </>
  );
}
export default function Page() {
  return (
    <>
      <div className="flex flex-col items-center">
        <GetListData />
      </div>
    </>
  );
}
