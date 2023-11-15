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
  Input,
  useToast,
} from "@chakra-ui/react";
import { UserDelete } from "@/app/api/user/user";

function PropsCheckModal(props: any) {
  const [show, setShow] = useState(true);
  const [password, setPassword] = useState("");
  const [text, setText] = useState("");
  const toast = useToast({ position: "top" });

  const handleDelete = async () => {
    const res = await UserDelete(password);
    if (res?.data.data === null) {
      setText(res.data.message);
    } else {
      toast({
        title: "회원탈퇴 되었습니다.",
        status: "success",
        duration: 3000,
        isClosable: true,
      });
      localStorage.removeItem("id");
      setTimeout(() => {
        window.location.replace("/login");
      }, 1000);
    }
  };

  return (
    <div>
      <Modal
        isOpen={show}
        onClose={() => {
          props.data();
        }}
        preserveScrollBarGap={true}
      >
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>알림</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <div>
              <p>탈퇴를 하기 위해 비밀번호를 다시 입력해주세요.</p>
              <Input
                type="password"
                variant="outline"
                focusBorderColor="green.500"
                placeholder="현재 비밀번호를 입력해주세요."
                value={password}
                onChange={(e: any) => setPassword(e.target.value)}
              ></Input>
              <p className="text-red-500 mt-[10px] text-end">{text}</p>
            </div>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" onClick={handleDelete}>
              회원 탈퇴하기
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </div>
  );
}

export default PropsCheckModal;
