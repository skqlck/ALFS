import React from "react";

import Lottie from "react-lottie-player";

import lottieJson from "../../_asset/data/Animation - 1698721798331.json";

export default function NoEmoji() {
  return (
    <Lottie
      loop
      animationData={lottieJson}
      play
      // style 은 선택
      style={{ width: 200, height: 200 }}
    />
  );
}
