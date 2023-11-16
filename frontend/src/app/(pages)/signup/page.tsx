"use client";

import React, { useEffect, useState } from "react";
import { Button, FormControl, FormHelperText, Input } from "@chakra-ui/react";
import { useForm } from "react-hook-form";
import { useToast } from "@chakra-ui/react";

import DaumPost from "@/app/_components/location/Daumpost";

import { CheckEmail, CheckId, CheckPhone, UserSignup } from "@/app/api/user/user";
import { useRouter } from "next/navigation";

type Inputs = {
  name: string;
  id: string;
  password: string;
  passwordCheck: string;
  birth: number;
  address_1: string;
  address_2: string;
  alias: string;
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
  const toast = useToast();
  const router = useRouter();

  const [allergy2, setAllergy2] = useState("");
  const [selectedAllergy2, setSelectedAllergy2] = useState<string[]>([]);
  const [hate, setHate] = useState("");
  const [selectedHate, setSelectedHate] = useState<string[]>([]);
  const { register, handleSubmit, watch, setValue } = useForm<Inputs>();

  useEffect(() => {
    const id = localStorage.getItem("id");
    if (id) {
      router.push("/");
    }
  }, []);

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
    email: false,
    phone_number: false,
    isSignup: false,
  });

  //회원가입버튼
  const handleSignup = async (e: any) => {
    const userId = await UserSignup(e);
    console.log(userId);

    toast({
      title: "회원가입에 성공했습니다. 로그인 페이지로 이동합니다.",
      status: "success",
      duration: 3000,
      isClosable: true,
    });
    setTimeout(() => {
      window.location.replace("/login");
    }, 2000);
  };

  //중복검사버튼
  const handleValidate = async (id: string) => {
    if (id === "id" && idRex.test(watch("id"))) {
      const res = await CheckId(watch("id"));
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
      //api호출
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
    } else if (id === "phone_number" && phoneRex.test(watch("phone_number"))) {
      //api호출
      const res = await CheckPhone(watch("phone_number"));
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

    if (
      (id === "password" && pwRex.test(watch("password"))) ||
      (id === "name" && nameRex.test(watch("name"))) ||
      (id === "address_1" && watch("address_1"))
    ) {
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
                  id="identifier"
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
                  {...register("phone_number", {
                    onChange() {
                      handleChange("phone_number");
                    },
                  })}
                />
                {showtext.phone_number &&
                  (validate.phone_number ? null : (
                    <FormHelperText className="text-red-500 text-xs">
                      {watch("phone_number")
                        ? phoneRex.test(watch("phone_number"))
                          ? "*중복확인을 완료해주세요."
                          : "*전화번호 형식이 아닙니다."
                        : "*전화번호를 입력해주세요."}
                    </FormHelperText>
                  ))}
              </div>
              <div className="w-[100px]">
                <Button
                  isDisabled={validate.phone_number}
                  colorScheme="whatsapp"
                  width={100}
                  variant="outline"
                  onClick={() => {
                    handleValidate("phone_number");
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

            {/* <div className="flex justify-evenly mb-[20px]">
              <div className="w-[100px] h-[40px]  flex items-center">알러지</div>
              <div className="w-[300px]">
                <ChoiceAllergy data={setAllergy} />
              </div>
              <div className="w-[100px]"></div>
            </div> */}

            {/* <div className="flex justify-evenly mb-[20px]">
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
            </div> */}

            {/* <div className="flex justify-evenly mb-[20px]">
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
            </div> */}
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
                  watch("birth")
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
