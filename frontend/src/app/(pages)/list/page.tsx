import React from "react";
import ListData from "../../_asset/data/item_List.json";
import Card from "@/app/_components/card/Card";
type Props = {};

export default function Page({}: Props) {
  return (
    <div className="Container w-[800px] h-auto mt-[124px] ml-[418px]">
      <div className="grid grid-cols-3 justify-center">
        {ListData.map((item: any) => (
          <div key={item.id} className="w-[178px] h-[450px]">
            <Card
              name={item.name}
              image={item.images}
              id={item.id}
              title={item.title}
              price={item.price}
              sale={item.sale}
              delivery={item.delivery}
            />
          </div>
        ))}
      </div>
    </div>
  );
}
