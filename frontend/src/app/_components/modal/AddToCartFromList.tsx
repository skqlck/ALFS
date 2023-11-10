"use client";

import React, { useState, useEffect } from "react";
import { Button, useDisclosure } from "@chakra-ui/react";
import {
  Modal,
  ModalHeader,
  ModalOverlay,
  ModalContent,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
} from "@chakra-ui/react";
import { Link } from "@chakra-ui/next-js";
import { AddProductToCart } from "@/app/api/cart/CartPage";
import { LuShoppingCart } from "react-icons/lu";
import Image from "next/image";

type CardProps = {
  id: number;
  image: string;
  name: string;
  price: number;
  sale: number;
  member_id: string;
};
export default function AddToCartFromList({ id, image, name, price, sale, member_id }: CardProps) {
  const [cnt, setCnt] = useState<number>(1);
  const changeCount = (operator: string) => {
    if (id) {
      setCnt((prevCnt) => {
        if (operator === "+") {
          return prevCnt + 1;
        } else if (operator === "-" && prevCnt > 1) {
          return prevCnt - 1;
        } else {
          return prevCnt;
        }
      });
    }
  };
  const formattedPrice = new Intl.NumberFormat().format(price * cnt);
  const formattedSale = new Intl.NumberFormat().format(sale * cnt);
  const { isOpen, onOpen, onClose } = useDisclosure();
  const AddCart = async (id: string, cnt: number, member_id: string) => {
    const response: any = await AddProductToCart(id, cnt, member_id);
    console.log("장바구니 추가요청", response);
  };
  return (
    <div>
      <button
        className="w-[178px] h-[32px] rounded-none mt-[18px] border-2 text-[15px] flex items-center justify-center"
        onClick={onOpen}
      >
        <LuShoppingCart className="mr-2" />
        담기
      </button>

      <Modal isOpen={isOpen} onClose={onClose} size="xl">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader mb={3}>
            <ModalCloseButton />
            장바구니에 담기
          </ModalHeader>
          <ModalBody mt={3}>
            <div className="flex justify-start items-center">
              <Image src={image} width={100} height={100} alt="item image" />
              <span className="ml-[20px]">{name}</span>
            </div>
            <hr className="mt-[10px]" />
            <div className="flex justify-between mt-[20px]">
              <div className="flex flex-col">
                <div>{name}</div>
                <div>
                  <span>{formattedSale}원</span>
                  {price !== sale && <span className="line-through opacity-30 ml-[10px]">{formattedPrice}원</span>}
                </div>
              </div>
              <div className="ButtonBox w-[81px] h-[27px] flex">
                <button
                  onClick={() => changeCount("-")}
                  className="w-[27px] h-[27px] items-center justify-center border-t border-l border-b border-opacity-50"
                >
                  -
                </button>
                <div className="Count w-[27px] h-[27px] border-t border-b border-opacity-50 flex items-center justify-center">
                  {cnt}
                </div>
                <button
                  onClick={() => changeCount("+")}
                  className="w-[27px] h-[27px] items-center justify-center border-t border-b border-r border-opacity-50"
                >
                  +
                </button>
              </div>
            </div>
          </ModalBody>
          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" mr={3} onClick={onClose}>
              취소
            </Button>
            <Link
              href={{ pathname: `/cart` }}
              colorScheme="whatsapp"
              variant="outline"
              mr={3}
              onClick={() => AddCart(String(id), cnt, member_id)}
            >
              <Button colorScheme="whatsapp" variant="outline" mr={3}>
                장바구니에 담기
              </Button>
            </Link>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}
