"use client";

import ChoiceAllergy from "@/app/_components/choiceAllergy/ChoiceAllergy";
import { Button, useToast } from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { AiOutlinePlus } from "react-icons/ai";

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
  Tag,
  TagLabel,
  TagCloseButton,
} from "@chakra-ui/react";
import { RegisterAllergy, RegisterHate } from "@/app/api/user/user";
import PropsModal from "@/app/_components/modal/PropsModal";

export default function AllergyButton(props: any) {
  const [allergy_1, setAllergy_1] = useState<string[]>([]);
  const [allergy_2, setAllergy_2] = useState("");
  const [selectedAllergy2, setSelectedAllergy2] = useState<string[]>([]);
  const [hate, setHate] = useState("");
  const [selectedHate, setSelectedHate] = useState<string[]>([]);
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [memberId, setMemberId] = useState<any>();
  const [show, setShow] = useState(false);

  //22가지 알러지 선택
  const setAllergy = (allergy: string[]) => {
    setAllergy_1(allergy);
  };

  //추가로 알러지 입력
  const handleAllergy2 = () => {
    if (allergy_2) {
      setSelectedAllergy2([...selectedAllergy2, allergy_2]);
      setAllergy_2("");
    }
  };
  //추가입력한 알러지 지우기
  const removeAllergy2 = (data: any) => {
    setSelectedAllergy2((prev: any) => {
      const isAlreadySelected = prev.filter((item: any) => item !== data);
      return [...isAlreadySelected];
    });
  };

  //추가로 기피식품 입력
  const handleHate = () => {
    if (hate) {
      setSelectedHate([...selectedHate, hate]);
      setHate("");
    }
  };
  //추가입력한 기피식품 지우기
  const removeHate = (data: any) => {
    setSelectedHate((prev: any) => {
      const isAlreadySelected = prev.filter((item: any) => item !== data);
      return [...isAlreadySelected];
    });
  };

  const submitAllergy = async (e: any) => {
    e.preventDefault();
    const allergy_data = { memberId: memberId, allergy: [...allergy_1, ...selectedAllergy2] };
    await RegisterAllergy(allergy_data);
    const hate_data = { memberId: memberId, hate: selectedHate };
    await RegisterHate(hate_data);
    onClose();
    setShow(true);
  };

  useEffect(() => {
    const prevId = localStorage.getItem("id");
    setMemberId(prevId);
    if (prevId) {
      const allergyArray = [];
      for (const item of props.props[0]) {
        if (item && item.allergyName) {
          allergyArray.push(item.allergyName);
        }
      }
      setAllergy_1(allergyArray);

      const hateArray = [];
      for (const item of props.props[1]) {
        if (item && item.allergyName) {
          hateArray.push(item.allergyName);
        }
      }
      setSelectedHate(hateArray);
    }
  }, [props]);

  return (
    <>
      <div className="border-black border-b-[4px] mb-[23px]">
        <p className="text-[30px]">나의 알러지</p>
        <div className="flex items-center justify-end mb-[26px]">
          <Button onClick={onOpen} variant="outline" colorScheme="whatsapp" display="flex">
            <AiOutlinePlus />
            알러지 수정하기
          </Button>

          <Modal isOpen={isOpen} onClose={onClose} size="2xl" preserveScrollBarGap={true}>
            <ModalOverlay />
            <ModalContent>
              <form onSubmit={submitAllergy}>
                <ModalHeader>알러지 설정</ModalHeader>
                <ModalCloseButton />
                <ModalBody>
                  <div className="flex justify-evenly mb-[20px]">
                    <div className="w-[100px] h-[40px]  flex items-center">알러지</div>
                    <div className="w-[300px]">
                      <ChoiceAllergy data={setAllergy} props={allergy_1} />
                      <div className="mt-[10px]">
                        {allergy_1.map((data, idx) => (
                          <Tag
                            key={idx}
                            borderRadius="full"
                            variant="solid"
                            colorScheme="whatsapp"
                            marginRight={2}
                            marginBottom={2}
                          >
                            <TagLabel>{data}</TagLabel>
                          </Tag>
                        ))}
                      </div>
                    </div>
                    <div className="w-[100px]"></div>
                  </div>

                  <div className="flex justify-evenly mb-[20px]">
                    <div className="w-[100px] h-[40px]  flex items-center whitespace-pre-line">추가입력</div>
                    <div className="w-[300px]">
                      <Input
                        borderColor="gray.300"
                        focusBorderColor="green.500"
                        placeholder="위의 항목을 제외한 알러지 입력"
                        value={allergy_2}
                        onChange={(e: any) => {
                          setAllergy_2(e.target.value);
                        }}
                      />
                      <div className="mt-[10px]">
                        {selectedAllergy2.map((data, idx) => (
                          <Tag
                            key={idx}
                            borderRadius="full"
                            variant="solid"
                            colorScheme="whatsapp"
                            marginRight={2}
                            marginBottom={2}
                          >
                            <TagLabel>{data}</TagLabel>
                            <TagCloseButton
                              onClick={() => {
                                removeAllergy2(data);
                              }}
                            />
                          </Tag>
                        ))}
                      </div>
                    </div>
                    <div className="w-[100px] flex justify-evenly">
                      <Button colorScheme="whatsapp" variant="outline" width={100} onClick={handleAllergy2}>
                        추가하기
                      </Button>
                    </div>
                  </div>

                  <div className="flex justify-evenly mb-[20px]">
                    <div className="w-[100px] h-[40px]  flex items-center">기피식품</div>
                    <div className="w-[300px]">
                      <Input
                        borderColor="gray.300"
                        focusBorderColor="green.500"
                        placeholder="원재료만 입력해주세요"
                        value={hate}
                        onChange={(e: any) => {
                          setHate(e.target.value);
                        }}
                      />
                      <div className="mt-[10px]">
                        {selectedHate.map((data, idx) => (
                          <Tag
                            key={idx}
                            borderRadius="full"
                            variant="solid"
                            colorScheme="whatsapp"
                            marginRight={2}
                            marginBottom={2}
                          >
                            <TagLabel>{data}</TagLabel>
                            <TagCloseButton
                              onClick={() => {
                                removeHate(data);
                              }}
                            />
                          </Tag>
                        ))}
                      </div>
                    </div>
                    <div className="w-[100px] flex justify-evenly">
                      <Button colorScheme="whatsapp" variant="outline" width={100} onClick={handleHate}>
                        추가하기
                      </Button>
                    </div>
                  </div>
                </ModalBody>

                <ModalFooter>
                  <Button colorScheme="whatsapp" variant="outline" width={200} type="submit">
                    알러지 설정 완료
                  </Button>
                </ModalFooter>
              </form>
            </ModalContent>
          </Modal>
        </div>
        {show && <PropsModal props="알러지가 변경되었습니다." />}
      </div>
    </>
  );
}
