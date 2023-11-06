"use client";

import React, { useState } from "react";
import { Button, useDisclosure } from "@chakra-ui/react";
import { Modal, ModalOverlay, ModalContent, ModalFooter, ModalBody, ModalCloseButton } from "@chakra-ui/react";
import { Link } from "@chakra-ui/next-js";

export default function AddToCart(props: any) {
  const { isOpen, onOpen, onClose } = useDisclosure();
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
            {props.message}
            <br />
            장바구니 페이지로 이동하시겠습니까?
          </ModalBody>
          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" mr={3}>
              <Link href={{ pathname: `/cart` }} colorScheme="whatsapp" variant="outline" mr={3}>
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
