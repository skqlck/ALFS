'use client'

import { GetBigSaleList } from '@/app/api/bigsalelist/BigSaleList'
import React, { useEffect, useState } from 'react'
import NoContent from '@/app/_components/common/NoContent';
import BigSaleList from '@/app/_components/bigsalecard/BigSaleList';

type BigSaleItem = {
  productId : number
  productName : string
  productTitle : string
  productPrice : number
  productSale : number
  productDelivery : string
  productSeller : string
  productPack : string
  productCount : string
  productWeight : string
  productAllergy : string
  productExpireDate : string
  productInformation : string
  productBuyType : string
  productStock : number
  productContent : string
  supervisorId : number
  productImg : string
  status : number
  count : number
  salePrice : number
  start : string
  end : string
}

export default function Page() {
  const [OnSaleList, setOnSaleList] = useState<BigSaleItem[]>([]);
  const [UpComingList, setUpComingList] = useState<BigSaleItem[]>([]);
  const [EndedList, setEndedList] = useState<BigSaleItem[]>([]);
  const [idx, setIdx] = useState(1);
  const SaleList = [UpComingList, OnSaleList, EndedList]
  const messages = [
    "✖ 예정된 특가 상품이 없습니다. ✖",
    "✖ 특가 상품이 없습니다. ✖",
    "✖ 종료된 특가 상품이 없습니다. ✖"
  ]

  useEffect(()=>{
    const initial = async () => {
      try {
        const response:any = await GetBigSaleList();
        let onsale : BigSaleItem[] = [];
        let upcoming : BigSaleItem[] = [];
        let ended : BigSaleItem[] = [];
        response.forEach((item : BigSaleItem)=>{
          if (item.status == 0){ // 대기
            upcoming.push(item)
          }
          else if (item.status == 1){ // 진행
            onsale.push(item)
          }
          else if (item.status == 2){ // 종료
            ended.push(item)
          }
        })
        setUpComingList(upcoming)
        setOnSaleList(onsale)
        setEndedList(ended)
      } catch (error) {
        console.log(error)
      }
    }
    initial();
  }, []);

  return (
    <div className='flex flex-col items-center'>
      <div className='Container w-[800px] h-auto mt-[20px]'>

        <div className='flex justify-around'>
          <span onClick={()=>{setIdx(0)}} className={(idx == 0 ? ' text-green-600 font-extrabold mb-3 border border-green-600 rounded' : 'text-gray-500') + ' w-[150px] h-[30px] text-center pt-1'}>UP COMING</span>
          <span onClick={()=>{setIdx(1)}} className={(idx == 1 ? ' text-green-600 font-extrabold mb-3 border border-green-600 rounded' : 'text-gray-500') + ' w-[150px] h-[30px] text-center pt-1'}>NOW</span>
          <span onClick={()=>{setIdx(2)}} className={(idx == 2 ? ' text-green-600 font-extrabold mb-3 border border-green-600 rounded' : 'text-gray-500') + ' w-[150px] h-[30px] text-center pt-1'}>SOLD OUT</span>
        </div>

        <div className='mb-3'>
          {SaleList[idx] && SaleList[idx].length > 0 ? 
            BigSaleList(SaleList[idx]) : 
            <NoContent message={messages[idx]}/>
          }
        </div>
      </div>
    </div>
  )
}