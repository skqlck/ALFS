"use client";
import React, { useEffect, useState } from "react";
import { Button, Input, InputGroup, InputRightElement } from "@chakra-ui/react";
import { AiOutlineSearch } from "react-icons/ai";
import { AllProduct, DeleteProduct, SpecialAll, SpecialDelete } from "@/app/api/supervisor/supervisor";
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
    const res = await SpecialAll();
    const supervisorId = localStorage.getItem("supervisorId");
    setData(res?.data.data.filter((item: any) => item.supervisorId === Number(supervisorId)));
    setFilteredData(res?.data.data.filter((item: any) => item.supervisorId === Number(supervisorId))); // Set initial data and filtered data to be the same
  };

  const handleSearch = (e: any) => {
    setTimeout(() => {
      const searchTerm = e.target.value;
      const filteredResponse = data.filter((item: any) => item.productName.includes(searchTerm));
      setFilteredData(filteredResponse);
    }, 1500);
  };

  const deleteProduct = async (e: number) => {
    const res = await SpecialDelete(e);
    console.log(res);
    getData();
  };

  useEffect(() => {
    getData();
  }, []);
  return (
    <div>
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
                  <Button
                    variant="outline"
                    colorScheme="whatsapp"
                    onClick={() => {
                      setShow(true);
                      setSendData(item.productId);
                    }}
                  >
                    수정
                  </Button>

                  <Button
                    variant="outline"
                    colorScheme="red"
                    onClick={() => {
                      deleteProduct(item.productId);
                    }}
                  >
                    삭제
                  </Button>
                </div>

                <Card
                  name={item.productName}
                  image={item.productImg}
                  id={item.id}
                  title={item.title}
                  price={item.productPrice}
                  sale={item.salePrice}
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
