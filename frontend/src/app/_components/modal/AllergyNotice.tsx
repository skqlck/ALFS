"use client";

import React, { useState } from "react";
import { Button, useDisclosure } from "@chakra-ui/react";
import { Modal, ModalOverlay, ModalContent, ModalFooter, ModalBody, ModalCloseButton } from "@chakra-ui/react";
import { Link } from "@chakra-ui/next-js";

export default function AllergyNotice(props: any) {
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

      <Modal isOpen={isOpen} onClose={onClose} size="3xl" preserveScrollBarGap={true}>
        <ModalOverlay />
        <ModalContent>
          <ModalCloseButton />
          <ModalBody>알러지 확인 체크</ModalBody>

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
