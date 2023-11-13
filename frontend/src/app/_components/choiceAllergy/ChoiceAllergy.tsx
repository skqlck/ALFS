"use client";

import React, { MouseEventHandler, useState } from "react";
import { Button, useDisclosure } from "@chakra-ui/react";
import {
  Modal,
  ModalOverlay,
  ModalContent,
  ModalHeader,
  ModalFooter,
  ModalBody,
  ModalCloseButton,
  Grid,
} from "@chakra-ui/react";

import Image from "next/image";
import { AiOutlineSearch } from "react-icons/ai";

const allergy = [
  { idx: 1, name: "밀", image: require("./image/밀.jpg") },
  { idx: 2, name: "메밀", image: require("./image/메밀.jpg") },
  { idx: 3, name: "대두", image: require("./image/대두.jpg") },
  { idx: 4, name: "호두", image: require("./image/호두.jpg") },
  { idx: 5, name: "땅콩", image: require("./image/땅콩.jpg") },
  { idx: 6, name: "복숭아", image: require("./image/복숭아.jpg") },
  { idx: 7, name: "토마토", image: require("./image/토마토.jpg") },
  { idx: 8, name: "돼지고기", image: require("./image/돼지고기.jpg") },
  { idx: 9, name: "난류", image: require("./image/난류.jpg") },
  { idx: 10, name: "우유", image: require("./image/우유.jpg") },
  { idx: 11, name: "닭고기", image: require("./image/닭고기.jpg") },
  { idx: 12, name: "쇠고기", image: require("./image/쇠고기.jpg") },
  { idx: 13, name: "새우 ", image: require("./image/새우.jpg") },
  { idx: 14, name: "고등어", image: require("./image/고등어.jpg") },
  { idx: 15, name: "홍합", image: require("./image/홍합.jpg") },
  { idx: 16, name: "전복", image: require("./image/전복.jpg") },
  { idx: 17, name: "굴", image: require("./image/굴.jpg") },
  { idx: 18, name: "조개류", image: require("./image/조개류.jpg") },
  { idx: 19, name: "게", image: require("./image/게.jpg") },
  { idx: 20, name: "오징어", image: require("./image/오징어.jpg") },
  { idx: 21, name: "아황산류", image: require("./image/아황산류.jpg") },
  { idx: 22, name: "잣", image: require("./image/잣.jpg") },
];

export default function ChoiceAllergy(props: any) {
  const { isOpen, onOpen, onClose } = useDisclosure();

  const [selectedAllergies, setSelectedAllergies] = useState([]);

  const handleToggleAllergy = (data: any) => {
    setSelectedAllergies((prevSelected: any) => {
      const isAlreadySelected = prevSelected.some((item: any) => item === data);
      if (isAlreadySelected) {
        return prevSelected.filter((item: any) => item !== data);
      } else {
        return [...prevSelected, data];
      }
    });
  };
  const sendAllergy = () => {
    onClose();
    console.log(selectedAllergies);
    props.data(selectedAllergies);
  };
  return (
    <div>
      <button
        type="button"
        onClick={onOpen}
        className="w-[300px] h-[40px] border-green-500 border-[1px] text-green-500 flex justify-center items-center"
      >
        <AiOutlineSearch className="w-[23px] h-[23px]" />
        알러지 선택하기
      </button>

      <Modal isOpen={isOpen} onClose={onClose} size="6xl">
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>나의 알러지 선택하기</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            <Grid templateColumns="repeat(6, 1fr)" gap={6} textAlign="center">
              {allergy.map((data) => (
                <div
                  key={data.idx}
                  className={`border-[5px] ${
                    selectedAllergies.find((item: any) => item === data.name) ? "border-green-500" : ""
                  }`}
                  onClick={() => {
                    handleToggleAllergy(data.name);
                  }}
                >
                  <Image src={data.image} alt="adf" priority className="w-[100%] h-[100px] mb-[10px]" />
                  <p>{data.name}</p>
                </div>
              ))}
            </Grid>
          </ModalBody>

          <ModalFooter>
            <Button colorScheme="whatsapp" variant="outline" mr={5} onClick={sendAllergy}>
              선택완료
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
