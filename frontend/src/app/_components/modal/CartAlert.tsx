import React, { useState, useEffect } from "react";
import {
  Popover,
  PopoverContent,
  PopoverHeader,
  PopoverBody,
  PopoverArrow,
  PopoverCloseButton,
  PopoverTrigger,
  Button,
} from "@chakra-ui/react";
import Image from "next/image";

export default function CartAlert(props: any) {
  console.log(props);
  const [show, setShow] = useState(true);
  const [top, setTop] = useState<number>();
  const [left, setLeft] = useState<number>();

  useEffect(() => {
    const cart = document.getElementById("cart");
    if (cart) {
      const rect = cart.getBoundingClientRect();
      setTop(rect.bottom);
      setLeft(rect.left - 150);
    }
  }, []);

  const popoverStyle = {
    top: top ? `${top + window.scrollY}px` : "0",
    left: left ? `${left}px` : "0",
  };

  return (
    <div>
      {/* className={`fixed`} style={popoverStyle} */}
      <Popover isOpen={show} colorScheme="whatsapp">
        <PopoverContent className="fixed shadow-xl" style={popoverStyle}>
          <PopoverArrow />
          <PopoverHeader>장바구니에 상품이 담겼습니다.</PopoverHeader>
          <PopoverBody>
            <div className="flex items-center">
              <Image src={props.props[0]} alt="" width={50} height={50}></Image>
              <p className="ml-[20px]">{props.props[1]}</p>
            </div>
          </PopoverBody>
        </PopoverContent>
      </Popover>
    </div>
  );
}
