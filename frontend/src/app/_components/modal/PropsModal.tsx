"use client";

import React, { useState } from "react";
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

function PropsModal(props: any) {
  const [show, setShow] = useState(true);

  const handleShow = () => {
    window.location.reload();
  };

  return (
    <div>
      <Modal isOpen={show} onClose={handleShow} preserveScrollBarGap={true}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>알림</ModalHeader>
          <ModalCloseButton />
          <ModalBody>{props.props}</ModalBody>

          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" onClick={handleShow}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}

export default PropsModal;
