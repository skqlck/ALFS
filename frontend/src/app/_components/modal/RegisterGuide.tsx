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
  useDisclosure,
} from "@chakra-ui/react";

function RegisterGuide() {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <div className="flex justify-end">
      <Button variant="outline" colorScheme="whatsapp" onClick={onOpen}>
        상품등록 가이드
      </Button>

      <Modal isOpen={isOpen} onClose={onClose} preserveScrollBarGap={true}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>상품등록 가이드</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <p>
              1.<br></br>메인,상세,정보사진을 등록합니다.
            </p>
            <br></br>
            <p>
              2.<br></br> 원재료사진을 올려 원재료부분의 사진을 자릅니다.
              <br />
              <b>(사진을 자르면 원재료추출버튼이 활성화됩니다.)</b>
            </p>
            <br></br>
            <p>
              3.<br></br>원재료추출버튼을 눌러 원재료의 내용을 추출합니다.
            </p>
            <br />
            <p>
              4.<br></br>이미지업로드 버튼을 눌러 서버에 업로드합니다.
            </p>
            <br />
            <p>
              5.<br></br>다음단계로 넘어가 상품의 정보들을 입력합니다.
            </p>
            <br />
            <p>
              6.<br></br>정보를 입력하고 상품등록 버튼을 눌러 상품을 등록합니다.
            </p>
          </ModalBody>

          <ModalFooter>
            <Button variant="outline" colorScheme="whatsapp" onClick={onClose}>
              닫기
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}

export default RegisterGuide;
