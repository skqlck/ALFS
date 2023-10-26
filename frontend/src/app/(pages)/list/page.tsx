import React from "react";
import ListData from "../../_asset/data/item_List.json";
import Card from "@/app/_components/card/Card";
import Link from "next/link";

export default function Page() {
  return (
    <div className="Container w-[800px] h-auto mt-[124px] ml-[418px]">
      총 {ListData.length}건
      <>
        <span className="flex justify-end">가격높은순</span>
      </>
      <hr />
      <div className="grid grid-cols-3 mx-auto mt-[10px]">
        {ListData.map((item: any) => (
          <div key={item.id} className="w-[178px] h-[450px] ml-[44px]">
            <Link href={{ pathname: `/detail/${item.id}` }}>
              <Card
                name={item.name}
                image={item.images}
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
  );
}
