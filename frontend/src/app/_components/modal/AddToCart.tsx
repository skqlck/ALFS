"use client";

import React from "react";
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
type ItemProps = {
  id: string;
  cnt: number;
  member_id: string;
  img: string;
  name: string;
};

export default function AddToCart({ id, cnt, member_id, img, name }: ItemProps) {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const AddCart = async (id: string, cnt: number, member_id: string) => {
    const response: any = await AddProductToCart(id, cnt, member_id);
    console.log("장바구니 추가요청", response);
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

      <Modal isOpen={isOpen} onClose={onClose} size="xl">
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
            <Button colorScheme="whatsapp" variant="outline" mr={3}>
              <Link
                href={{ pathname: `/cart` }}
                colorScheme="whatsapp"
                variant="outline"
                mr={3}
                onClick={() => AddCart(id, cnt, member_id)}
              >
                Add
              </Link>
            </Button>
            <Button colorScheme="whatsapp" variant="outline" mr={3} onClick={onClose}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}
