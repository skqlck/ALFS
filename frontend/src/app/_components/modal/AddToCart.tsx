"use client";

import React, { useState } from "react";
import { Button, useDisclosure } from "@chakra-ui/react";
import { Modal, ModalOverlay, ModalContent, ModalFooter, ModalBody, ModalCloseButton } from "@chakra-ui/react";
import { Link } from "@chakra-ui/next-js";
import { AddProductToCart } from "@/app/api/cart/CartPage";

type ItemProps = {
  id: string;
  cnt: number;
  member_id: string;
};

export default function AddToCart({ id, cnt, member_id }: ItemProps) {
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

      <Modal isOpen={isOpen} onClose={onClose} size="3xl">
        <ModalOverlay />
        <ModalContent>
          <ModalCloseButton />
          <ModalBody>
            <br />
            장바구니 페이지로 이동하시겠습니까?
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
                Move
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
