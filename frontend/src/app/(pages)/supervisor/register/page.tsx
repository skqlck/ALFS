"use client";

import { baseAxios } from "@/app/api/Api";
import { Button, Input } from "@chakra-ui/react";
import axios from "axios";
import Image from "next/image";
import React, { useState } from "react";
import { GiConsoleController } from "react-icons/gi";
import Logo from "../../../_asset/img/Logo.jpg";

export default function Page() {
  const [file, setFile] = useState<File[]>([]);
  const [image, setImage] = useState<string>("");
  const [image1, setImage1] = useState<string>("");
  const [image2, setImage2] = useState<string>("");
  const [form, setForm] = useState([]);
  const [form1, setForm1] = useState([]);
  const [form2, setForm2] = useState([]);

  const uploadImage = async () => {
    const formData = new FormData();
    formData.append(`images`, form[0]);
    formData.append(`images`, form1[0]);
    formData.append(`images`, form2[0]);
    const ocrRequest = { format: "jpg", name: "20구" };
    formData.append("OcrFileRequest", new Blob([JSON.stringify(ocrRequest)], { type: "application/json" }));

    try {
      const res = await baseAxios.post<any>("api/supervisor/ocr/file", formData, {
        headers: {
          "Content-Type": "multipart/form-data",
        },
      });
      console.log(res);
    } catch (error) {
      console.error("Error:", error);
    }
  };

  const handlechangefile = (e: any) => {
    console.log(e.target.files);
    const [newfile] = e.target.files;
    setForm(e.target.files);

    const fileReader = new FileReader();
    fileReader.readAsDataURL(newfile);
    fileReader.onload = (e) => {
      const result = e?.target?.result as string;
      setImage(result);
    };
  };
  const handlechangefile1 = (e: any) => {
    console.log(e.target.files);
    const [newfile] = e.target.files;
    setForm1(e.target.files);

    const fileReader = new FileReader();
    fileReader.readAsDataURL(newfile);
    fileReader.onload = (e) => {
      const result = e?.target?.result as string;
      setImage1(result);
    };
  };
  const handlechangefile2 = (e: any) => {
    console.log(e.target.files);
    const [newfile] = e.target.files;
    setForm2(e.target.files);

    const fileReader = new FileReader();
    fileReader.readAsDataURL(newfile);
    fileReader.onload = (e) => {
      const result = e?.target?.result as string;
      setImage2(result);
    };
  };
  return (
    <div>
      <div className=" border-black border-b-[4px]">
        <p className="text-[30px] mb-[50px]">상품등록</p>
      </div>
      <div className="flex">
        <div className="mt-[20px] border-b-[1px]">
          <div className="flex flex-col ustify-between items-center">
            <p className="text-[20px]">상품 메인 사진</p>
            <Input type="file" onChange={handlechangefile}></Input>
          </div>

          <div>
            <Image width={250} height={250} src={image || Logo} alt=""></Image>
          </div>
        </div>
        <div className="mt-[20px] border-b-[1px]">
          <div className="flex flex-col justify-between items-center">
            <p className="text-[20px]">상품 상세정보 사진</p>
            <Input type="file" onChange={handlechangefile1}></Input>
            <Image width={250} height={250} src={image1 || Logo} alt=""></Image>
          </div>

          <div></div>
        </div>
        <div className="mt-[20px] border-b-[1px]">
          <div className="flex flex-col justify-between items-center">
            <p className="text-[20px]">원재료 사진</p>

            <Input type="file" onChange={handlechangefile2}></Input>
          </div>

          <div>
            <Image width={250} height={250} src={image2 || Logo} alt=""></Image>
          </div>
        </div>
      </div>

      <div className="flex justify-evenly mt-[20px]">
        <Button width={300} variant="outline" colorScheme="whatsapp" onClick={uploadImage}>
          상품 등록하기
        </Button>
      </div>
    </div>
  );
}
