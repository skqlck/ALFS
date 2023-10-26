import React from "react";
import Alfs from "./alfs.png";
import Image from "next/image";
import { FaRegHeart } from "react-icons/fa";
import { AiOutlineShoppingCart } from "react-icons/ai";
import { AiOutlineSearch } from "react-icons/ai";

export default function MiddleNav() {
  return (
    <div className="min-w-[1000px] h-[100px] justify-center flex ">
      <div className="min-w-[1000px] flex items-center justify-between">
        <Image src={Alfs} className="w-[120px] h-[50px]" alt="picture of the author" priority />
        <div className="flex min-w-[450px] h-[50px] border-2 rounded-lg border-black items-center">
          <input type="text" placeholder="검색어를 입력하세요" className="w-[400px] h-[40px] m-2 focus:outline-none" />
          <AiOutlineSearch className="w-[40px] h-[40px] mr-2" />
        </div>
        <div className="flex gap-[20px]">
          <FaRegHeart className="min-w-[40px] h-[40px]" />
          <AiOutlineShoppingCart className="min-w-[40px] h-[40px]" />
        </div>
      </div>
    </div>
  );
}
