import React from "react";
import { AiOutlinePlus } from "react-icons/ai";
import { Table, Thead, Tbody, Tr, Th, Td, TableContainer, Checkbox } from "@chakra-ui/react";
import { BiEdit } from "react-icons/bi";
type Props = {};

export default function page({}: Props) {
  return (
    <div>
      <div className="border-black border-b-[4px] mb-[23px]">
        <p className="text-[30px]">나의 배송지</p>
        <div className="flex items-center justify-end mb-[26px]">
          <AiOutlinePlus />새 배송지 추가
        </div>
      </div>
      <div>
        <TableContainer whiteSpace={"pre-line"}>
          <Table variant="simple">
            <Thead>
              <Tr>
                <Th width={100}>선택</Th>
                <Th width={250}>주소</Th>
                <Th width={100}>수령인</Th>
                <Th width={200}>연락처</Th>
                <Th width={100}>수정</Th>
              </Tr>
            </Thead>
            <Tbody>
              <Tr>
                <Td>
                  <Checkbox colorScheme="green" defaultChecked size="lg"></Checkbox>
                </Td>
                <Td>
                  광주 광산구 흑석동 759-1 <br />
                  여름 201호
                </Td>
                <Td>신대혁</Td>
                <Td>010-4092-5645</Td>
                <Td>
                  <BiEdit className="text-[20px]"></BiEdit>
                </Td>
              </Tr>
            </Tbody>
          </Table>
        </TableContainer>
      </div>
    </div>
  );
}
