"use client";
import React, { useEffect, useState } from "react";
import { Button, Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import { AiOutlineSearch } from "react-icons/ai";
import { AllProduct, DeleteProduct } from "@/app/api/supervisor/supervisor";
import Loading from "@/app/_components/loading/loading";
import dynamic from "next/dynamic";
import ProductUpdateModal from "@/app/_components/modal/ProductUpdateModal";

const Card = dynamic(() => import("../../../_components/card/SupervisorCard"), {
  loading: () => <Loading />,
  ssr: false,
});

export default function Page() {
  const [data, setData] = useState([]);
  const [filteredData, setFilteredData] = useState([]);
  const [show, setShow] = useState(false);
  const [sendData, setSendData] = useState();

  const getData = async () => {
    const res = await AllProduct();
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
    getData();
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
        <div className="grid grid-cols-3 mx-auto mt-[10px]">
          {filteredData.map((item: any) => (
            <>
              <div key={item.id} className="w-[178px] h-[500px] ml-[44px]">
                <div className="flex justify-evenly mb-[10px]">
                  <Button
                    variant="outline"
                    colorScheme="whatsapp"
                    onClick={() => {
                      setShow(true);
                      setSendData(item.id);
                    }}
                  >
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
                />
              </div>
            </>
          ))}
        </div>
      </div>
      {show && <ProductUpdateModal props={sendData} data={(e: boolean) => setShow(e)} />}
    </div>
  );
}
