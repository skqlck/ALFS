"use client";

import React from "react";
import Slider from "react-slick";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";
import Image from "next/image";
import Banner from "../_components/WEB BANNER 1.png";
import { BsFillArrowRightCircleFill, BsFillArrowLeftCircleFill } from "react-icons/bs";

function SampleNextArrow(props: any) {
  const { className, style, onClick } = props;
  return (
    <div
      style={{
        position: "absolute",
        right: "10%",
        top: "50%",
        zIndex: 1,
        cursor: "pointer",
      }}
    >
      <BsFillArrowRightCircleFill color="lightgray" className="w-[50px] h-[50px]" onClick={onClick} />
    </div>
  );
}

function SamplePrevArrow(props: any) {
  const { className, style, onClick } = props;
  return (
    <div
      style={{
        position: "absolute",
        left: "10%",
        top: "50%",
        zIndex: 1,
        cursor: "pointer",
      }}
    >
      <BsFillArrowLeftCircleFill
        color="lightgray"
        className="w-[50px] h-[50px] decoration-gray-400"
        onClick={onClick}
      />
    </div>
  );
}

export default function Carousel() {
  const settings = {
    dots: true,
    slidesToShow: 1,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 3000,
    arrow: true,
    nextArrow: <SampleNextArrow />,
    prevArrow: <SamplePrevArrow />,
  };
  return (
    <div className="flex justify-center">
      <div className="w-[1440px]">
        <Slider {...settings} className="bg-black">
          <div className="">
            <Image src={Banner} alt="asdf"></Image>
          </div>
          <div>
            <h3>2</h3>
          </div>
        </Slider>
      </div>
    </div>
  );
}
