"use client";

import React, { useEffect, useRef, useState } from "react";
import { loadPaymentWidget } from "@tosspayments/payment-widget-sdk";
import { useAsync } from "react-use";
import { nanoid } from "nanoid";
import axios from "axios";
import { PurchaseItem } from "@/app/api/cart/CartPage";

const clientKey = "test_ck_ORzdMaqN3w964Rzy7B4Pr5AkYXQG";
const customerKey = "test_sk_mBZ1gQ4YVXWOqkW44eM93l2KPoqN";

export default function TossButton(props: any) {
  console.log(props);
  const paymentWidgetRef = useRef<any>(null);
  const paymentMethodsWidgetRef = useRef<any>(null);
  const [price, setPrice] = useState(props.props[1]);

  const handleClick = async () => {
    const paymentWidget = await loadPaymentWidget(clientKey, customerKey);
    const paymentMethodsWidget = paymentWidget.renderPaymentMethods(
      "#payment-widget",
      { value: price },
      { variantKey: "DEFAULT" }
    );

    paymentWidgetRef.current = paymentWidget;
    paymentMethodsWidgetRef.current = paymentMethodsWidget;
    try {
      // ## Q. 결제 요청 후 계속 로딩 중인 화면이 보인다면?
      // 아직 결제 요청 중이에요. 이어서 요청 결과를 확인한 뒤, 결제 승인 API 호출까지 해야 결제가 완료돼요.
      // 코드샌드박스 환경에선 요청 결과 페이지(`successUrl`, `failUrl`)로 이동할 수가 없으니 유의하세요.
      const res = await paymentWidget?.requestPayment({
        orderId: nanoid(),
        orderName: "상품",
        customerName: "김토스",
        // successUrl: `${window.location.origin}/success`,
        // failUrl: `${window.location.origin}/fail`,
      });
      const { paymentKey, orderId, amount } = res;
      const res1 = await axios.post(
        "https://api.tosspayments.com/v1/payments/confirm",
        {
          paymentKey,
          orderId,
          amount,
        },
        {
          headers: {
            Authorization: `Basic ${Buffer.from(`${process.env.TOSS_PAYMENTS_SECRET_KEY}:`).toString("base64")}`,
          },
        }
      );
      if (res1) {
        const basket_ids = props.props[0]
          .filter((item: any) => item.isCheck === true)
          .map((item: any) => item.basket_id);
        const res2 = await PurchaseItem(basket_ids);
        console.log(res2);
        props.data([res1, res2, price]);
      }
    } catch (error) {
      // handle error
    }
  };
  useEffect(() => {
    setPrice(props.props[1]);
  }, [props]);

  return (
    <>
      <button
        className="Submit w-[272px] h-[62px] mt-[11px] flex items-center justify-center bg-[#33C130] text-white"
        onClick={handleClick}
      >
        결제하기
      </button>
    </>
  );
}
