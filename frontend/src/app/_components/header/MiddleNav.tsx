import React from "react";
import Logo from "../../_asset/img/Logo.jpg";
import Image from "next/image";
import { FaRegHeart } from "react-icons/fa";
import { AiOutlineShoppingCart } from "react-icons/ai";
import { AiOutlineSearch } from "react-icons/ai";
import { Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import Link from "next/link";

export default function MiddleNav() {
  return (
    <div className="min-w-[1000px] h-[100px] justify-center flex ">
      <div className="min-w-[1000px] flex items-center justify-between">
        <Link href="/main">
          <div className="flex justify-center items-center">
            <Image src={Logo} width={50} height={50} alt="" priority />
            <p className="text-[30px] ml-[10px]">
              <b>ALFS</b>
            </p>
          </div>
        </Link>

        <div>
          <InputGroup size="lg" width={400}>
            <Input type="text" placeholder="검색어를 입력하세요" focusBorderColor="none" />
            <InputRightElement display="flex" justifyContent="center" alignItems="center">
              <AiOutlineSearch className="w-[40px] h-[40px]" />
            </InputRightElement>
          </InputGroup>
        </div>

        <div className="flex gap-[20px]">
          <FaRegHeart className="min-w-[40px] h-[40px]" />
          <AiOutlineShoppingCart className="min-w-[40px] h-[40px]" />
        </div>
      </div>
    </div>
  );
}
