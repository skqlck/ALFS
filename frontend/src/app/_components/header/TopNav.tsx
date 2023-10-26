import React from "react";
import { Menu, MenuButton, MenuList, MenuItem, Button } from "@chakra-ui/react";
import { BsChevronDown } from "react-icons/bs";

export default function TopNav() {
  return (
    <div className="min-w-[1000px] h-[30px] flex justify-center">
      <div className="min-w-[1000px] flex items-center justify-end">
        <div className="mr-[20px]">
          <p>신대혁님</p>
        </div>
        <div>
          <Menu>
            <MenuButton>고객센터</MenuButton>
            <MenuList>
              <MenuItem>1:1 문의</MenuItem>
              <MenuItem>상품 문의</MenuItem>
            </MenuList>
          </Menu>
        </div>
      </div>
    </div>
  );
}
