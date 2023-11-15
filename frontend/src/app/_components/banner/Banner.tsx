"use client";

import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Image from "next/image";
import BannerInfos from "@/../public/banner/BannerList.json";
import Link from "next/link";

export default function Carousel() {
  const settings = {
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 4000,
  };
  return (
    <div className="flex justify-center">
      <div className="w-[100vw]">
        <Slider {...settings}>
          {BannerInfos.map((info) => {
            return (
              <div className="w-[800px] h-[300px] relative" key={info.image_url}>
                <Link href={info.moveTo} replace={true} target={info.target}>
                  <Image src={info.image_url} alt="없어요" fill={true} />
                </Link>
              </div>
            );
          })}
        </Slider>
      </div>
    </div>
  );
}
