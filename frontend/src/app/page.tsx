"use client";

import React, { useEffect } from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import Image from "next/image";
import Alps from "../app/_asset/img/알프스.jpg";

type Props = {};

export default function Page({}: Props) {
  useEffect(() => {
    AOS.init();
  }, []);
  return (
    <div className="min-w-[1000px] flex justify-center">
      <div className="w-[1000px] flex">
        <div data-aos="fade-up" className=" w-[500px] h-[300px] border-[1px] flex justify-center items-center">
          <p className="text-[30px]">
            새로운 기능!
            <br />
            알러지 기능을 확인해보세요{" "}
          </p>
          {/* <Image src={Alps} alt="asdf"></Image> */}
        </div>
        <div data-aos="fade-up" className=" w-[500px] h-[300px] border-[1px]">
          asdf
          {/* <Image src={Alps} alt="asdf"></Image> */}
        </div>
      </div>

      {/* <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div>
        <div data-aos="fade-up" className="h-[300px]">
          asdfad
        </div> */}
    </div>
  );
}
