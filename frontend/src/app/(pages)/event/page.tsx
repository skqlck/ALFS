"use client";

import React, { use } from "react";
import { getEvent } from "@/app/api/event/getEvent";
import { voteEvent } from "@/app/api/event/voteEvent";
import { useState, useEffect } from "react";
import { Case } from "@/app/_components/event/Case";
import { RateBar } from "@/app/_components/event/RateBar";
import NoContent from "@/app/_components/common/NoContent";
import Question from "@/app/_components/animate/Quetion";
import Sound from "@/app/_components/sound/Sound";

type Props = {};

type Event = {
  id: number;
  title: string;
  case1: string;
  case2: string;
};
type Rate = {
  case1_rate: number;
  case2_rate: number;
};

export default function Page({}: Props) {
  const [eventInfo, setEventInfo] = useState<Event>();
  const [rateInfo, setRateInfo] = useState<Rate>();
  const [selected, setSelected] = useState(0);

  useEffect(() => {
    async function initialize() {
      const data = await getEvent();
      console.log(data);
      setEventInfo(data);
    }
    initialize();
  }, []);

  async function getRates(choose_case: number) {
    if (!eventInfo) {
      return;
    }
    if (rateInfo) {
      return;
    }
    const data = await voteEvent(eventInfo.id, choose_case);
    setRateInfo(data);
    setSelected(choose_case);
  }

  return (
    <div className="flex flex-col items-center">
      <div className="flex flex-col items-center w-[1000px]">
        <h1 className=" text-5xl font-extrabold m-10">네가 참 궁금해~ 그건 너도 마찬가지</h1>
        {eventInfo ? (
          <>
            <Sound />
            <div className=" flex flex-col items-center p-3">
              <div>
                <h1 className=" text-4xl my-4">{eventInfo.title}</h1>
              </div>
              <div className="flex justify-around w-[700px] items-center">
                <Case
                  name={eventInfo.case1}
                  vote={(choose_case: number) => {
                    getRates(choose_case);
                  }}
                  choose_case={1}
                  selected={selected}
                />
                {/* <span>VS</span> */}
                <Question />
                <Case
                  name={eventInfo.case2}
                  vote={(choose_case: number) => {
                    getRates(choose_case);
                  }}
                  choose_case={2}
                  selected={selected}
                />
              </div>
            </div>
            <RateBar
              rate1={rateInfo ? rateInfo.case1_rate : null}
              rate2={rateInfo ? rateInfo.case2_rate : null}
              selected={selected}
            />
          </>
        ) : (
          <NoContent message="✖ 진행중인 양자택일 이벤트가 없습니다. ✖" />
        )}
      </div>
    </div>
  );
}
