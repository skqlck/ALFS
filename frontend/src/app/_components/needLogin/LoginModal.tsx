"use client";

import React, { useState, useEffect } from "react";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Button,
} from "@chakra-ui/react";
import { useRouter } from "next/navigation";

function LoginModal() {
  const [show, setShow] = useState(true);
  const router = useRouter();

  useEffect(() => {
    if (show === false) {
      router.push("/login");
    }
  }, [show]);
  return (
    <div>
      <Modal isOpen={show} onClose={() => setShow(false)} preserveScrollBarGap={true}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>ERROR</ModalHeader>
          <ModalCloseButton />
          <ModalBody>로그인이 필요한 페이지입니다.</ModalBody>

          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" mr={3} onClick={() => setShow(false)}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}

export default LoginModal;
