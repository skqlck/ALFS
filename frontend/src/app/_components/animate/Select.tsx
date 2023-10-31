import React from "react";

import Lottie from "react-lottie-player";

import lottieJson from "../../_asset/data/Animation - 1698733442701.json";

export default function Select() {
  return (
    <Lottie
      loop
      animationData={lottieJson}
      play
      // style 은 선택
      style={{ width: 400, height: 400 }}
    />
  );
}
