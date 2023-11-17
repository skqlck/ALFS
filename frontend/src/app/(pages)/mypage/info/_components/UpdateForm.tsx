"use client";

import React, { useEffect, useState } from "react";
import { Button, FormControl, FormHelperText, Input, useToast } from "@chakra-ui/react";
import { useForm } from "react-hook-form";

import { CheckEmail, CheckId, CheckPhone, UserUpdate } from "@/app/api/user/user";
import PropsModal from "@/app/_components/modal/PropsModal";
import PropsCheckModal from "@/app/_components/modal/PropsCheckModal";

type Inputs = {
  name: string;
  identifier: string;
  password: string;
  passwordCheck: string;
  birth: string;
  email: string;
  phoneNumber: string;
};

export default function UpdateForm(props: any) {
  const idRex = /^[a-zA-Z0-9]{6,16}$/;
  const pwRex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,15}$/;
  const nameRex = /^[가-힣]{2,6}$/;
  const emailRex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
  const phoneRex = /^010\d{3,4}\d{4}$/;
  const toast = useToast({
    position: "bottom",
  });
  const [show, setShow] = useState(false);
  const [showDelete, setShowDelete] = useState(false);

  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  //helpertext보여주기
  const [showtext, setShowText] = useState({
    name: false,
    identifier: false,
    password: false,
    passwordCheck: false,
    birth: false,
    email: false,
    phoneNumber: false,
  });

  const [text, setText] = useState({
    name: "",
    id: "",
    password: "",
    passwordCheck: "",
    birth: "",
    address_1: "",
    address_2: "",
    email: "",
    phoneNumber: "",
  });

  //유효성 검사
  const [validate, setValidate] = useState({
    name: true,
    identifier: true,
    password: false,
    passwordCheck: false,
    email: true,
    phoneNumber: true,
  });

  //회원가입버튼
  const handleUpdate = async (e: any) => {
    const res = await UserUpdate(e);
    setShow(true);
  };

  const handleDelete = async () => {};

  //중복검사버튼
  const handleValidate = async (id: string) => {
    if (id === "identifier" && idRex.test(watch("identifier"))) {
      const res = await CheckId(watch("identifier"));
      if (res === false) {
        setValidate((prev) => ({
          ...prev,
          [id]: true,
        }));
        toast({
          title: "사용가능한 아이디입니다.",
          status: "success",
          duration: 3000,
          isClosable: true,
        });
      } else {
        toast({
          title: "중복된 아이디입니다.",
          status: "error",
          duration: 3000,
          isClosable: true,
        });
      }
    } else if (id === "email" && emailRex.test(watch("email"))) {
      const res = await CheckEmail(watch("email"));
      if (res === false) {
        setValidate((prev) => ({
          ...prev,
          [id]: true,
        }));
        toast({
          title: "사용가능한 이메일입니다.",
          status: "success",
          duration: 3000,
          isClosable: true,
        });
      } else {
        toast({
          title: "중복된 이메일입니다.",
          status: "error",
          duration: 3000,
          isClosable: true,
        });
      }
    } else if (id === "phoneNumber" && phoneRex.test(watch("phoneNumber"))) {
      const res = await CheckPhone(watch("phoneNumber"));
      if (res === false) {
        setValidate((prev) => ({
          ...prev,
          [id]: true,
        }));
        toast({
          title: "사용가능한 번호입니다.",
          status: "success",
          duration: 3000,
          isClosable: true,
        });
      } else {
        toast({
          title: "중복된 번호입니다.",
          status: "error",
          duration: 3000,
          isClosable: true,
        });
      }
    }
  };

  //폼데이터 변경전용
  const handleChange = (id: string) => {
    setValidate((prev) => ({
      ...prev,
      [id]: false,
    }));

    setShowText((prev) => ({
      ...prev,
      [id]: true,
    }));

    if ((id === "password" && pwRex.test(watch("password"))) || (id === "name" && nameRex.test(watch("name")))) {
      setValidate((prev) => ({
        ...prev,
        [id]: true,
      }));
    }
  };
  useEffect(() => {
    if (watch("passwordCheck") === watch("password")) {
      setValidate((prev) => ({
        ...prev,
        passwordCheck: true,
      }));
    } else {
      setValidate((prev) => ({
        ...prev,
        passwordCheck: false,
      }));
    }
  }, [watch("password"), watch("passwordCheck")]);

  useEffect(() => {
    setValue("identifier", props.props.identifier);
    setValue("name", props.props.name);
    setValue("email", props.props.email);
    setValue("phoneNumber", props.props.phoneNumber);
    setValue("birth", props.props.birth);
  }, [props]);

  return (
    <div className="min-w-[650px] flex justify-center">
      <form onSubmit={handleSubmit(handleUpdate)}>
        <FormControl width={650}>
          <div className="mt-[50px]">
            <div className="flex justify-evenly my-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                아이디<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="아이디를 입력해주세요"
                  {...register("identifier", {
                    onChange() {
                      handleChange("id");
                    },
                  })}
                  id="identifier"
                />
                {showtext.identifier &&
                  (validate.identifier ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("identifier")
                        ? idRex.test(watch("identifier"))
                          ? "*중복확인을 완료해주세요."
                          : "*6자이상 16자 이하의 영문 혹은 영문과 숫자를 조합"
                        : "*아이디를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>

              <div className="flex justify-evenly">
                <Button
                  isDisabled={validate.identifier}
                  colorScheme="whatsapp"
                  width={100}
                  variant="outline"
                  onClick={() => {
                    handleValidate("id");
                  }}
                >
                  중복확인
                </Button>
              </div>
            </div>

            <div className=" flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                비밀번호<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  type="password"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="비밀번호를 입력해주세요"
                  {...register("password", {
                    required: true,
                    onChange() {
                      handleChange("password");
                    },
                  })}
                  id="password"
                />
                {showtext.password &&
                  (validate.password ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("password")
                        ? "*영문, 숫자, 특수문자 포함 8자 이상 15자 이하"
                        : "*비밀번호를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>

              <div className="w-[100px]"></div>
            </div>

            <div className=" flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                비밀번호확인<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  type="password"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="비밀번호를 확인해주세요"
                  {...register("passwordCheck", {
                    required: true,
                    onChange() {
                      handleChange("passwordCheck");
                    },
                  })}
                  id="passwordCheck"
                />
                {showtext.passwordCheck &&
                  (validate.passwordCheck ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("passwordCheck") ? "*비밀번호가 다릅니다." : "*비밀번호를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]"></div>
            </div>

            <div className=" flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                이름<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="이름을 입력해주세요"
                  {...register("name", {
                    onChange() {
                      handleChange("name");
                    },
                  })}
                  id="name"
                />
                {showtext.name &&
                  (validate.name ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("name") ? "*한글로 2~6글자" : "*이름을 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]"></div>
            </div>

            <div className=" flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                이메일<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="이메일을 입력해주세요"
                  {...register("email", {
                    onChange() {
                      handleChange("email");
                    },
                  })}
                />
                {showtext.email &&
                  (validate.email ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("email")
                        ? emailRex.test(watch("email"))
                          ? "*중복확인을 완료해주세요."
                          : "*이메일 형식이 아닙니다."
                        : "*이메일을 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="flex justify-evenly">
                <Button
                  isDisabled={validate.email}
                  colorScheme="whatsapp"
                  variant="outline"
                  width={100}
                  onClick={() => {
                    handleValidate("email");
                  }}
                >
                  중복확인
                </Button>
              </div>
            </div>

            <div className=" flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                전화번호<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  type="number"
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="-을 제외한 전화번호를 입력해주세요"
                  {...register("phoneNumber", {
                    onChange() {
                      handleChange("phoneNumber");
                    },
                  })}
                />
                {showtext.phoneNumber &&
                  (validate.phoneNumber ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("phoneNumber")
                        ? phoneRex.test(watch("phoneNumber"))
                          ? "*중복확인을 완료해주세요."
                          : "*전화번호 형식이 아닙니다."
                        : "*전화번호를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]">
                <Button
                  isDisabled={validate.phoneNumber}
                  colorScheme="whatsapp"
                  width={100}
                  variant="outline"
                  onClick={() => {
                    handleValidate("phoneNumber");
                  }}
                >
                  중복확인
                </Button>
              </div>
            </div>

            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px] flex items-center">
                생년월일<p className="text-red-500">*</p>
              </div>
              <div>
                <div className="w-[300px]">
                  <Input
                    type="date"
                    borderColor="gray.300"
                    focusBorderColor="green.500"
                    max="2023-11-17"
                    {...register("birth")}
                  />
                </div>
                {showtext.birth && <FormHelperText className="text-red-500 text-xs">*{text.birth}</FormHelperText>}
              </div>

              <div className="w-[100px]"></div>
            </div>
          </div>

          <div className="flex justify-evenly my-[50px]">
            <Button
              width={200}
              variant="outline"
              colorScheme="whatsapp"
              type="button"
              onClick={() => setShowDelete(true)}
            >
              탈퇴하기
            </Button>
            <Button
              width={200}
              isDisabled={
                !(
                  validate.identifier &&
                  validate.password &&
                  validate.passwordCheck &&
                  validate.name &&
                  validate.email &&
                  validate.phoneNumber &&
                  watch("birth")
                )
              }
              variant="outline"
              colorScheme="whatsapp"
              type="submit"
            >
              수정하기
            </Button>
          </div>
        </FormControl>
      </form>
      {show && <PropsModal props="회원정보가 변경되었습니다." />}
      {showDelete && <PropsCheckModal data={() => setShowDelete(false)} />}
    </div>
  );
}
