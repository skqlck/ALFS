"use client";

import React, { useEffect, useState } from "react";
import { AiOutlinePlus } from "react-icons/ai";
import { Table, Thead, Tbody, Tr, Th, Td, TableContainer, Checkbox, Button } from "@chakra-ui/react";
import { BiEdit } from "react-icons/bi";
import { AddressAll } from "@/app/api/user/user";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
} from "@chakra-ui/react";

type address = {
  id: number;
  address_1: string;
  address_2: string;
  alias: string;
};

export default function Page() {
  const [address, setAddress] = useState([]);
  const [id, setId] = useState();
  const { isOpen, onOpen, onClose } = useDisclosure();

  const GetAddress = async (id: any) => {
    const res = await AddressAll(id);
    if (res) {
      setAddress(res.data.data);
      console.log(res);
    }
  };
  useEffect(() => {
    if (sessionStorage.getItem("id")) {
      GetAddress(sessionStorage.getItem("id"));
    }
  }, [typeof window !== "undefined" && sessionStorage.getItem("id")]);

  return (
    <div>
      <div className="border-black border-b-[4px] mb-[23px]">
        <p className="text-[30px]">나의 배송지</p>
        <div className="flex items-center justify-end mb-[26px]">
          <Button onClick={onOpen} variant="outline" colorScheme="whatsapp" display="flex">
            <AiOutlinePlus />새 배송지 추가
          </Button>

          <Modal isOpen={isOpen} onClose={onClose}>
            <ModalOverlay />
            <ModalContent>
              <ModalHeader>Modal Title</ModalHeader>
              <ModalCloseButton />
              <ModalBody></ModalBody>

              <ModalFooter>
                <Button colorScheme="blue" mr={3} onClick={onClose}>
                  Close
                </Button>
                <Button variant="ghost">Secondary Action</Button>
              </ModalFooter>
            </ModalContent>
          </Modal>
        </div>
      </div>
      <div>
        <TableContainer whiteSpace={"pre-line"}>
          <Table variant="simple">
            <Thead>
              <Tr>
                <Th width={100}>선택</Th>
                <Th width={150}>명칭</Th>
                <Th width={400}>주소</Th>
                <Th width={100}>수정</Th>
              </Tr>
            </Thead>
            {address.map((data: any) => (
              <>
                <Tbody>
                  <Tr key={data.id}>
                    <Td>
                      <Checkbox colorScheme="green" defaultChecked size="lg"></Checkbox>
                    </Td>
                    <Td>{data.alias}</Td>
                    <Td>
                      {data.address_1} <br />
                      {data.address_2}
                    </Td>
                    <Td>
                      <BiEdit className="text-[20px]"></BiEdit>
                    </Td>
                  </Tr>
                </Tbody>
              </>
            ))}
          </Table>
        </TableContainer>
      </div>
    </div>
  );
}
