import React, { useState } from 'react';
import {
  Menu,
  MenuButton,
  MenuList,
  MenuItem,
  MenuItemOption,
  MenuGroup,
  MenuOptionGroup,
  MenuDivider,
  Button
} from '@chakra-ui/react';
import { BsChevronDown } from 'react-icons/bs';

type Props = {};

export default function TopNav({}: Props) {
  // const [open, setOpen] = useState(false);
  // const openMenu: () => void = () => {
  //   setOpen(true);
  // };
  return (
    <div className="min-w-[1000px] h-[30px] flex justify-center">
      <div className="min-w-[1000px] flex items-center justify-end">
        <div>
          <p>신대혁님</p>
        </div>
        <div>
          <Menu>
            <MenuButton as={Button} rightIcon={<BsChevronDown />}>
              Actions
            </MenuButton>
            <MenuList bg={'blue'}>
              <MenuItem>Download</MenuItem>
              <MenuItem>Create a Copy</MenuItem>
              <MenuItem>Mark as Draft</MenuItem>
              <MenuItem>Delete</MenuItem>
              <MenuItem>Attend a Workshop</MenuItem>
            </MenuList>
          </Menu>
        </div>
      </div>
    </div>
  );
}
