'use client'

import React from "react"

type Props = {
    rate1 : number | null
    rate2 : number | null
    selected : number
}
export function RateBar({rate1, rate2, selected} : Props){
    const rate1Style = {
        width:`${rate1? rate1*6 : 0}px`
    }
    const rate2Style = {
        width:`${rate2? rate2*6 : 0}px`
    }
    return (
        <div className="mt-10">
            { rate1 ? (
                <div className="flex">
                    <div style={rate1Style} className={(selected == 1 ? "bg-green-500" : "bg-gray-400") + " text-center rounded-l-full"}>
                        {rate1 + "%"}
                    </div>
                    <div style={rate2Style} className={(selected == 2 ? "bg-green-500" : "bg-gray-400") + " text-center rounded-r-full"}>
                        {rate2 + "%"}
                    </div>
                </div>
            ) : (
                <div className="w-[600px] text-center rounded-full bg-gray-400">
                    ?
                </div>
            )}
        </div>
    )
}