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
  Input,
  Button,
} from "@chakra-ui/react";
import {
  BoardAll,
  BoardDetail,
  BoardSupervisorAll,
  BoardSupervisorComment,
  BoardSupervisorDetail,
} from "@/app/api/board/Board";
import Link from "next/link";
import { useSearchParams } from "next/navigation";
import PropsModal from "@/app/_components/modal/PropsModal";

export default function Page() {
  const [data, setData] = useState([]);
  const [detailData, setDetailData] = useState<any>([]);
  const params = useSearchParams();
  const detail = params.get("num");
  const [text, setText] = useState<string>();
  const [show, setShow] = useState(false);

  const GetData = async () => {
    const res = await BoardSupervisorAll();
    setData(res?.data.data.reverse());
  };

  const GetDetail = async (e: any) => {
    const res = await BoardSupervisorDetail(e);
    setDetailData(res?.data.data);
  };

  const sendText = async () => {
    const data = { id: detail, comment: text };
    const res = await BoardSupervisorComment(data);
    if (res?.data.code === 200) {
      setShow(true);
    }
  };

  useEffect(() => {
    GetData();
  }, []);

  useEffect(() => {
    GetDetail(detail);
  }, [detail]);
  return (
    <div>
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
              {detailData.comment === null && (
                <>
                  <Input
                    placeholder="답변을 입력해주세요."
                    width={500}
                    height={200}
                    focusBorderColor="green.500"
                    marginBottom="20px"
                    onChange={(e) => {
                      setText(e.target.value);
                    }}
                  />
                  <Button variant="outline" colorScheme="whatsapp" marginLeft="50px" onClick={sendText}>
                    답변 제출하기
                  </Button>
                </>
              )}
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
                    <Td>{data.id}</Td>

                    <Td>
                      <Link href={`board?num=${data.id}`}>{data.title}</Link>
                    </Td>

                    <Td>
                      {data.comment !== null ? (
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
      {show && <PropsModal props="답변이 작성되었습니다." />}
    </div>
  );
}
