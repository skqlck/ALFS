"use client";

import React, { useEffect, useState } from "react";
import { AiOutlinePlus } from "react-icons/ai";
import { Table, Thead, Tbody, Tr, Th, Td, TableContainer, Checkbox, Button, useToast } from "@chakra-ui/react";
import { AiOutlineClose, AiOutlineEdit } from "react-icons/ai";
import { AddressAll, ChangeStatus, DeleteAddress, PlusAddress } from "@/app/api/user/user";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  useDisclosure,
  Input,
} from "@chakra-ui/react";
import { useForm } from "react-hook-form";
import DaumPost from "@/app/_components/location/Daumpost";
import PropsModal from "@/app/_components/modal/PropsModal";
import ChangeAddress from "./_components/UpdateAddress";
import PropsErrorModal from "@/app/_components/modal/PropsErrorModal";

type Inputs = {
  id: string;
  address_1: string;
  address_2: string;
  alias: string;
};

export default function Page() {
  const toast = useToast();
  const [myAddress, setMyAddress] = useState([]);
  const [userId, setUserId] = useState<any>("null");
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { register, handleSubmit, setValue } = useForm<Inputs>();
  const [showChange, setShowChange] = useState(false);
  const [showDelete, setShowDelete] = useState(false);
  const [showError, setShowError] = useState(false);

  const GetAddress = async (id: any) => {
    const res = await AddressAll(id);
    if (res) {
      setMyAddress(res.data.data);
    }
  };

  const changeStatus = async (id: any) => {
    const data = [Number(userId), id];
    const res = await ChangeStatus(data);
    setShowChange(true);
  };

  const deleteMyAddress = async (id: any) => {
    const data = [Number(userId), id];
    const res = await DeleteAddress(data);
    setShowDelete(true);
  };

  const setAddress = (address: string) => {
    setValue("address_1", address);
  };

  const handleAddress = async (e: any) => {
    if (myAddress.length === 5) {
      setShowError(true);
    } else {
      const data = {
        member_id: userId,
        address_1: e.address_1,
        address_2: e.address_2,
        alias: e.alias,
      };
      const res = await PlusAddress(data);
      GetAddress(userId);
    }
  };

  useEffect(() => {
    const prevId = localStorage.getItem("id");
    GetAddress(prevId);
    setUserId(prevId);
  }, []);

  return (
    <div>
      <div className="border-black border-b-[4px] mb-[23px]">
        <p className="text-[30px]">나의 배송지</p>
        <div className="flex items-center justify-end mb-[26px]">
          <Button onClick={onOpen} variant="outline" colorScheme="whatsapp" display="flex">
            <AiOutlinePlus />새 배송지 추가
          </Button>

          <Modal isOpen={isOpen} onClose={onClose} size="xl">
            <ModalOverlay />
            <ModalContent>
              <form onSubmit={handleSubmit(handleAddress)}>
                <ModalHeader>새 배송지 입력(최대 5개 입력가능)</ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                  <div className="flex justify-evenly mb-[20px]">
                    <div className="w-[100px] h-[40px]  flex items-center">
                      주소<p className="text-red-500">*</p>
                    </div>
                    <div className="w-[300px]">
                      <Input borderColor="gray.300" required disabled {...register("address_1")} />
                    </div>
                    <div className="w-[100px]">
                      <DaumPost data={setAddress} />
                    </div>
                  </div>

                  <div className="flex justify-evenly mb-[20px]">
                    <div className="w-[100px] h-[40px]  flex items-center">상세주소</div>
                    <div className="w-[300px]">
                      <Input
                        borderColor="gray.300"
                        focusBorderColor="green.500"
                        placeholder="상세주소를 입력해주세요"
                        {...register("address_2")}
                      ></Input>
                    </div>
                    <div className="w-[100px]"></div>
                  </div>

                  <div className="flex justify-evenly mb-[20px]">
                    <div className="w-[100px] h-[40px]  flex items-center">주소명칭</div>
                    <div className="w-[300px]">
                      <Input
                        borderColor="gray.300"
                        focusBorderColor="green.500"
                        placeholder="주소명칭을 입력해주세요"
                        {...register("alias")}
                      ></Input>
                    </div>
                    <div className="w-[100px]"></div>
                  </div>
                </ModalBody>

                <ModalFooter>
                  <Button variant="outline" colorScheme="whatsapp" type="submit" onClick={onClose}>
                    주소 추가하기
                  </Button>
                </ModalFooter>
              </form>
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
                <Th width={200}>명칭</Th>
                <Th width={400}>주소</Th>
                <Th width={100}>수정</Th>
                <Th width={100}>삭제</Th>
              </Tr>
            </Thead>
            {myAddress.map((data: any) => (
              <>
                <Tbody key={data.id}>
                  <Tr>
                    <Td>
                      {data.status ? (
                        <Checkbox colorScheme="green" defaultChecked size="lg"></Checkbox>
                      ) : (
                        <Checkbox colorScheme="green" size="lg" onChange={() => changeStatus(data.id)}></Checkbox>
                      )}
                    </Td>
                    <Td>{data.alias}</Td>

                    <Td>
                      {data.address_1} <br />
                      {data.address_2}
                    </Td>
                    <Td>
                      <ChangeAddress props={[data, userId]} />
                    </Td>
                    <Td
                      onClick={() => {
                        deleteMyAddress(data.id);
                      }}
                    >
                      <AiOutlineClose className="text-[20px] cursor-pointer"></AiOutlineClose>
                    </Td>
                  </Tr>
                </Tbody>
              </>
            ))}
          </Table>
        </TableContainer>
      </div>
      {showChange && <PropsModal props="기본주소지가 변경되었습니다." />}
      {showDelete && <PropsModal props="주소지가 삭제되었습니다." />}
      {showError && <PropsErrorModal props="주소지는 5개까지 입력가능합니다. 주소지를 삭제해주세요." />}
    </div>
  );
}
