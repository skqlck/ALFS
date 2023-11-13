import React from "react";

import Lottie from "react-lottie-player";
// Alternatively:
// import Lottie from 'react-lottie-player/dist/LottiePlayerLight'

import lottieJson from "../../_asset/data/Animation - 1698655588026.json";

export default function Food() {
  return (
    <Lottie
      loop
      animationData={lottieJson}
      play
      // style 은 선택
      style={{ width: 300, height: 300 }}
    />
  );
}
