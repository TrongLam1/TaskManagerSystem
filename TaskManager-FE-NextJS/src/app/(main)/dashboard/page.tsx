import Card from "@/components/card/card";
import Chart from "@/components/chart/chart";
import TaskTableDashboard from "@/components/task/taskTableDashboard";
import UserTableDashboard from "@/components/user/userTableDashboard";
import { FaNewspaper } from "react-icons/fa";
import { FaArrowsToDot } from "react-icons/fa6";
import { LuClipboardList } from "react-icons/lu";
import { MdAdminPanelSettings } from "react-icons/md";
import { auth } from "../../../../auth";
import { GetAllUsers } from "@/app/api/userApi";
import { useEffect } from "react";
import { revalidateUser } from "@/utils/revalidate";

export const metadata = {
  title: "Dashboard",
};

const stats = [
  {
    _id: "1",
    label: "TOTAL TASK",
    total: 0,
    icon: <FaNewspaper />,
    bg: "bg-[#1d4ed8]",
  },
  {
    _id: "2",
    label: "COMPLETED TASK",
    total: 0,
    icon: <MdAdminPanelSettings />,
    bg: "bg-[#0f766e]",
  },
  {
    _id: "3",
    label: "TASK IN PROGRESS ",
    total: 0,
    icon: <LuClipboardList />,
    bg: "bg-[#f59e0b]",
  },
  {
    _id: "4",
    label: "TODOS",
    total: 0,
    icon: <FaArrowsToDot />,
    bg: "bg-[#be185d]",
  },
];

export default async function Dashboard() {
  const session = await auth();
  const res = await GetAllUsers(session?.user.token, 1, true);
  const users = res.status === 200 ? res.data!.data : null;

  return (
    <div className="h-full py-4">
      <div className="grid grid-cols-1 gap-5 md:grid-cols-4">
        {stats.map((stat: unknown, index: number) => {
          return <Card key={index} stat={stat} />;
        })}
      </div>
      <div className="my-16 w-full rounded bg-white p-4 shadow-sm">
        <h4 className="text-xl font-semibold text-gray-600">
          Chart by Priority
        </h4>
        <Chart />
      </div>
      <div className="flex w-full flex-col gap-4 py-8 md:flex-row 2xl:gap-10">
        <TaskTableDashboard />
        <UserTableDashboard users={users} />
      </div>
    </div>
  );
}
