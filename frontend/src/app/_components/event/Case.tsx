'use client'

import React from "react"
type Props = {
    name : string
    vote : Function
    choose_case : number
    selected : number
}

export function Case({name, vote, choose_case, selected} : Props){
    return (
        <button
            onClick={()=>{vote(choose_case)}} 
            className={
                "rounded-full w-[250px] h-[250px] border-2 " +
                (selected==choose_case ? "border-green-500 scale-110 " : " ") + 
                (selected != 0 ? "disabled " : " ") +
                (selected == 0 ? "hover:scale-110 hover:border-green-500 " : " ")
            }
        >
            <span className=" text-4xl font-bold">{name}</span>
        </button>
    )
}