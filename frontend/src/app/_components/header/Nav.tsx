import React from "react";
import TopNav from "./TopNav";
import MiddleNav from "./MiddleNav";
import BottomNav from "./BottomNav";

type Props = {};

export default function Nav({}: Props) {
  return (
    <>
      <TopNav />
      <MiddleNav />
      <BottomNav />
    </>
  );
}
