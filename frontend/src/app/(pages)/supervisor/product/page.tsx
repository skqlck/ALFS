"use client";
import React, { useEffect, useState } from "react";
import Card from "@/app/_components/card/Card";
import Link from "next/link";
import { GetList } from "../../../apis/list/ListPage";
import { Button, Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import { AiOutlineSearch } from "react-icons/ai";
import { DeleteProduct } from "@/app/apis/supervisor/supervisor";
import PropsModal from "@/app/_components/modal/PropsModal";
import { useSession } from "next-auth/react";

type Props = {};

export default function Page({}: Props) {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const { data: session } = useSession();

  const getData = async () => {
    // const res = await GetList(session?.user?.name);
    // setData(res);
    // setFilteredData(res); // Set initial data and filtered data to be the same
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
    return <PropsModal props="상품이 삭제되었습니다."></PropsModal>;
  };

  useEffect(() => {
    if (session?.user) {
      getData();
    }
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
      <div className=" w-[750px] h-auto mt-[50px]">
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
                />
              </div>
            </>
          ))}
        </div>
      </div>
    </div>
  );
}
