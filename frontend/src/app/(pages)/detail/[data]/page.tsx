"use client";

import React, { useState } from "react";
import Image from "next/image";
import DetailData from "../../../_asset/data/item_Details.json";
import { useParams } from "next/navigation";

type Props = {};

export default function Page({}: Props) {
  const [count, setCount] = useState<number>(1);
  const id = useParams();
  console.log("아이디:", id);
  const changeCount = (operator: string) => {
    if (operator === "+") {
      setCount(count + 1);
    } else if (operator === "-" && count > 1) {
      setCount(count - 1);
    }
  };
  const data: any = DetailData.find((item) => item.id === id.data);
  const discount = Math.round(((data.price - data.sale) / data.price) * 100);
  const sale = new Intl.NumberFormat().format(data.sale);
  const price = new Intl.NumberFormat().format(data.price);
  const formattedPrice = new Intl.NumberFormat().format(data.sale * count);
  return (
    <>
      <div className="DetailBox whitespace-pre-line w-[1130px] h-[full] mt-[109px] ml-[149px] flex">
        <div className="ImgBox w-[414px] h-[612px]">
          <Image src={data.images.main_img} width={414} height={622} className="DetailImg" alt="detail Img" />
        </div>
        <div className="DetailDescriptionBox w-[633px] ml-[83px]">
          <div className="Title w-[633px] h-[26px] text-[24px]">{data.name}</div>
          <div className="Subtitle w-[633px] h-[26px] text-[16px] opacity-[0.3] mt-[7px]">{data.title}</div>
          <div className="Price w-[633px] h-[26px] mt-[7px] text-[20px]">
            {discount !== 0 && (
              <span className="Discount" style={{ color: "red" }}>
                {discount}%
              </span>
            )}
            {sale}원
          </div>
          {data.price !== data.sale && (
            <div className="Cost w-[full] h-[14px] mt-[7px] text-[13px] opacity-[0.3] line-through">{price}원</div>
          )}
          <div className="Origin w-[full] h-[26px] text-[20px] mt-[7px]">원산지 : 상세정보 참조</div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center mt-[31px] text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">배송</div>
            <div className="DeliveryContents w-[503px] h-[full]">{data.delivery}</div>
          </div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">판매자</div>
            <div className="DeliveryContents w-[503px] h-[full]">{data.seller}</div>
          </div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">포장타입</div>
            <div className="DeliveryContents w-[503px] h-[full]">{data.package}</div>
          </div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">판매단위</div>
            <div className="DeliveryContents w-[503px] h-[full]">{data.count}</div>
          </div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">중량/용량</div>
            <div className="DeliveryContents w-[503px] h-[full]">{data.weight}</div>
          </div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">알레르기정보</div>
            <div className="DeliveryContents w-[503px] h-[full]">{data.allergy}</div>
          </div>
          <div className="Delivery w-[full] min-h-[67px] border-t border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">유통기한(또는 소비기한) 정보</div>
            <div className="DeliveryContents w-[503px] h-[full]">
              수령일 포함 180일 이상 남은 제품을 보내드립니다.(총 유통기한 365일)
            </div>
          </div>
          <div className="Delivery w-[full] min-h-[87px] border-t border-b border-opacity-10 flex items-center text-[15px]">
            <div className="DeliveryTitle w-[130px] h-[full]">상품선택</div>
            <div className="DeliveryContents w-[503px] h-[full] pl-[5px] ml-[60px] mt-[13px] border border-opacity-10">
              {data.name}
              <br />
              <div className="flex justify-between items-center w-[full] h-[40px]">
                <div className="ButtonBox w-[81px] h-[27px] flex">
                  <button
                    onClick={() => changeCount("-")}
                    className="w-[27px] h-[27px] items-center justify-center border-t border-l border-b border-opacity-50"
                  >
                    -
                  </button>
                  <div className="Count w-[27px] h-[27px] border-t border-b border-opacity-50 flex items-center justify-center">
                    {count}
                  </div>
                  <button
                    onClick={() => changeCount("+")}
                    className="w-[27px] h-[27px] items-center justify-center border-t border-b border-r border-opacity-50"
                  >
                    +
                  </button>
                </div>
                <div className="flex ">
                  {data.price !== data.sale && <span className="line-through opacity-[0.5] mr-[10px]">8,900</span>}
                  <span className="mr-[45px]">{sale}원</span>
                </div>
              </div>
            </div>
          </div>
          <div className="Totalprice mt-[36px] h-[59px] flex justify-end items-end">
            <span className="text-[15px] pb-[10px]">총 상품금액 : </span>
            <span className="text-[40px]">{formattedPrice}원</span>
          </div>
          <div className="Submit w-[633px] min-h-[62px] flex justify-end">
            <button className="SubmitBtn w-[472px] h-[62px] mt-[11px] flex items-center justify-center bg-[#33C130] text-white">
              장바구니 담기
            </button>
          </div>
        </div>
      </div>
      <div className="Information w-[1145px] h-[full] mx-auto mt-[30px]">
        <hr />
        <Image
          src={data.images.description_img}
          layout="responsive"
          width={1}
          height={1}
          className="DescriptionImg mt-[10px]"
          alt="description Img"
        />
        <div className="nameBox flex flex-col items-center mt-[30px] mb-[30px]">
          <span className="InfoTitle text-[36px] opacity-80">{data.title}</span>
          <span className="InfoName text-[24px]">{data.name}</span>
        </div>
        <hr />
        <div className="InfoContent flex flex-col items-center p-[20px] mt-[30px] mb-[30px]">{data.content}</div>
        <hr />
        <div>
          <Image
            src={data.images.ingredient_img}
            layout="responsive"
            width={1}
            height={1}
            className="IngredientImg mt-[10px]"
            alt="Ingredient Img"
          />
        </div>
      </div>
    </>
  );
}
