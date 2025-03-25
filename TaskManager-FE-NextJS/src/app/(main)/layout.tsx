import Navbar from "@/components/navbar/navbar";
import Sidebar from "@/components/sidebar/sidebar";
import { auth } from "../../../auth";
import { redirect } from "next/navigation";

export default async function MainLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session = await auth();

  if (session?.user === undefined) redirect("/login");

  return (
    <div className="flex h-full w-full bg-[#f3f4f6] text-black">
      <div className="sticky top-0 hidden h-screen w-1/5 bg-white md:block">
        <Sidebar />
      </div>
      <div className="main flex-1">
        <Navbar />
        <div className="p-4 2xl:px-10">{children}</div>
      </div>
    </div>
  );
}
