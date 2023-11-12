"use client";

import React, { useEffect, useState } from "react";
import AOS from "aos";
import "aos/dist/aos.css";
import Food from "./_components/animate/food";
import DownArrow from "./_components/animate/downArrow";
import HateEmoji from "./_components/animate/HateEmoji";
import NoEmoji from "./_components/animate/NoEmoji";
import WarningEmoji from "./_components/animate/WarningEmoji";
import HappyEmoji from "./_components/animate/HappyEmoji";
import Girl from "./_components/animate/Girl";
import Select from "./_components/animate/Select";
import InsertAllergy from "../app/_asset/img/InsertAllergy.gif";
import Disgusting from "./_components/animate/Emoji";

import ChoiceAllergy from "./_components/choiceAllergy/ChoiceAllergy";

import Image from "next/image";
import Green from "./_asset/img/green.jpg";
import Orange from "./_asset/img/orange.jpg";
import Red from "./_asset/img/red.jpg";
import Yellow from "./_asset/img/yellow.jpg";

import {
  Step,
  StepDescription,
  StepIcon,
  StepIndicator,
  StepNumber,
  StepSeparator,
  StepStatus,
  StepTitle,
  Stepper,
  useSteps,
  Box,
  Button,
} from "@chakra-ui/react";

const steps = [
  { title: "나만의 알러지 선택", description: "22가지의 알러지 성분중에 선택할 수 있어요." },
  { title: "추가적인 알러지 선택", description: "걱정 NO! 다른 알러지도 추가할 수 있어요." },
  { title: "기피식품 선택", description: "추가적으로 기피하는 식품도 선택할 수 있어요." },
];

const status = [
  { imgUrl: Red, text: "알러지가 포함되면 빨간색!" },
  { imgUrl: Yellow, text: "기피식품이 포함되면 노란색!" },
  { imgUrl: Orange, text: "같은제조시설에 알러지가 포함되면 주황색!" },
  { imgUrl: Green, text: "아무것도 포함되지 않으면 초록색!" },
];

export default function Page() {
  const [mode, setMode] = useState<number>(0);
  const { activeStep, setActiveStep } = useSteps({
    index: 0,
    count: steps.length,
  });

  const setAllergy = (e: any) => {
    console.log(e);
  };

  useEffect(() => {
    AOS.init();
  }, []);
  return (
    <>
      <div style={{ fontFamily: "omyu_pretty" }}>
        <div className="min-w-[1000px] flex justify-center bg-gray-100">
          <div className="w-[1000px] flex">
            <div data-aos="fade-up" className=" w-[500px] h-[300px]  flex justify-center items-center">
              <p className="text-[40px]">
                ALFS만의 새로운 기능!
                <br />
                알러지 기능을 확인해보세요!
              </p>
            </div>
            <div data-aos="fade-up" className=" w-[500px] h-[300px] flex justify-center items-center">
              <Food />
            </div>
          </div>
        </div>

        <div className="min-w-[1000px] flex justify-center bg-gray-100">
          <div className="w-[1000px] flex justify-center bg-gray-100 ">
            <DownArrow />
          </div>
        </div>

        <div className="min-w-[1000px] flex justify-center">
          <div className="w-[1000px] flex my-[50px]">
            <div className="w-[600px] ">
              <div className="our_solution_category" data-aos="zoom-in">
                <div className="solution_cards_box">
                  <div
                    className="solution_card cursor-pointer"
                    onClick={() => {
                      setMode(0);
                    }}
                  >
                    <div className="hover_color_bubble"></div>
                    <NoEmoji />
                  </div>
                  <div
                    className="solution_card"
                    onClick={() => {
                      setMode(1);
                    }}
                  >
                    <div className="hover_color_bubble cursor-pointer"></div>
                    <HateEmoji />
                  </div>
                </div>
                <div className="solution_cards_box">
                  <div
                    className="solution_card cursor-pointer"
                    onClick={() => {
                      setMode(2);
                    }}
                  >
                    <div className="hover_color_bubble"></div>
                    <WarningEmoji />
                  </div>
                  <div
                    className="solution_card cursor-pointer"
                    onClick={() => {
                      setMode(3);
                    }}
                  >
                    <div className="hover_color_bubble"></div>
                    <HappyEmoji />
                  </div>
                </div>
              </div>
            </div>

            {mode === 0 && (
              <div className="w-[400px] flex flex-col justify-center items-center" data-aos="fade-left">
                <p className="text-[20px] mb-[10px]">{status[0].text}</p>
                <Image src={status[0].imgUrl} width={200} height={200} alt="" />
              </div>
            )}
            {mode === 1 && (
              <div className="w-[400px] flex flex-col justify-center items-center" data-aos="fade-left">
                <p className="text-[20px] mb-[10px]">{status[1].text}</p>
                <Image src={status[1].imgUrl} width={200} height={200} alt="" />
              </div>
            )}
            {mode === 2 && (
              <div className="w-[400px] flex flex-col justify-center items-center" data-aos="fade-left">
                <p className="text-[20px] mb-[10px]">{status[2].text}</p>
                <Image src={status[2].imgUrl} width={200} height={200} alt="" />
              </div>
            )}
            {mode === 3 && (
              <div className="w-[400px] flex flex-col justify-center items-center" data-aos="fade-left">
                <p className="text-[20px] mb-[10px]">{status[3].text}</p>
                <Image src={status[3].imgUrl} width={200} height={200} alt="" />
              </div>
            )}
          </div>
        </div>

        <div className="min-w-[1000px] flex justify-center bg-green-50">
          <div className="w-[1000px] flex justify-center my-[100px]" data-aos="fade-down">
            <p className="text-[50px]">
              <b>ALFS</b> 이용 방법
            </p>
          </div>
        </div>

        <div className="min-w-[1000px] flex justify-center bg-green-50">
          <div className="w-[1000px] flex mb-[100px]" data-aos="fade-down">
            <div className="w-[400px] whitespace-pre-line">
              <Stepper
                size="lg"
                index={activeStep}
                orientation="vertical"
                width="200px"
                height="500px"
                colorScheme="whatsapp"
              >
                {steps.map((step, index) => (
                  <Step key={index} onClick={() => setActiveStep(index)}>
                    <StepIndicator>
                      <StepStatus complete={<StepIcon />} incomplete={<StepNumber />} active={<StepNumber />} />
                    </StepIndicator>

                    <Box flexShrink="0">
                      <StepTitle>{step.title}</StepTitle>
                      <StepDescription>{step.description}</StepDescription>
                    </Box>

                    <StepSeparator />
                  </Step>
                ))}
              </Stepper>
            </div>
            {activeStep === 0 && (
              <div className="w-[600px] flex flex-col justify-center items-center" data-aos="fade-left">
                <Select />
                <ChoiceAllergy data={setAllergy} props={[]} />
              </div>
            )}
            {activeStep === 1 && (
              <div className="w-[600px] flex flex-col justify-center items-center" data-aos="fade-left">
                <Girl />
                <Image src={InsertAllergy} alt=""></Image>
              </div>
            )}
            {activeStep === 2 && (
              <div className="w-[600px] flex flex-col justify-center items-center" data-aos="fade-left">
                <Disgusting />
              </div>
            )}
          </div>
        </div>

        <div className="min-w-[1000px] flex justify-center">
          <div className="w-[700px] flex justify-evenly mt-[50px]" data-aos="fade-down">
            <a href="/list">
              <Button variant="outline" colorScheme="whatsapp" width={200}>
                구매하러 가기
              </Button>
            </a>
            <a href="/mypage/allergy">
              <Button variant="outline" colorScheme="whatsapp" width={200}>
                알러지 등록하러 가기
              </Button>
            </a>
            <a href="/login">
              <Button variant="outline" colorScheme="whatsapp" width={200}>
                로그인 및 회원가입
              </Button>
            </a>
          </div>
        </div>
      </div>
    </>
  );
}
