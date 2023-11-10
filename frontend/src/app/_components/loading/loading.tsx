import { Spinner } from "@chakra-ui/react";
import React from "react";

type Props = {};

export default function Loading({}: Props) {
  return (
    <div className="flex justify-center items-center">
      <Spinner color="green.500" size="xl"></Spinner>
    </div>
  );
}
