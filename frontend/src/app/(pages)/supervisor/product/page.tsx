"use client";
import React, { Suspense, useEffect, useState } from "react";
// import Card from "@/app/_components/card/Card";
import { Button, Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import { AiOutlineSearch } from "react-icons/ai";
import { AllProduct, DeleteProduct } from "@/app/api/supervisor/supervisor";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";

const Card = dynamic(() => import("../../../_components/card/Card"), {
  loading: () => <Loading />,
  ssr: false,
});

export default function Page() {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [memberId, setMemberId] = useState<any>("");

  const getData = async () => {
    const res = await AllProduct();
    console.log(res);
    setData(res?.data.data);
    setFilteredData(res?.data.data); // Set initial data and filtered data to be the same
  };

  const handleSearch = (e: any) => {
    setTimeout(() => {
      const searchTerm = e.target.value;
      const filteredResponse = data.filter((item: any) => item.name.includes(searchTerm));
      setFilteredData(filteredResponse);
    }, 1500);
  };

  const deleteProduct = async (e: number) => {
    const res = await DeleteProduct(e);
    console.log(res);
    getData();
  };

  useEffect(() => {
    const id = localStorage.getItem("id");
    setMemberId(id);
    getData();
  }, []);
  return (
    <div>
      <div className=" border-black border-b-[4px]">
        <p className="text-[30px] mb-[50px]">상품관리</p>
      </div>
      <div className="my-[20px]">
        <InputGroup size="lg" width={400}>
          <Input type="text" placeholder="검색어를 입력하세요" focusBorderColor="none" onChange={handleSearch} />
          <InputRightElement display="flex" justifyContent="center" alignItems="center">
            <AiOutlineSearch className="w-[40px] h-[40px]" />
          </InputRightElement>
        </InputGroup>
      </div>
      <div className="w-[750px] h-auto mt-[50px]">
        <div className="grid grid-cols-3 mx-auto mt-[10px]">
          {filteredData.map((item: any) => (
            <>
              <div key={item.id} className="w-[178px] h-[500px] ml-[44px]">
                <div className="flex justify-evenly mb-[10px]">
                  <Button variant="outline" colorScheme="whatsapp">
                    수정
                  </Button>
                  <Button
                    variant="outline"
                    colorScheme="red"
                    onClick={() => {
                      deleteProduct(item.id);
                    }}
                  >
                    삭제
                  </Button>
                </div>

                <Card
                  name={item.name}
                  image={item.img}
                  id={item.id}
                  title={item.title}
                  price={item.price}
                  sale={item.sale}
                  delivery={item.delivery}
                  member_id={memberId}
                />
              </div>
            </>
          ))}
        </div>
      </div>
    </div>
  );
}
