"use client";

import React, { useState, useEffect } from "react";
import { GetList, ProductCnt } from "../../api/list/ListPage";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";

const Card = dynamic(() => import("../../_components/card/Card"), {
  loading: () => <Loading />,
  ssr: false,
});

function GetListData() {
  const [memberId, setMemberId] = useState<string>("");
  const [response, setResponse] = useState<any>([]);
  const [totalCnt, setTotalCnt] = useState<number>(0);
  const [page, setPage] = useState<number>(1);
  const [totalPages, setTotalPages] = useState<number>(Math.ceil(totalCnt / 15));
  useEffect(() => {
    const ListData = async () => {
      const member_id: string = localStorage.getItem("id")!;
      setMemberId(member_id);
      const res: any = await GetList(member_id, page);
      const resCnt: any = await ProductCnt();
      setTotalCnt(resCnt);
      setResponse(res);
    };
    ListData();
  }, []);

  useEffect(() => {
    setTotalPages(Math.ceil(totalCnt / 15));
  }, [totalCnt]);

  const handlePageChange = async (newPage: number) => {
    if (newPage >= 1 && newPage <= totalPages) {
      const res: any = await GetList(memberId, newPage);
      setResponse(res);
      setPage(newPage);
    }
  };
  return (
    <>
      {response && (
        <div className="Container flex flex-col justify-center w-[1000px] h-auto mt-[124px]">
          총 {totalCnt}건
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
                  filterCode={item.filterCode}
                />
              </div>
            ))}
          </div>
          <div className="flex justify-center mt-4">
            {page > 1 && (
              <>
                <button onClick={() => handlePageChange(1)} className="mx-2 px-4 py-2 bg-gray-200">
                  {"<<"}
                </button>
                <button onClick={() => handlePageChange(page - 1)} className="mx-2 px-4 py-2 bg-gray-200">
                  {"<"}
                </button>
              </>
            )}
            {Array.from({ length: totalPages }, (_, index) => index + 1).map((pageNum) => (
              <button
                key={pageNum}
                onClick={() => handlePageChange(pageNum)}
                className={`mx-2 px-4 py-2 ${pageNum === page ? "bg-blue-500 text-white" : "bg-gray-200"}`}
              >
                {pageNum}
              </button>
            ))}
            {page < totalPages && (
              <>
                <button onClick={() => handlePageChange(page + 1)} className="mx-2 px-4 py-2 bg-gray-200">
                  {">"}
                </button>
                <button onClick={() => handlePageChange(totalPages)} className="mx-2 px-4 py-2 bg-gray-200">
                  {">>"}
                </button>
              </>
            )}
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
