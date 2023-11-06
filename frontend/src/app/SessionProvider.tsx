"use client";
import { SessionProvider } from "next-auth/react";

export function SessionProviders({ children }: { children: any }) {
  return <SessionProvider>{children}</SessionProvider>;
}
