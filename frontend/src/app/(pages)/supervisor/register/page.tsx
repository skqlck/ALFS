"use client";

import { baseAxios } from "@/app/api/Api";
import { Button, Input } from "@chakra-ui/react";
import axios from "axios";
import Image from "next/image";
import React, { useState } from "react";

export default function Page() {
  const [file, setFile] = useState<File[]>([]);
  const [image, setImage] = useState<string>("");
  const [image1, setImage1] = useState<string>("");
  const [image2, setImage2] = useState<string>("");
  const config = {
    headers: {
      "Content-Type": "multipart/form-data",
      Accept: "application/json",
    },
  };

  const uploadImage = async () => {
    const formData = new FormData();
    for (let i = 0; i < file.length; i++) {
      formData.append("images", file[i]); // 첫 번째 파일을 선택
    }
    const ocrRequest = { format: "jpg", name: "20구" };
    formData.append("OcrFileRequest", JSON.stringify(ocrRequest));

    const postSurvey = await axios({
      method: "POST",
      url: "http://k9c204.p.ssafy.io:8080/api/supervisor/ocr/file",
      headers: {
        "Content-Type": "multipart/form-data",
      },
      data: formData,
    });

    // console.log(res.data.img);
    // setImage(res.data.img);
  };

  const handlechangefile = (e: any) => {
    console.log(e.target.files);
    const [newfile] = e.target.files;
    setFile([...file, e.target.files]);

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
    setFile([...file, e.target.files]);

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
    setFile([...file, e.target.files]);

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
      <div className="mt-[20px] border-b-[1px]">
        <div className="flex justify-between items-center">
          <p className="text-[20px]">상품 메인 사진</p>
          <form onSubmit={uploadImage}>
            <Input type="file" onChange={handlechangefile}></Input>
            <Button type="submit"></Button>
          </form>
        </div>

        <div>
          <Image width={500} height={500} src={image} alt=""></Image>
        </div>
      </div>
      <div className="mt-[20px] border-b-[1px]">
        <div className="flex justify-between items-center">
          <p className="text-[20px]">상품 상세정보 사진</p>
          <form onSubmit={uploadImage}>
            <Input type="file" onChange={handlechangefile1}></Input>
            <Button type="submit"></Button>
          </form>
        </div>

        <div>
          <Image width={500} height={500} src={image1} alt=""></Image>
        </div>
      </div>
      <div className="mt-[20px] border-b-[1px]">
        <div className="flex justify-between items-center">
          <p className="text-[20px]">원재료 사진</p>
          <form onSubmit={uploadImage}>
            <Input type="file" onChange={handlechangefile2}></Input>
            <Button type="submit"></Button>
          </form>
        </div>

        <div>
          <Image width={500} height={500} src={image2} alt=""></Image>
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
