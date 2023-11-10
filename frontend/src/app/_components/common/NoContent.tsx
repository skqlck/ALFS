import React from 'react'

type Props = {
    message : string
}

export default function NoContent({message}: Props) {
  return (
    <div className='w-[800px] h-[200px] mt-[124px] flex justify-center'>
        <span className=' font-bold text-3xl text-gray-600 text-center'>
            {message}
        </span>
    </div>
  )
}