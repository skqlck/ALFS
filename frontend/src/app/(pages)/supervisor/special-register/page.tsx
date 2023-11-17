"use client";
import React, { useEffect, useState } from "react";
import { Button, FormControl, FormLabel, Input, InputGroup, InputRightElement, useToast } from "@chakra-ui/react";
import { AiOutlineSearch } from "react-icons/ai";
import { AllProduct, DeleteProduct, SpecialRegister } from "@/app/api/supervisor/supervisor";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";
import { useForm } from "react-hook-form";
import PropsModal from "@/app/_components/modal/PropsModal";

const Card = dynamic(() => import("../../../_components/card/SupervisorCard"), {
  loading: () => <Loading />,
  ssr: false,
});

type Inputs = {
  status: number;
  start: string;
  end: string;
  count: number;
  salePrice: string;
  productId: number;
  supervisorId: number;
  productName: string;
};

export default function Page() {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const toast = useToast({ position: "top" });
  const { register, handleSubmit, setValue, watch } = useForm<Inputs>();
  const [show, setShow] = useState(false);

  const getData = async () => {
    const res = await AllProduct();
    setData(res?.data.data);
  };

  const handleSearch = (e: any) => {
    setTimeout(() => {
      const searchTerm = e.target.value;
      const filteredResponse = data.filter((item: any) => item.name.includes(searchTerm));
      setFilteredData(filteredResponse);
    }, 1500);
  };

  const registerProduct = async (e: any) => {
    setValue("start", e.start + ":00.000");
    setValue("end", e.end + ":00.000");
    const data = {
      status: 0,
      start: watch("start"),
      end: watch("end"),
      count: e.count,
      salePrice: e.salePrice,
      productId: e.productId,
      supervisorId: localStorage.getItem("supervisorId"),
    };
    const res = await SpecialRegister(data);
    if (res?.data.code === 200) {
      setShow(true);
    } else {
      toast({
        title: "이미 특가로 등록된 상품입니다.",
        status: "error",
        duration: 3000,
        isClosable: true,
      });
    }
  };

  useEffect(() => {
    getData();
  }, []);
  return (
    <div>
      <div className="my-[20px] flex justify-end">
        <InputGroup size="lg" width={400}>
          <Input type="text" placeholder="검색어를 입력하세요" focusBorderColor="green.500" onChange={handleSearch} />
          <InputRightElement display="flex" justifyContent="center" alignItems="center">
            <AiOutlineSearch className="w-[40px] h-[40px]" />
          </InputRightElement>
        </InputGroup>
      </div>
      <div className="w-[750px] h-auto mt-[50px]">
        <p>현재선택된 상품 : {watch("productName")}</p>
        <div className="grid grid-cols-3 mx-auto mt-[10px]">
          {filteredData.map((item: any) => (
            <>
              <div key={item.id} className="w-[178px] h-[500px] ml-[44px] ">
                <div className="flex justify-evenly mb-[10px]">
                  <Button
                    variant="outline"
                    colorScheme="whatsapp"
                    onClick={() => {
                      setValue("productId", item.id);
                      setValue("productName", item.name);
                      toast({
                        title: "선택이 완료되었습니다.",
                        status: "success",
                        duration: 3000,
                        isClosable: true,
                      });
                    }}
                  >
                    선택
                  </Button>
                </div>

                <Card
                  name={item.name}
                  image={item.img}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  sale={item.sale}
                />
              </div>
            </>
          ))}
        </div>
        <div>
          <form onSubmit={handleSubmit(registerProduct)}>
            <div className="flex justify-evenly">
              <FormControl width={300}>
                <FormLabel>시작일</FormLabel>
                <Input
                  required
                  type="datetime-local"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="시작일을 입력해주세요."
                  {...register("start")}
                ></Input>
              </FormControl>
              <FormControl width={300}>
                <FormLabel>종료일</FormLabel>
                <Input
                  required
                  type="datetime-local"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="종료일을 입력해주세요."
                  {...register("end")}
                ></Input>
              </FormControl>
            </div>

            <div className="flex justify-evenly my-[20px] border-b-[1px]">
              <FormControl width={300}>
                <FormLabel>판매수량</FormLabel>
                <Input
                  required
                  type="number"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="판매수량일을 입력해주세요."
                  {...register("count")}
                ></Input>
              </FormControl>
              <FormControl width={300} marginBottom="20px">
                <FormLabel>할인가격</FormLabel>
                <Input
                  required
                  type="number"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="할인가격을 입력해주세요."
                  {...register("salePrice")}
                ></Input>
              </FormControl>
            </div>
            <div className="flex justify-center">
              <Button variant="outline" colorScheme="whatsapp" type="submit">
                특가상품 등록
              </Button>
            </div>
          </form>
        </div>
      </div>
      {show && <PropsModal props="특가상품이 등록되었습니다." />}
    </div>
  );
}
