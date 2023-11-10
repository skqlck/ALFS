'use client'

import React from 'react'
import Card from '../card/Card'
import Link from 'next/link'
import {useState, useEffect} from 'react'

type Props = {
    name: string;
    image: string;
    id: number;
    title: string;
    price: number;
    sale: number;
    delivery: string;
    status : number;
    start : string;
    end : string;
    member_id : string;
}

function parseLeftTime(mills : number) {
  const hours : number = Math.floor(mills / 3600000)
  mills -= hours * 3600000
  const minutes : number = Math.floor(mills / 60000)
  mills -= minutes * 60000
  const seconds : number = Math.floor(mills / 1000)
  return  (hours > 0 ? hours + "h " : "") + (minutes > 0 ? minutes + "m " : "") + (seconds > 0 ? seconds + "s" : "")
}

export default function BigSaleCard({ name, image, id, title, price, delivery, status, start, end, sale, member_id }: Props){
  const [leftTime, setLeftTime] = useState(0);

  useEffect(()=>{
    if (status == 0 && Date.now() < Date.parse(start)) {
      setLeftTime(Date.parse(start) - Date.now())
      setInterval(()=>{
        setLeftTime(Date.parse(start) - Date.now())}
      , 1000)
      console.log(name)
    };
  }, [])

  return (
    <div className='mx-auto'>
      <Link href={{ pathname: `/detail/${id}` }}>
        {status == 0 && Date.now() < Date.parse(start) ?
          <span className='absolute text-center text-lg font-bold text-red-600 ml-3.5 mt-24 bg-white rounded w-[150px] pt-1 -rotate-12'>
            {parseLeftTime(leftTime)}
          </span>
        : null}
        {status == 2 ?
          <span className='absolute text-center text-lg font-bold text-gray-600 ml-3.5 mt-24 bg-white rounded w-[150px] pt-1 -rotate-12'>
            SOLD OUT
          </span>
        : null}
        <Card
          member_id={member_id}
          name={name}
          image={image}
          id={id}
          title={title}
          price={price}
          sale={sale}
          delivery={delivery}
          filterCode={[]}
        />
      </Link>
    </div>
  )
};