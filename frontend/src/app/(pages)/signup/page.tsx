"use client";

import React, { useEffect, useState } from "react";
import { Button, FormControl, FormHelperText, Input } from "@chakra-ui/react";
import { useForm, SubmitHandler } from "react-hook-form";

import Link from "next/link";
import DaumPost from "@/app/_components/location/Daumpost";
import ChoiceAllergy from "@/app/_components/choiceAllergy/ChoiceAllergy";

import { Tag, TagLabel, TagLeftIcon, TagRightIcon, TagCloseButton } from "@chakra-ui/react";

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
  allergy_1: string[];
  allergy_2: string[];
  hate: string[];
  allergy2: string;
};

export default function Page() {
  const idRex = /^[a-zA-Z0-9]{6,16}$/;
  const pwRex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^+=-])(?=.*[0-9]).{8,15}$/;
  const nameRex = /^[가-힣]{2,6}$/;
  const emailRex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
  const phoneRex = /^010\d{3,4}\d{4}$/;

  const [year, setYear] = useState<number>(2023);
  const [month, setMonth] = useState<number>(1);
  const [day, setDay] = useState<number>(1);
  const [allergy2, setAllergy2] = useState("");
  const [selectedAllergy2, setSelectedAllergy2] = useState<string[]>([]);
  const [hate, setHate] = useState("");
  const [selectedHate, setSelectedHate] = useState<string[]>([]);
  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  //helpertext보여주기
  const [showtext, setShowText] = useState({
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

  //helpertext설정
  const [text, setText] = useState({
    name: "",
    id: "",
    password: "",
    passwordCheck: "",
    birth: "",
    address_1: "",
    address_2: "",
    email: "",
    phone_number: "",
  });

  //유효성 검사
  const [validate, setValidate] = useState({
    name: false,
    id: false,
    password: false,
    passwordCheck: false,
    year: false,
    month: false,
    day: false,
    address_1: false,
    address_2: false,
    email: false,
    phone_number: false,
    isSignup: false,
  });

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

    setShowText((prev) => ({
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

  //22가지 알러지 선택
  const setAllergy = (allergy: string[]) => {
    setValue("allergy_1", allergy);
  };

  //추가로 알러지 입력
  const handleAllergy2 = () => {
    if (allergy2) {
      setSelectedAllergy2([...selectedAllergy2, allergy2]);
      setValue("allergy_2", [...selectedAllergy2, allergy2]);
      setAllergy2("");
    }
  };
  //추가입력한 알러지 지우기
  const removeAllergy2 = (data: any) => {
    setSelectedAllergy2((prev: any) => {
      const isAlreadySelected = prev.filter((item: any) => item !== data);
      setValue("allergy_2", isAlreadySelected);
      return [...isAlreadySelected];
    });
  };

  //추가로 기피식품 입력
  const handleHate = () => {
    if (hate) {
      setSelectedHate([...selectedHate, hate]);
      setValue("hate", [...selectedHate, hate]);
      setHate("");
    }
  };
  //추가입력한 알러지 지우기
  const removeHate = (data: any) => {
    setSelectedHate((prev: any) => {
      const isAlreadySelected = prev.filter((item: any) => item !== data);
      setValue("hate", isAlreadySelected);
      return [...isAlreadySelected];
    });
  };

  const changeYear = (e: any) => {
    setShowText((prev) => ({
      ...prev,
      birth: true,
    }));

    const year = e.target.value;
    if (year.length <= 4) {
      setYear(year);
      if (year < 1923 || year > 2024) {
        setText((prev) => ({
          ...prev,
          birth: "정확한 날짜를 입력해주세요.",
        }));
        setValidate((prev) => ({
          ...prev,
          year: false,
        }));
      } else {
        setValidate((prev) => ({
          ...prev,
          year: true,
        }));
      }
    }
  };
  const changeMonth = (e: any) => {
    const month = e.target.value;
    if (month.length <= 2) {
      setMonth(month);
      if (month < 1 || month > 12 || month.length === 1) {
        setText((prev) => ({
          ...prev,
          birth: "정확한 날짜를 입력해주세요.",
        }));
        setValidate((prev) => ({
          ...prev,
          month: false,
        }));
      } else {
        setValidate((prev) => ({
          ...prev,
          month: true,
        }));
      }
    }
  };
  const changeDay = (e: any) => {
    const day = e.target.value;
    if (day.length <= 2) {
      setDay(day);
      if (day < 1 || day > 31 || day.length === 1) {
        setText((prev) => ({
          ...prev,
          birth: "정확한 날짜를 입력해주세요.",
        }));
        setValidate((prev) => ({
          ...prev,
          day: false,
        }));
      } else {
        setValidate((prev) => ({
          ...prev,
          day: true,
        }));
      }
    }
  };

  useEffect(() => {
    setValue("birth", year + month + day);
  }, [year, month, day, setValue]);
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

          <div className="border-y-2 border-gray-300">
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
                {showtext.id &&
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
                  placeholder="전화번호를 입력해주세요"
                  {...register("phone_number", {
                    onChange() {
                      handleChange("phone_number");
                    },
                  })}
                />
                {showtext.phone_number &&
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
                {showtext.address_2 &&
                  (validate.address_2 ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {!watch("address_2") && "*상세주소를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]"></div>
            </div>

            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px] flex items-center">
                생년월일<p className="text-red-500">*</p>
              </div>
              <div>
                <div className="w-[300px] h-[40px] border-gray-300 rounded-[6px] flex border-[1px]">
                  <Input
                    type="number"
                    variant="unstyled"
                    placeholder="YYYY"
                    borderColor="gray.300"
                    justifyContent="center"
                    focusBorderColor="green.500"
                    textAlign="center"
                    value={year}
                    onChange={changeYear}
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
                    value={month}
                    onChange={changeMonth}
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
                    value={day}
                    onChange={changeDay}
                  />
                </div>
                {showtext.birth && !(validate.year && validate.month && validate.day) && (
                  <FormHelperText className="text-red-500 text-xs">*{text.birth}</FormHelperText>
                )}
              </div>

              <div className="w-[100px]"></div>
            </div>

            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">알러지</div>
              <div className="w-[300px]">
                <ChoiceAllergy data={setAllergy} />
              </div>
              <div className="w-[100px]"></div>
            </div>

            <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">추가입력</div>
              <div className="w-[300px]">
                <Input
                  borderColor="gray.300"
                  focusBorderColor="green.500"
                  placeholder="원재료만 입력해주세요"
                  value={allergy2}
                  onChange={(e: any) => {
                    setAllergy2(e.target.value);
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
                  validate.address_2 &&
                  validate.year &&
                  validate.month &&
                  validate.day
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
