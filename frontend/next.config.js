module.exports = {
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
};
