"use client";

import React, { useEffect, useState } from "react";
import LoginModal from "@/app/_components/needLogin/LoginModal";

export default function RootLayout({ children }: { children: React.ReactNode }) {
  const [show, setShow] = useState(false);

  useEffect(() => {
    const id = localStorage.getItem("id");
    if (!id) {
      setShow(true);
    }
  }, []);

  return (
    <>
      {show && <LoginModal />}

      {children}
    </>
  );
}
