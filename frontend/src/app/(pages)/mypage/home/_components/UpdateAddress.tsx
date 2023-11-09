import DaumPost from "@/app/_components/location/Daumpost";
import {
  Button,
  Input,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  Toast,
  useDisclosure,
  useToast,
} from "@chakra-ui/react";
import React, { useEffect } from "react";
import { AiOutlineEdit } from "react-icons/ai";
import { useForm } from "react-hook-form";
import { UpdateAddress } from "@/app/api/user/user";

type Inputs = {
  id: string;
  address_1: string;
  address_2: string;
  alias: string;
};

export default function ChangeAddress(props: any) {
  const data = props.props[0];
  const userId = props.props[1];
  const address_id = props.props[0].id;
  const toast = useToast();
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  const setAddress = (address: string) => {
    setValue("address_1", address);
  };

  const handleUpdate = async (e: any) => {
    const sendData = {
      member_id: Number(userId),
      address_id: address_id,
      address: {
        address_1: e.address_1,
        address_2: e.address_2,
        alias: e.alias,
      },
    };
    await UpdateAddress(sendData);
    toast({
      title: "주소가 변경되었습니다.",
      status: "success",
      duration: 3000,
      isClosable: true,
    });
    onClose();
    setTimeout(() => {
      window.location.reload();
    }, 1000);
  };

  useEffect(() => {
    setValue("address_1", data.address_1);
    setValue("address_2", data.address_2);
    setValue("alias", data.alias);
  }, []);
  return (
    <>
      <Button onClick={onOpen} variant="unstyled">
        <AiOutlineEdit />
      </Button>

      <Modal isOpen={isOpen} onClose={onClose} size="xl">
        <ModalOverlay />
        <ModalContent>
          <form onSubmit={handleSubmit(handleUpdate)}>
            <ModalHeader>배송지 수정</ModalHeader>
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
                    required
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
                    required
                  ></Input>
                </div>
                <div className="w-[100px]"></div>
              </div>
            </ModalBody>

            <ModalFooter>
              <Button variant="outline" colorScheme="whatsapp" type="submit">
                수정완료
              </Button>
            </ModalFooter>
          </form>
        </ModalContent>
      </Modal>
    </>
  );
}
