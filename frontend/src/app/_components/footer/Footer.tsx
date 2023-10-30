import Image from "next/image";
import React from "react";
import FooterImage from "./_image/alfs-removebg-preview.png";

type Props = {};

export default function Footer({}: Props) {
  return (
    <div className="min-w-[1000px] bg-gray-100 mt-[50px] h-[500px] flex justify-center items-center">
      <div className="w-[1000px] h-[400px]  flex">
        <Image src={FooterImage} alt="" width={400} height={400}></Image>
        <div className="w-[600px] ">
          <p className="text-[40px] ">TEAM C204</p>
          <div className="w-[600px] h-[340px] flex flex-col justify-center items-center">
            <div className="w-[600px] flex">
              <div className="w-[300px] h-[100px] border-[1px] border-black">김수진</div>
              <div className="w-[300px] h-[100px] border-[1px] border-black">안종상</div>
            </div>
            <div className="w-[600px] flex">
              <div className="w-[300px] h-[100px] border-[1px] border-black">손효민</div>
              <div className="w-[300px] h-[100px] border-[1px] border-black">홍주영</div>
            </div>
            <div className="w-[600px] flex">
              <div className="w-[300px] h-[100px] border-[1px] border-black">연주원</div>
              <div className="w-[300px] h-[100px] border-[1px] border-black">신대혁</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
