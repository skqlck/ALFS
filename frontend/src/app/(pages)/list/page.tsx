import React from "react";
import Card from "@/app/_components/card/Card";
import Link from "next/link";
import { GetList } from "../../api/list/ListPage";

async function GetListData() {
  const response: any = await GetList();
  console.log(response);

  return (
    <>
      <div className="Container w-[800px] h-auto mt-[124px] ml-[418px]">
        총 {response.length}건
        <>
          <span className="flex justify-end">가격높은순{response.data}</span>
        </>
        <hr />
        <div className="grid grid-cols-3 mx-auto mt-[10px]">
          {response.map((item: any) => (
            <div key={item.id} className="w-[178px] h-[450px] ml-[44px]">
              <Link href={{ pathname: `/detail/${item.id}` }}>
                <Card
                  name={item.name}
                  image={item.img}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  sale={item.sale}
                  delivery={item.delivery}
                />
              </Link>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
export default function Page() {
  return (
    <>
      <React.Suspense fallback={<div>Loading...</div>}>
        <GetListData />
      </React.Suspense>
    </>
  );
}
