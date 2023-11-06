/**
 * @type {import('next').NextConfig}
 */
const nextConfig = {
  output: "standalone",
  images: {
    domains: ["product-image.kurly.com", "img-cf.kurly.com"], // 이미지를 가져올 도메인을 추가해주세요.
    remotePatterns: [
      {
        protocol: "https",
        hostname: "my-alfs-aws-bucket.s3.ap-northeast-2.amazonaws.com",
        port: "",
        pathname: "/static/**",
      },
    ],
  },
  experimental: {
    serverActions: true,
  },
  env: {
    // NEXTAUTH_URL: "http://localhost:3000",
    NEXTAUTH_URL: "https://k9c204.p.ssafy.io",
    NEXTAUTH_SECRET: "+0wC9vJe7FO6ce0io2FBk9xEalqKfwv0EGiBhG2xgPA=",
  },
};

module.exports = nextConfig;
