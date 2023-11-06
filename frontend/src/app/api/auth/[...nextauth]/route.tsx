import NextAuth from "next-auth/next";
import CredentialsProvider from "next-auth/providers/credentials";

const handler = NextAuth({
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        identifier: { label: "Username", type: "text", placeholder: "jsmith" },
        password: { label: "Password", type: "password" },
        userId: { label: "Password", type: "password" },
      },
      async authorize(credentials: any) {
        const res = await fetch("https://k9c204.p.ssafy.io/api/member/login", {
          method: "POST",
          body: JSON.stringify(credentials),
          headers: { "Content-Type": "application/json" },
        });
        const user = await res.json();
        console.log(user);
        user.name = credentials.userId;
        // If no error and we have user data, return it
        if (res.ok && user) {
          return user;
        }
        return null;
      },
    }),
  ],
  callbacks: {
    async session({ session, user }: any) {
      // Send properties to the client, like an access_token and user id from a provider.

      return session;
    },
  },
  pages: {
    // error: "/main",
    // signIn: "/login",
  },
  secret: process.env.NEXTAUTH_SECRET,
});

export { handler as GET, handler as POST };
