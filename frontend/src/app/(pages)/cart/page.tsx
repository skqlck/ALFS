"use client";

import React, { useState, useEffect } from "react";
import { BsCheckCircleFill } from "react-icons/bs";
import { GrLocation } from "react-icons/gr";
import Link from "next/link";
import Image from "next/image";
import CartItem from "@/app/_components/cartItem/CartItem";
import { CartList } from "@/app/api/cart/CartPage";
import SnowImage from "../../_asset/img/SnowImage.svg";
import WaterDropImage from "../../_asset/img/WaterDropImage.svg";
import SunImage from "../../_asset/img/SunImage.svg";
import { IoIosArrowUp, IoIosArrowDown } from "react-icons/io";
import { RemoveBasket } from "@/app/api/cart/CartPage";
import { AddressAll } from "@/app/api/user/user";
import TossButton from "./_components/TossButton";

type Props = {};

type CartData = {
  basket_id: number;
  count: number;
  product: {
    id: number;
    name: string;
    title: string;
    price: number;
    sale: number;
    img: string;
  };
  isCheck: boolean;
  pack: string;
};

export default function Page({}: Props) {
  const [memberId, setMemberId] = useState<string>("");
  const [userAddress, setUserAddress] = useState<any>([]);
  const [memberData, setMemberData] = useState<CartData[]>([]);
  const [isFrozenVisible, setFrozenVisible] = useState<boolean>(false);
  const [isRefrigeratedVisible, setRefrigeratedVisible] = useState<boolean>(false);
  const [isRoomTemperatureVisible, setRoomTemperatureVisible] = useState<boolean>(false);
  const [selectedCount, setSelectedCount] = useState<number>(0);
  const [mode, setMode] = useState(0);

  const [receipt, setReceipt] = useState<any>();
  const [payList, setPayList] = useState<any>();

  // 해당 상품의 pack에 따라 나눠서 관리 ( 냉동, 냉장, 상온 )
  const toggleFrozenPV = () => {
    setFrozenVisible(!isFrozenVisible);
  };
  const toggleRefrigeratedPV = () => {
    setRefrigeratedVisible(!isRefrigeratedVisible);
  };
  const toggleRoomTempPV = () => {
    setRoomTemperatureVisible(!isRoomTemperatureVisible);
  };

  //데이터를 받아와서 pack으로 분류하고, 선택 상태를 관리
  useEffect(() => {
    const fetchProductData = async () => {
      try {
        const member_id: string = localStorage.getItem("id")!;
        setMemberId(member_id);
        const response: CartData[] = await CartList();
        setMemberData(response);
        response.forEach((item) => {
          if (item.pack === "냉동") {
            setFrozenVisible(!isFrozenVisible);
          } else if (item.pack === "냉장") {
            setRefrigeratedVisible(!isRefrigeratedVisible);
          } else if (item.pack === "상온") {
            setRoomTemperatureVisible(!isRoomTemperatureVisible);
          }
        });
        const selectedProducts = response.filter((item) => item.isCheck).length;
        const selectedProductsCount = selectedProducts > 0 ? selectedProducts : 0;
        setSelectedCount(selectedProductsCount);

        const addres: any = await AddressAll(member_id);
        const mainAddress = addres.data.data.find((item: any) => item.status === true);
        setUserAddress(mainAddress);
      } catch (error) {
        console.log(error);
      }
    };

    fetchProductData();
  }, []);

  //전체선택 관리
  const [isAllCheck, setIsAllCheck] = useState<boolean>(true);
  const toggleAllProducts = () => {
    const updatedMemberData = memberData.map((item) => ({
      ...item,
      isCheck: !isAllCheck,
    }));
    setMemberData(updatedMemberData);
    const selectedProductsCount = updatedMemberData.filter((item) => item.isCheck).length;
    setSelectedCount(selectedProductsCount);
    setIsAllCheck(!isAllCheck);
  };

  //장바구니 상품 삭제
  const deleteItems = async () => {
    const checkedMember: Array<number> = memberData.filter((item) => item.isCheck).map((item) => item.basket_id);
    const res: any = await RemoveBasket(checkedMember);
    const filteredMemberData = memberData.filter((item) => !checkedMember.includes(item.basket_id));
    const selectedProducts = filteredMemberData ? filteredMemberData.filter((item) => item.isCheck).length : 0;
    const selectedProductsCount = selectedProducts > 0 ? selectedProducts : 0;
    setSelectedCount(selectedProductsCount);
    setMemberData(filteredMemberData);
  };
  const deleteItem = async (basketId: number) => {
    try {
      await RemoveBasket([basketId]);
      const updatedMemberData = memberData.filter((item) => item.basket_id !== basketId);
      const selectedProducts = updatedMemberData ? updatedMemberData.filter((item) => item.isCheck).length : 0;
      const selectedProductsCount = selectedProducts > 0 ? selectedProducts : 0;
      setSelectedCount(selectedProductsCount);
      setMemberData(updatedMemberData);
    } catch (error) {
      console.error("상품 삭제 에러:", error);
    }
  };

  //화면 우측의 총 상품금액, 할인금액, 배송료 관리
  const [totalPrice, setTotalPrice] = useState<number>(0);
  const [totalDiscount, setTotalDiscount] = useState<number>(0);
  const [priceWithoutDelFee, setPriceWithoutDelFee] = useState<number>(0);
  const [deliveryFee, setDeliveryFee] = useState<number>(3000);
  const [result, setResult] = useState<number>(0);

  const toggleCheck = (index: number, isChecked: any) => {
    setMemberData((prevMemberData: CartData[]) => {
      const updatedMemberData = [...prevMemberData];
      updatedMemberData[index].isCheck = isChecked;
      const selectedProductsCount = updatedMemberData.filter((item) => item.isCheck).length;
      setSelectedCount(selectedProductsCount);
      return updatedMemberData;
    });
  };
  useEffect(() => {
    let price = 0;
    let discount = 0;
    memberData.forEach((cartItem) => {
      if (cartItem.isCheck) {
        price += cartItem.product.price * cartItem.count;
        discount += (cartItem.product.price - cartItem.product.sale) * cartItem.count;
      }
    });

    // 배송비 계산
    let fee = price - discount > 50000 ? 0 : 3000;

    // 업데이트된 값들을 상태로 저장
    setTotalPrice(price);
    setTotalDiscount(discount);
    setPriceWithoutDelFee(price - discount);
    setDeliveryFee(fee);
    setResult(price - discount + fee);
  }, [memberData]);

  // 수량을 CartItem 컴포넌트에서 계산한 cnt를 현재 페이지의 count에 적용 ( 비동기화 )
  const setCountInParent = (index: number, newCount: number) => {
    const updatedMemberData = [...memberData];
    updatedMemberData[index] = {
      ...updatedMemberData[index],
      count: newCount,
    };
    setMemberData(updatedMemberData);
  };

  const formattedTotalPrice = new Intl.NumberFormat().format(totalPrice);
  const formattedDiscount = new Intl.NumberFormat().format(totalDiscount);
  const formattedPriceWithoutDelFee = new Intl.NumberFormat().format(priceWithoutDelFee);
  const formattedFee = new Intl.NumberFormat().format(deliveryFee);
  const ResultPrice = new Intl.NumberFormat().format(totalPrice - totalDiscount + deliveryFee);

  const completePay = (e: any) => {
    setMode(1);
    setReceipt(e[0].data);
    setPayList(e[1].success_list);
  };

  const formattedreceiptPrice = (price: number) => {
    return new Intl.NumberFormat().format(price);
  };
  return (
    <>
      {mode === 0 ? (
        <>
          <div className="Container mt-[76px] flex flex-col items-center">
            <span className="text-[36px]">장바구니</span>
            <div className="MainBox w-[1097px] mt-[20px] ml-[149px] flex">
              <div className="PdBox w-[800px] mr-[25px]">
                <div className="SelectAllBox flex items-center text-[20px] ml-[10px] mb-[30px]">
                  <BsCheckCircleFill className="w-[30px] h-[30px] mr-[15px]" style={{ color: "#21A71E" }} />
                  {memberData !== null && selectedCount === memberData.length ? (
                    <span onClick={toggleAllProducts}>
                      전체선택해제 ({selectedCount}/{memberData.length})
                    </span>
                  ) : (
                    <span onClick={toggleAllProducts}>
                      전체선택 ({selectedCount}/{memberData.length})
                    </span>
                  )}
                  <span className="opacity-30 ml-[20px]">|</span>
                  <span className="ml-[20px]" onClick={deleteItems}>
                    선택삭제
                  </span>
                </div>
                {memberData && memberData.some((cartItem) => cartItem.pack === "냉동") && (
                  <div>
                    <hr className="border-black" />
                    <div className="flex items-center justify-between">
                      <div className="flex items-center">
                        <Image src={SnowImage} width={50} height={50} className="SnowImg" alt="snow Img" />
                        <span className="ml-[20px]">냉동상품</span>
                      </div>
                      <div onClick={toggleFrozenPV} className="mr-[23px]">
                        {isFrozenVisible ? (
                          <IoIosArrowUp className="w-[30px] h-[30px]" />
                        ) : (
                          <IoIosArrowDown className="w-[30px] h-[30px]" />
                        )}
                      </div>
                    </div>
                    {memberData &&
                      memberData.map((cartItem: any, index: number) => {
                        if (cartItem.pack === "냉동") {
                          return (
                            <CartItem
                              key={index}
                              product={cartItem.product}
                              count={cartItem.count}
                              member_id={memberId}
                              isCheck={cartItem.isCheck}
                              setIsCheck={(isChecked) => toggleCheck(index, isChecked)}
                              isProductVisible={isFrozenVisible}
                              basket_id={cartItem.basket_id}
                              onCountChange={(newCount: number) => setCountInParent(index, newCount)}
                              onDeleteItem={() => deleteItem(cartItem.basket_id)}
                            />
                          );
                        }
                        return null;
                      })}
                  </div>
                )}
                {memberData && memberData.some((cartItem) => cartItem.pack === "냉장") && (
                  <div>
                    <hr className="border-black" />
                    <div className="flex items-center justify-between">
                      <div className="flex items-center">
                        <Image
                          src={WaterDropImage}
                          width={50}
                          height={50}
                          className="WaterDropImg"
                          alt="waterdrop Img"
                        />
                        <span className="ml-[20px]">냉장상품</span>
                      </div>
                      <div onClick={toggleRefrigeratedPV} className="mr-[23px]">
                        {isRefrigeratedVisible ? (
                          <IoIosArrowUp className="w-[30px] h-[30px]" />
                        ) : (
                          <IoIosArrowDown className="w-[30px] h-[30px]" />
                        )}
                      </div>
                    </div>
                    {memberData &&
                      memberData.map((cartItem: any, index: number) => {
                        if (cartItem.pack === "냉장") {
                          return (
                            <CartItem
                              key={index}
                              product={cartItem.product}
                              count={cartItem.count}
                              member_id={memberId}
                              isCheck={cartItem.isCheck}
                              setIsCheck={(isChecked) => toggleCheck(index, isChecked)}
                              isProductVisible={isRefrigeratedVisible}
                              basket_id={cartItem.basket_id}
                              onCountChange={(newCount: number) => setCountInParent(index, newCount)}
                              onDeleteItem={() => deleteItem(cartItem.basket_id)}
                            />
                          );
                        }
                        return null;
                      })}
                  </div>
                )}
                {memberData && memberData.some((cartItem) => cartItem.pack === "상온") && (
                  <div>
                    <hr className="border-black" />
                    <div className="flex items-center justify-between">
                      <div className="flex items-center">
                        <Image src={SunImage} width={50} height={50} className="SunImg" alt="sun Img" />
                        <span className="ml-[20px]">상온상품</span>
                      </div>
                      <div onClick={toggleRoomTempPV} className="mr-[23px]">
                        {isRoomTemperatureVisible ? (
                          <IoIosArrowUp className="w-[30px] h-[30px]" />
                        ) : (
                          <IoIosArrowDown className="w-[30px] h-[30px]" />
                        )}
                      </div>
                    </div>
                    {memberData &&
                      memberData.map((cartItem: any, index: number) => {
                        if (cartItem.pack === "상온") {
                          return (
                            <CartItem
                              key={index}
                              product={cartItem.product}
                              count={cartItem.count}
                              member_id={memberId}
                              isCheck={cartItem.isCheck}
                              setIsCheck={(isChecked) => toggleCheck(index, isChecked)}
                              isProductVisible={isRoomTemperatureVisible}
                              basket_id={cartItem.basket_id}
                              onCountChange={(newCount: number) => setCountInParent(index, newCount)}
                              onDeleteItem={() => deleteItem(cartItem.basket_id)}
                            />
                          );
                        }
                        return null;
                      })}
                  </div>
                )}
              </div>

              <div className="orderBox w-[272px] h-[463px] sticky left-[1100px] top-[100px]">
                <div className="RightBox w-[272px] border border-opacity-30">
                  <div className="OrderTop w-[272px] p-[20px]">
                    <div className="Address text-[20px] flex items-center">
                      <GrLocation />
                      <span className="ml-[5px]">배송지</span>
                    </div>
                    {userAddress ? (
                      <span className="text-[20px]">
                        {userAddress.address_1} <br /> {userAddress.address_2}
                      </span>
                    ) : (
                      <span className="text-[20px]">배송지가 없습니다. 배송지를 추가해주세요.</span>
                    )}
                    <Link
                      href={{ pathname: `/mypage/home` }}
                      className="SubmitBtn w-[225px] h-[42px] mt-[11px] mb-[11px] flex items-center justify-center text-[#33C130] text-[12px] border border-[#33C130]"
                    >
                      배송지 변경
                    </Link>
                  </div>
                  <hr />
                  <div className="OrderBottom w-[272px] p-[20px] text-[16px]">
                    <div className="flex justify-between mb-[10px]">
                      <span>상품금액</span>
                      <span>{formattedTotalPrice}원</span>
                    </div>
                    <div className="flex justify-between mb-[10px]">
                      <span>상품할인금액</span>
                      <span className="text-[red]">-{formattedDiscount}원</span>
                    </div>
                    <hr />
                    <div className="flex justify-between mb-[20px] mt-[10px]">
                      <span>상품총액</span>
                      <span>{formattedPriceWithoutDelFee}원</span>
                    </div>
                    <div className="flex justify-between">
                      <span>배송비</span>
                      <span>{formattedFee}원</span>
                    </div>
                  </div>
                  <hr />
                  <div className="OrderPrice w-[272px] p-[20px] text-[16px]">
                    <div className="flex justify-between">
                      <span>결제예정금액</span>
                      <b>
                        <span>{ResultPrice}원</span>
                      </b>
                    </div>
                  </div>
                  <TossButton props={[memberData, result]} data={completePay} />
                </div>
              </div>
            </div>
          </div>
        </>
      ) : (
        <>
          <div className="Container min-w-[700px] mt-[76px] flex flex-col items-center">
            <span className="text-[36px]">결제내역</span>
            <div className="w-[700px] border-[1px] mt-[50px]">
              <div className="MainBox w-[700px] my-[20px] ml-[20px]">
                <p>결제방법 : {receipt?.easyPay.provider}</p>
                <p>총 결제금액 : {formattedreceiptPrice(receipt?.easyPay.amount)}원</p>
              </div>
              <div className="w-[700px] ml-[20px]">
                {payList.map((item: any, idx: number) => (
                  <div key={idx} className="flex items-center my-[20px]">
                    <Image src={item.product.img} alt="" width={100} height={100}></Image>
                    <div className="ml-[20px]">
                      <p>{item.product.name}</p>
                      <p>수량 : {item.count}개</p>
                      <p>가격 : {formattedreceiptPrice(item.product.sale * item.count)}원</p>
                    </div>
                  </div>
                ))}
              </div>
            </div>
          </div>
        </>
      )}
    </>
  );
}
