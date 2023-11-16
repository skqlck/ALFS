"use client";
import React, { useEffect, useState } from "react";
import {
  Table,
  Thead,
  Tbody,
  Tr,
  Th,
  Td,
  TableContainer,
  Badge,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Text,
} from "@chakra-ui/react";
import { BoardAll, BoardDetail } from "@/app/api/board/Board";
import Link from "next/link";
import { useSearchParams } from "next/navigation";

export default function Page() {
  const [allergy, setAllergy] = useState([]);
  const [hate, setHate] = useState([]);
  const [data, setData] = useState([]);
  const [detailData, setDetailData] = useState<any>([]);
  const params = useSearchParams();
  const detail = params.get("num");

  const GetData = async () => {
    const res = await BoardAll();
    setData(res?.data.data.reverse());
  };

  const GetDetail = async (e: any) => {
    const res = await BoardDetail(e);
    setDetailData(res?.data.data);
  };

  useEffect(() => {
    GetData();
  }, []);

  useEffect(() => {
    GetDetail(detail);
  }, [detail]);
  return (
    <div>
      <div className="border-black border-b-[4px] mb-[10px]">
        <p className="text-[30px]">1:1 문의</p>
        <div className="flex items-center justify-end mb-[50px]"></div>
      </div>
      {detailData ? (
        <div className="mt-[50px]">
          <Card className="mb-[50px]">
            <CardBody>
              <p className="text-[25px] mb-[30px]">제목 : {detailData.title}</p>
              <p>{detailData.content}</p>
            </CardBody>
          </Card>

          <Card>
            <CardBody>
              <p className="text-[25px] mb-[30px]">답변</p>
              <p>{detailData.comment}</p>
            </CardBody>
          </Card>
        </div>
      ) : (
        <TableContainer whiteSpace={"pre-line"}>
          <Table variant="simple">
            <Thead>
              <Tr>
                <Th width={150}>번호</Th>
                <Th width={450}>제목</Th>
                <Th width={150}>답변여부</Th>
              </Tr>
            </Thead>
            <Tbody>
              {data.map((data: any, idx: number) => (
                <>
                  <Tr>
                    <Td>{data.board_id}</Td>

                    <Td>
                      <Link href={`all?num=${data.board_id}`}>{data.title}</Link>
                    </Td>

                    <Td>
                      {data.status ? (
                        <Badge colorScheme="green">답변완료</Badge>
                      ) : (
                        <Badge colorScheme="red">답변미완료</Badge>
                      )}
                    </Td>
                  </Tr>
                </>
              ))}
            </Tbody>
          </Table>
        </TableContainer>
      )}
    </div>
  );
}
