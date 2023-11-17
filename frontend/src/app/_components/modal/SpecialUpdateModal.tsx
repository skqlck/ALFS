"use client";

import React, { useEffect, useState } from "react";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Button,
  FormControl,
  FormLabel,
  Input,
  Textarea,
  Select,
} from "@chakra-ui/react";
import { GetProductDetail } from "@/app/api/detail/DetailPage";
import { useForm } from "react-hook-form";
import { SpecialUpdate, UpdateProduct } from "@/app/api/supervisor/supervisor";
import PropsModal from "./PropsModal";

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

function SpecialUpdateModal(props: any) {
  const [show, setShow] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  const handleShow = () => {
    props.data(false);
    setShow(false);
  };
  const GetData = async (data: any) => {
    setValue("start", data.start);
    setValue("end", data.end);
    setValue("count", data.count);
    setValue("salePrice", data.salePrice);
    setValue("status", data.status);
    setValue("productId", data.productId);
  };

  const registerProduct = async (e: any) => {
    const data = {
      status: e.status,
      start: e.start,
      end: e.end,
      count: e.count,
      salePrice: e.salePrice,
      supervisorId: Number(localStorage.getItem("supervisorId")),
    };
    const res = await SpecialUpdate(data, e.productId);
    if (res?.data.code === 200) {
      setShowModal(true);
    }
  };

  useEffect(() => {
    GetData(props.props);
  }, []);

  return (
    <div>
      <Modal size="4xl" isOpen={show} onClose={handleShow} preserveScrollBarGap={true}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>상품 정보수정하기</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
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
                    특가상품 수정
                  </Button>
                </div>
              </form>
            </div>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" onClick={handleShow}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
      {showModal && <PropsModal props="특가상품이 수정되었습니다." />}
    </div>
  );
}

export default SpecialUpdateModal;
