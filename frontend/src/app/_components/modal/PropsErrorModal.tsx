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

function PropsErrorModal(props: any) {
  const [show, setShow] = useState(true);

  const handleShow = () => {
    window.location.reload();
  };

  return (
    <div>
      <Modal isOpen={show} onClose={handleShow}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>알림</ModalHeader>
          <ModalCloseButton />
          <ModalBody>{props.props}</ModalBody>

          <ModalFooter>
            <Button colorScheme="red" variant="outline" onClick={handleShow}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}

export default PropsErrorModal;
