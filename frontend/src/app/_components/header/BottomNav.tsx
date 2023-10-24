import React from 'react';
import { GiHamburgerMenu } from 'react-icons/gi';

type Props = {};

export default function BottomNav({}: Props) {
  return (
    <div className="min-w-[1000px] h-[50px] flex justify-center items-center border-b-2">
      <div className="w-[1000px] flex">
        <div className="flex w-[200px]">
          <GiHamburgerMenu className="w-[20px] h-[20px] mr-2" />
          <p>카테고리</p>
        </div>
        <div className="flex gap-[50px]">
          <p>신상품</p>
          <p>베스트</p>
          <p>대체상품</p>
        </div>
      </div>
    </div>
  );
}
