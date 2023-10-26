"use client";

import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Button,
} from "@chakra-ui/react";
import DaumPostcode from "react-daum-postcode";

function DaumPost(props: any) {
  const { isOpen, onOpen, onClose } = useDisclosure();

  const handleComplete = (e: any) => {
    onClose();
    props.data(e.address);
  };

  return (
    <>
      <Button onClick={onOpen} type="button" colorScheme="whatsapp" variant="outline" width={100}>
        주소검색
      </Button>
      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>주소검색</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <DaumPostcode onComplete={handleComplete} />
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" mr={3} onClick={onClose}>
              Close
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </>
  );
}

export default DaumPost;
