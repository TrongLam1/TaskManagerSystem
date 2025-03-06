"use client";

import clsx from "clsx";
import Link from "next/link";
import { usePathname } from "next/navigation";
import { FaTasks, FaTrashAlt, FaUsers } from "react-icons/fa";
import {
  MdDashboard,
  MdOutlineAddTask,
  MdOutlinePendingActions,
  MdSettings,
  MdTaskAlt,
} from "react-icons/md";

const linkData = [
  {
    label: "Dashboard",
    link: "/dashboard",
    icon: <MdDashboard />,
  },
  {
    label: "Tasks",
    link: "/task/1",
    icon: <FaTasks />,
  },
  {
    label: "Completed",
    link: "/completed/1",
    icon: <MdTaskAlt />,
  },
  {
    label: "In Progress",
    link: "/in-progress/1",
    icon: <MdOutlinePendingActions />,
  },
  {
    label: "To Do",
    link: "/to-do/1",
    icon: <MdOutlinePendingActions />,
  },
  {
    label: "Team",
    link: "/user/1",
    icon: <FaUsers />,
  },
  {
    label: "Trash",
    link: "/trashed",
    icon: <FaTrashAlt />,
  },
];

export default function Sidebar() {
  const location = usePathname();
  const path = location.split("/")[1];

  //   const sidebarLinks = user?.isAdmin ? linkData : linkData.slice(0, 5);

  const closeSidebar = () => {};

  const NavLink = ({ el }) => {
    return (
      <Link
        href={el.link}
        onClick={closeSidebar}
        className={clsx(
          "flex w-full items-center gap-2 rounded-full px-3 py-2 text-base text-gray-800 lg:w-3/4",
          path === el?.link?.split("/")[0]
            ? "bg-blue-700 text-neutral-100"
            : "hover:bg-[#2564ed2d]",
        )}
      >
        {el.icon}
        <span className="hover:text-[#2564ed]">{el.label}</span>
      </Link>
    );
  };

  return (
    <div className="flex h-full w-full flex-col gap-6 p-5">
      <h1 className="flex items-center gap-1">
        <p className="rounded-full bg-blue-600 p-2">
          <MdOutlineAddTask className="text-2xl font-black text-white" />
        </p>
        <span className="text-2xl font-bold text-black">TaskMe</span>
      </h1>

      <div className="flex flex-1 flex-col gap-y-5 py-8">
        {linkData.map((link) => (
          <NavLink el={link} key={link.label} />
        ))}
      </div>

      <div className="">
        <button className="flex w-full items-center gap-2 p-2 text-lg text-gray-800">
          <MdSettings />
          <span>Settings</span>
        </button>
      </div>
    </div>
  );
}
