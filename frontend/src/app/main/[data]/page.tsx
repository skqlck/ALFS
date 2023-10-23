import React from "react";

type Props = { data: string };

export default function page({ params }: { params: Props }) {
  return (
    <>
      <div>{params.data}</div>
      <div>asdf</div>
    </>
  );
}
