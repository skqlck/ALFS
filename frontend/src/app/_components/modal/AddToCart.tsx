"use client";

import React, { useState } from "react";
import { Button, useDisclosure } from "@chakra-ui/react";
import {
  Modal,
  ModalOverlay,
  ModalHeader,
  ModalContent,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
} from "@chakra-ui/react";
import { Link } from "@chakra-ui/next-js";
import { AddProductToCart } from "@/app/api/cart/CartPage";
import Image from "next/image";
import CartAlert from "./CartAlert";
type ItemProps = {
  id: string;
  cnt: number;
  member_id: string;
  img: string;
  name: string;
};

export default function AddToCart({ id, cnt, member_id, img, name }: ItemProps) {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [show, setShow] = useState(false);
  const AddCart = async (id: string, cnt: number) => {
    const response: any = await AddProductToCart(id, cnt);
    onClose();
    setShow(true);
    // setTimeout(() => {
    //   setShow(false);
    // }, 2000);
  };
  return (
    <div>
      <button
        type="button"
        onClick={onOpen}
        className="SubmitBtn w-[472px] h-[62px] mt-[11px] flex items-center justify-center bg-[#33C130] text-white"
      >
        장바구니에 담기
      </button>

      <Modal isOpen={isOpen} onClose={onClose} size="xl" preserveScrollBarGap={true}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>
            장바구니에 담으시겠습니까?
            <ModalCloseButton />
          </ModalHeader>
          <ModalBody>
            <div className="flex justify-between items-center">
              <Image src={img} width={100} height={100} alt="Cart Item" />
              <span>{name}</span>
              <span>수량 : {cnt}개</span>
            </div>
          </ModalBody>
          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" mr={3} onClick={onClose}>
              Close
            </Button>
            <Button colorScheme="whatsapp" variant="outline" onClick={() => AddCart(String(id), cnt)}>
              장바구니에 담기
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
      {show && <CartAlert props={[img, name]} />}
    </div>
  );
}
