import React from "react";
import Alfs from "./alfs.png";
import Image from "next/image";
import { FaRegHeart } from "react-icons/fa";
import { AiOutlineShoppingCart } from "react-icons/ai";
import { AiOutlineSearch } from "react-icons/ai";

type Props = {};

export default function Nav({}: Props) {
  return (
    <div className="w-full h-[100px] justify-center flex">
      <div className="w-[1000px]  flex">
        <Image src={Alfs} width={150} height={50} alt="picture of the author" />
        <div>
          <input
            type="text"
            placeholder="검색어를 입력하세요"
            className="w-[470px] h-[50px] border-[1px] rounded-lg border-black"
          />
          <AiOutlineSearch />
        </div>

        <FaRegHeart className="w-[50px] h-[50px]" />
        <AiOutlineShoppingCart className="w-[50px] h-[50px]" />
      </div>
    </div>
  );
}
