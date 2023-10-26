"use client";

import React, { useEffect, useState } from "react";
import { Button, FormControl, FormHelperText, Input } from "@chakra-ui/react";
import { useForm, SubmitHandler } from "react-hook-form";
import { AiOutlineSearch } from "react-icons/ai";

import Link from "next/link";
import DaumPost from "@/app/_components/location/Daumpost";

type Inputs = {
  name: string;
  id: string;
  password: string;
  passwordCheck: string;
  birth: number;
  address_1: string;
  address_2: string;
  email: string;
  phone_number: string;
};

export default function Page() {
  const idRex = /^[a-zA-Z0-9]{6,16}$/;
  const pwRex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,15}$/;
  const nameRex = /^[가-힣]{2,6}$/;
  const emailRex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
  const phoneRex = /^010\d{3,4}\d{4}$/;

  const [text, setText] = useState({
    name: false,
    id: false,
    password: false,
    passwordCheck: false,
    birth: false,
    address_1: false,
    address_2: false,
    email: false,
    phone_number: false,
  });

  const [validate, setValidate] = useState({
    name: false,
    id: false,
    password: false,
    passwordCheck: false,
    birth: false,
    address_1: false,
    address_2: false,
    email: false,
    phone_number: false,
    isSignup: false,
  });

  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  //회원가입버튼
  const handleSignup = (e: Inputs) => {
    console.log(e);
  };

  //중복검사버튼
  const handleValidate = (id: string) => {
    if (id === "id") {
      //api호출
      setValidate((prev) => ({
        ...prev,
        [id]: true,
      }));
    } else if (id === "email") {
      //api호출
      setValidate((prev) => ({
        ...prev,
        [id]: true,
      }));
    }
  };

  //폼데이터 변경전용
  const handleChange = (id: string) => {
    setValidate((prev) => ({
      ...prev,
      [id]: false,
    }));

    setText((prev) => ({
      ...prev,
      [id]: true,
    }));

    if (
      (id === "password" && pwRex.test(watch("password"))) ||
      (id === "passwordCheck" && watch("passwordCheck") == watch("password")) ||
      (id === "name" && nameRex.test(watch("name"))) ||
      (id === "phone_number" && phoneRex.test(watch("phone_number"))) ||
      (id === "address_2" && watch("address_2"))
    ) {
      setValidate((prev) => ({
        ...prev,
        [id]: true,
      }));
    }
  };

  //주소검색
  const setAddress = (address: string) => {
    setValue("address_1", address);
    setValidate((prev) => ({
      ...prev,
      address_1: true,
    }));
  };
  console.log(validate);

  return (
    <div className="min-w-[650px] flex justify-center">
      <form onSubmit={handleSubmit(handleSignup)}>
        <FormControl width={650}>
          <div>
            <div className="text-center text-[30px] mt-[50px] mb-[26px]">회원가입</div>
            <div className="flex justify-end">
              <p className="text-red-500">*</p>는 필수사항입니다.
            </div>
          </div>

          <div className="border-y-2">
            <div className="flex justify-evenly my-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                아이디<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  focusBorderColor="green.500"
                  borderColor="gray.300"
                  placeholder="아이디를 입력해주세요"
                  {...register("id", {
                    onChange() {
                      handleChange("id");
                    },
                  })}
                />
                {text.id &&
                  (validate.id ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("id")
                        ? idRex.test(watch("id"))
                          ? "*중복확인을 완료해주세요."
                          : "*6자이상 16자 이하의 영문 혹은 영문과 숫자를 조합"
                        : "*아이디를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="flex justify-evenly">
                <Button
                  isDisabled={validate.id}
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
                {text.password &&
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
                />
                {text.passwordCheck &&
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
                />
                {text.name &&
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
                {text.email &&
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
                  placeholder="전화번호를 입력해주세요"
                  {...register("phone_number", {
                    onChange() {
                      handleChange("phone_number");
                    },
                  })}
                />
                {text.phone_number &&
                  (validate.phone_number ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("phone_number") ? "*전화번호 형식이 아닙니다." : "*전화번호를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]"></div>
            </div>

            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                주소<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  borderColor="gray.300"
                  disabled
                  {...register("address_1", {
                    onChange() {
                      handleChange("address_1");
                    },
                  })}
                />
              </div>
              <div className="w-[100px]">
                <DaumPost data={setAddress} />
              </div>
            </div>

            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                상세주소<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  borderColor="gray.300"
                  focusBorderColor="green.500"
                  placeholder="상세주소를 입력해주세요"
                  {...register("address_2", {
                    onChange() {
                      handleChange("address_2");
                    },
                  })}
                ></Input>
                {text.address_2 &&
                  (validate.address_2 ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {!watch("address_2") && "*상세주소를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]"></div>
            </div>

            {/* <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px] flex items-center">
                생년월일<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px] border-gray-300 rounded-[6px] flex border-[1px]">
                <Input
                  type="number"
                  variant="unstyled"
                  placeholder="YYYY"
                  borderColor="gray.300"
                  justifyContent="center"
                  focusBorderColor="green.500"
                  textAlign="center"
                  onChange={changeDate("year")}
                />
                <p className="flex items-center">/</p>
                <Input
                  type="number"
                  variant="unstyled"
                  placeholder="MM"
                  borderColor="gray.300"
                  justifyContent="center"
                  focusBorderColor="green.500"
                  textAlign="center"
                  onChange={changeDate("month")}
                />
                <p className="flex items-center">/</p>
                <Input
                  type="number"
                  variant="unstyled"
                  placeholder="DD"
                  borderColor="gray.300"
                  justifyContent="center"
                  focusBorderColor="green.500"
                  textAlign="center"
                  onChange={changeDate("day")}
                />
                {text.birth &&
                  (validate.birth ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("birth") ? null : "*생년월일을 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]"></div>
            </div> */}

            {/* <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                알러지<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <button className="w-[300px] h-[40px] border-green-500 border-[1px] text-green-500 flex justify-center items-center">
                  <AiOutlineSearch className="w-[23px] h-[23px]" />
                  알러지 선택하기
                </button>
                {text.phone_number && <FormHelperText className="text-red-500 text-xs">*</FormHelperText>}
              </div>
              <div className="w-[100px]"></div>
            </div> */}

            {/* <div className="flex justify-evenly my-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">
                기피식품<p className="text-red-500">*</p>
              </div>
              <div className="w-[300px]">
                <Input
                  type="date"
                  borderColor="gray.300"
                  justifyContent="center"
                  focusBorderColor="green.500"
                  {...register("birth", { required: true })}
                />
                {text.phone_number && (
                  <FormHelperText className="text-red-500 text-xs">*생년월일을 선택해주세요</FormHelperText>
                )}
              </div>
              <div className="w-[100px] flex justify-evenly">
                <button className="w-[100px] h-[40px] border-green-500 border-[1px] text-green-500">추가하기</button>
              </div>
            </div>*/}
          </div>

          <div className="flex justify-evenly my-[50px]">
            <Button
              width={200}
              isDisabled={
                !(
                  validate.id &&
                  validate.password &&
                  validate.passwordCheck &&
                  validate.name &&
                  validate.email &&
                  validate.phone_number &&
                  validate.address_1 &&
                  validate.address_2
                )
              }
              variant="outline"
              colorScheme="whatsapp"
              type="submit"
            >
              가입하기
            </Button>
          </div>
        </FormControl>
      </form>
    </div>
  );
}
