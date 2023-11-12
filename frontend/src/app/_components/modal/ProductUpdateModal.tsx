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
import { UpdateProduct } from "@/app/api/supervisor/supervisor";
import PropsModal from "./PropsModal";

type Inputs = {
  id: number;
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

const category = [
  { idx: 0, title: "채소" },
  { idx: 1, title: "과일 · 견과 · 쌀" },
  { idx: 2, title: "수산 · 해산 · 건어물" },
  { idx: 3, title: "정육 · 계란" },
  { idx: 4, title: "국 · 반찬 · 메인요리" },
  { idx: 5, title: "샐러드 · 간편식" },
  { idx: 6, title: "면 · 양념 · 오일" },
  { idx: 7, title: "생수 · 음료 · 우유 · 커피" },
  { idx: 8, title: "간식 · 과자 · 떡" },
  { idx: 9, title: "베이커리 · 치즈 · 델리" },
];

function ProductUpdateModal(props: any) {
  console.log(props);
  const [show, setShow] = useState(true);
  const [showModal, setShowModal] = useState(false);
  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  const handleShow = () => {
    props.data(false);
    setShow(false);
  };
  const GetData = async (id: number) => {
    const res = await GetProductDetail(id);
    setValue("allergy", res.allergy);
    setValue("buyType", res.buyType);
    setValue("category", res.category);
    setValue("content", res.content);
    setValue("count", res.count);
    setValue("delivery", res.delivery);
    setValue("expireDate", res.expireDate);
    setValue("information", res.information);
    setValue("name", res.name);
    setValue("title", res.title);
    setValue("pack", res.pack);
    setValue("sale", res.sale);
    setValue("seller", res.seller);
    setValue("stock", res.stock);
    setValue("weight", res.weight);
    setValue("img_1", res.main_img);
    setValue("img_2", res.detail_img);
    setValue("img_3", res.ingre_img);
    setValue("id", res.id);
    setValue("price", res.price);
  };

  const handleProduct = async (e: any) => {
    const res = await UpdateProduct(e);
    setShowModal(true);
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
              <form onSubmit={handleSubmit(handleProduct)}>
                <div className="grid grid-cols-2 gap-5  justify-items-center">
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
                    <FormLabel>할인가격</FormLabel>
                    <Input
                      type="number"
                      width={300}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="할인가격을 입력해주세요."
                      {...register("sale")}
                    ></Input>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>배송정보</FormLabel>
                    <Select
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="배송정보를 선택해주세요."
                      {...register("delivery")}
                    >
                      <option value="일반배송">일반배송</option>
                    </Select>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>판매자명</FormLabel>
                    <Select
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="판매자를 선택해주세요."
                      {...register("seller")}
                    >
                      <option value="알프스">알프스</option>
                    </Select>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>패키징정보</FormLabel>
                    <Select
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="패키징정보를 선택해주세요."
                      {...register("pack")}
                    >
                      <option value="냉동">냉동</option>
                      <option value="냉장">냉장</option>
                    </Select>
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
                    <FormLabel>구매유형</FormLabel>
                    <Input
                      width={300}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="구매유형를 입력해주세요."
                      {...register("buyType")}
                    ></Input>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>재고수량</FormLabel>
                    <Input
                      width={300}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="재고수량을 입력해주세요."
                      {...register("stock")}
                    ></Input>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>카테고리</FormLabel>
                    <Select
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="카테고리를 선택해주세요."
                      {...register("category")}
                    >
                      {category.map((item) => (
                        <option key={item.idx} value={item.idx}>
                          {item.title}
                        </option>
                      ))}
                    </Select>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>알레르기정보</FormLabel>
                    <Textarea
                      width={300}
                      height={200}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="알레르기정보를 입력해주세요."
                      {...register("allergy")}
                    ></Textarea>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>유통기한정보</FormLabel>
                    <Textarea
                      width={300}
                      height={200}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="유통기한정보를 입력해주세요."
                      {...register("expireDate")}
                    ></Textarea>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>추가정보</FormLabel>
                    <Textarea
                      width={300}
                      height={200}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="추가정보를 입력해주세요."
                      {...register("information")}
                    ></Textarea>
                  </FormControl>

                  <FormControl width={300}>
                    <FormLabel>제품설명</FormLabel>
                    <Textarea
                      width={300}
                      height={200}
                      focusBorderColor="green.500"
                      borderColor="gray.300"
                      placeholder="제품설명을 입력해주세요."
                      {...register("content")}
                    ></Textarea>
                  </FormControl>
                </div>
                <div className="flex justify-evenly mt-[50px]">
                  <Button width={300} variant="outline" colorScheme="whatsapp" type="submit">
                    상품 수정하기
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
      {showModal && <PropsModal props="상품이 수정되었습니다." />}
    </div>
  );
}

export default ProductUpdateModal;
