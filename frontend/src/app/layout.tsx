import type { Metadata } from "next";
import { Inter } from "next/font/google";
import "./globals.css";
import localFont from "next/font/local";
import Nav from "./_components/header/Nav";
import Footer from "./_components/footer/Footer";

import { Providers } from "./providers";

// Font files can be colocated inside of `app`
const myFont = localFont({
  src: "./fonts/GmarketSansTTFMedium.ttf",
  display: "swap",
});

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "ALFS",
  description: "Generated by create next app",
};

export default async function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body className={myFont.className}>
        <Providers>
          <Nav></Nav>
          {children}
          <Footer />
        </Providers>
      </body>
    </html>
  );
}
