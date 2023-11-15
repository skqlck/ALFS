import type { Metadata } from "next";
import "./globals.css";
import Nav from "./_components/header/Nav";
import Footer from "./_components/footer/Footer";
import { Providers } from "./providers";
import RecentData from "./_components/header/RecentData";

export const metadata: Metadata = {
  title: "ALFS",
  description: "Generated by create next app",
};

export default async function RootLayout({ children }: { children: React.ReactNode }) {
  return (
    <html lang="en">
      <body>
        <Providers>
          <Nav></Nav>
          <RecentData />
          {children}
          <Footer />
        </Providers>
      </body>
    </html>
  );
}
