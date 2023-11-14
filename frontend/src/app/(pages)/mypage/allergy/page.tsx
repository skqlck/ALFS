"use client";
import React, { useEffect, useState } from "react";
import AllergyButton from "./_components/AllergyButton";
import { MyAllergy, MyHate, NaverImage } from "@/app/api/user/user";
import { Button } from "@chakra-ui/react";

export default function Page() {
  const [allergy, setAllergy] = useState([]);
  const [hate, setHate] = useState([]);

  const response = async (id: any) => {
    const res1 = await MyAllergy(id);
    setAllergy(res1?.data.data);
    const res2 = await MyHate(id);
    setHate(res2?.data.data);
  };

  useEffect(() => {
    const id = localStorage.getItem("id");
    response(id);
  }, []);

  return (
    <div>
      <AllergyButton props={[allergy, hate]} />
      <div className="mt-[20px] border-b-[1px]">
        <div className=" justify-between items-center">
          <p className="text-[20px] mb-[20px]">선택된 알러지</p>
          <div className="flex gap-[20px] mb-[20px]">
            {allergy?.map((item: any, idx) => (
              <Button variant="outline" colorScheme="whatsapp" key={idx}>
                {item.allergyName}
              </Button>
            ))}
          </div>
        </div>
      </div>
      <div className="mt-[20px] border-b-[1px]">
        <div className=" justify-between items-center">
          <p className="text-[20px] mb-[20px]">선택된 기피식품</p>
          <div className="flex gap-[20px] mb-[20px]">
            {hate?.map((item: any, idx) => (
              <Button variant="outline" colorScheme="whatsapp" key={idx}>
                {item.allergyName}
              </Button>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
}
