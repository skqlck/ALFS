"use client";

import React, { useState, useEffect } from "react";
type Props = {};

export default function Page({}: Props) {
  useEffect(() => {}, []);
  return (
    <div className="flex flex-col items-center">
      <div className="flex flex-col items-center w-[1000px]">
        <div className="items-center border border-[#33C130] w-[800px] text-[#33C130]">굿</div>
      </div>
    </div>
  );
}
