import Image from "next/image";
import React from "react";
import FooterImage from "./_image/alfs-removebg-preview.png";
import Link from "next/link";

type Props = {};

export default function Footer({}: Props) {
  return (
    <div className="min-w-[1000px] mt-[50px] bg-gray-100  h-[300px] flex flex-col  items-center">
      <Image src={FooterImage} alt="" width={200} height={200}></Image>
      <div className="border-b-[2px] drop-shadow-lg">
        <div className="w-[1000px] flex justify-evenly mb-[20px]">
          <Link href="/">
            <b>Main</b>
          </Link>
          <Link href="/">
            <b>Main</b>
          </Link>
          <Link href="/">
            <b>Main</b>
          </Link>
          <Link href="/">
            <b>Main</b>
          </Link>
          <Link href="/">
            <b>Main</b>
          </Link>
        </div>
      </div>
      <p className="text-[10px] mt-[20px] text-gray-500">
        <b>© 2023 ALFS. All Rights Reserved.</b>
      </p>
    </div>
  );
}
