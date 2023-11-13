"use client";

import { baseAxios } from "@/app/api/Api";
import { Button, FormControl, FormLabel, Input, Textarea } from "@chakra-ui/react";
import Image from "next/image";
import React, { useState } from "react";
import Logo from "../../../_asset/img/Logo.jpg";
import { useForm } from "react-hook-form";

type Inputs = {
  name: string;
  title: string;
  price: number;
  sale: number;
  img_1: string;
  img_2: string;
  img_3: string;
  delivery: string;
  seller: string;
  pack: string;
  count: string;
  weight: string;
  allergy: string;
  expireDate: string;
  information: string;
  buyType: string;
  stock: number;
  content: string;
  category: number;
};

export default function Page() {
  const [image, setImage] = useState<string>("");
  const [image1, setImage1] = useState<string>("");
  const [image2, setImage2] = useState<string>("");
  const [image3, setImage3] = useState<string>("");
  const [form, setForm] = useState([]);
  const [form1, setForm1] = useState([]);
  const [form2, setForm2] = useState([]);
  const [form3, setForm3] = useState([]);
  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  const uploadImage = async () => {
    const formData = new FormData();
    formData.append(`images`, form[0]);
    formData.append(`images`, form1[0]);
    formData.append(`images`, form2[0]);
    formData.append(`images`, form3[0]);
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
  const handlechangefile3 = (e: any) => {
    console.log(e.target.files);
    const [newfile] = e.target.files;
    setForm3(e.target.files);

    const fileReader = new FileReader();
    fileReader.readAsDataURL(newfile);
    fileReader.onload = (e) => {
      const result = e?.target?.result as string;
      setImage3(result);
    };
  };
  return (
    <div>
      <div className=" border-black border-b-[4px]">
        <p className="text-[30px] mb-[50px]">상품등록</p>
      </div>
      <div className="mb-[50px]">
        <div className="mt-[20px] flex">
          <div>
            <div className="flex flex-col ustify-between items-center">
              <p className="flex text-[20px]">
                상품 메인 사진<Input width={300} type="file" onChange={handlechangefile}></Input>
              </p>

              <Image width={250} height={250} src={image || Logo} alt=""></Image>
            </div>
          </div>
          <div>
            <div className="flex flex-col justify-between items-center">
              <p className="text-[20px]">상품 상세사진</p>
              <Input type="file" onChange={handlechangefile1}></Input>
              <Image width={250} height={250} src={image1 || Logo} alt=""></Image>
            </div>
          </div>
        </div>

        <div className="mt-[20px] flex">
          <div>
            <div className="flex flex-col ustify-between items-center">
              <p className="text-[20px]">상품 정보사진</p>

              <Input type="file" onChange={handlechangefile2}></Input>
              <Image width={250} height={250} src={image2 || Logo} alt=""></Image>
            </div>
          </div>
          <div>
            <div className="flex flex-col justify-between items-center">
              <p className="text-[20px]">상품 원재료 사진</p>

              <Input type="file" onChange={handlechangefile3}></Input>
              <Image width={250} height={250} src={image1 || Logo} alt=""></Image>
            </div>
          </div>
        </div>
      </div>

      <form action="" className="grid grid-cols-2 gap-5  justify-items-center">
        <FormControl width={300}>
          <FormLabel>제품명</FormLabel>
          <Input
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="제품명을 입력해주세요."
            {...register("name")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>부제목</FormLabel>
          <Input
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="부제목을 입력해주세요."
            {...register("title")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>상품가격</FormLabel>
          <Input
            type="number"
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="상품가격을 입력해주세요."
            {...register("price")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>할인율</FormLabel>
          <Input
            type="number"
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="할인율을 입력해주세요."
            {...register("sale")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>배송정보</FormLabel>
          <Input
            type="number"
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="배송정보를 입력해주세요."
            {...register("delivery")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>판매자명</FormLabel>
          <Input
            type="number"
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="판매자명을 입력해주세요."
            {...register("seller")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>패키징정보</FormLabel>
          <Input
            type="number"
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="패키징정보를 입력해주세요."
            {...register("pack")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>수량</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="수량을 입력해주세요."
            {...register("count")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>제품무게</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="제품무게를 입력해주세요."
            {...register("weight")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>알레르기정보</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="알레르기정보를 입력해주세요."
            {...register("allergy")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>유통기한정보</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="유통기한정보를 입력해주세요."
            {...register("expireDate")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>추가정보</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="추가정보를 입력해주세요."
            {...register("information")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>추가정보</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="추가정보를 입력해주세요."
            {...register("buyType")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>구매유형</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="구매유형을 입력해주세요."
            {...register("stock")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>재고수량</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="재고수량을 입력해주세요."
            {...register("name")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>카테고리</FormLabel>
          <Input
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="재고수량을 입력해주세요."
            {...register("category")}
          ></Input>
        </FormControl>

        <FormControl width={300}>
          <FormLabel>제품설명</FormLabel>
          <Textarea
            width={300}
            focusBorderColor="green.500"
            borderColor="gray.300"
            placeholder="제품설명을 입력해주세요."
            {...register("content")}
          ></Textarea>
        </FormControl>
      </form>

      <div className="flex justify-evenly mt-[20px]">
        <Button width={300} variant="outline" colorScheme="whatsapp" onClick={uploadImage}>
          상품 등록하기
        </Button>
      </div>
    </div>
  );
}
