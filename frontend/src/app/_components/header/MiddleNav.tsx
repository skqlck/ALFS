import React from "react";
import Logo from "../../_asset/img/Logo.jpg";
import Image from "next/image";
import { AiOutlineSearch } from "react-icons/ai";
import { Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import Link from "next/link";

import MiddleIcon from "./MiddleIcon";

export default function MiddleNav() {
  return (
    <div className="min-w-[1000px] h-[100px] justify-center flex ">
      <div className="min-w-[1000px] flex items-center justify-between">
        <Link href="/">
          <div className="flex justify-center items-center">
            <Image src={Logo} width={50} height={50} alt="" priority />
            <p className="text-[30px] ml-[10px]">
              <b>ALFS</b>
            </p>
          </div>
        </Link>

        <div>
          <InputGroup size="lg" width={400}>
            <Input type="text" placeholder="검색어를 입력하세요" focusBorderColor="green.500" />
            <InputRightElement display="flex" justifyContent="center" alignItems="center">
              <AiOutlineSearch className="w-[40px] h-[40px]" />
            </InputRightElement>
          </InputGroup>
        </div>

        <MiddleIcon></MiddleIcon>
      </div>
    </div>
  );
}
