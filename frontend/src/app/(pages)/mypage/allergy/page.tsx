"use client";

import ChoiceAllergy from "@/app/_components/choiceAllergy/ChoiceAllergy";
import { Button } from "@chakra-ui/react";
import React, { useState } from "react";

type Props = {};

export default function Page({}: Props) {
  const [allergy, setAllergy] = useState([]);
  const changeAllergy = () => {
    console.log("수정이 완료되었습니다.");
  };
  return (
    <div>
      <div className=" border-black border-b-[4px]">
        <p className="text-[30px] mb-[50px]">나의 알러지</p>
      </div>
      <div className="mt-[20px] border-b-[1px]">
        <div className="flex justify-between items-center">
          <p className="text-[20px]">선택된 알러지</p>
          <ChoiceAllergy data={setAllergy} />
        </div>
        <div></div>
      </div>
      <div className="mt-[20px] border-b-[1px]">
        <p className="text-[20px]  ">추가한 알러지</p>
      </div>
      <div className="mt-[20px] border-b-[1px]">
        <p className="text-[20px]">기피하는 식품</p>
        <div>asdg</div>
      </div>
      <div className="flex justify-evenly mt-[20px]">
        <Button width={300} variant="outline" colorScheme="whatsapp" onClick={changeAllergy}>
          수정하기
        </Button>
      </div>
    </div>
  );
}
