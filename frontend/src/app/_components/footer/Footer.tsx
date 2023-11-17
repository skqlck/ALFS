import Image from "next/image";
import React from "react";
import FooterImage from "./_image/alfs-removebg-preview.png";
import Link from "next/link";

type Props = {};

export default function Footer({}: Props) {
  return (
    <div className="min-w-[1000px] mt-[50px] bg-gray-100  h-[300px] flex flex-col  items-center">
      <Image src={FooterImage} alt="" width={200} height={200}></Image>
      <div className="drop-shadow-lg">
        <div className="w-[1000px] flex justify-evenly mb-[20px]">
          <b>연주원</b>

          <b>손효민</b>

          <b>김수진</b>

          <b>안종상</b>

          <b>홍주영</b>

          <b>신대혁</b>
        </div>
      </div>
      <p className="text-[10px] mt-[20px] text-gray-500">
        <b>© 2023 ALFS. All Rights Reserved.</b>
        &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
        <b>C204 with SSAFY</b>
      </p>
    </div>
  );
}
