"use client";

import { MdOutlineSearch } from "react-icons/md";
import Profile from "../profile/profile";
import NotificationPanel from "../notification/notificationPanel";

export default function Navbar() {
  return (
    <div className="flex items-center justify-between bg-white px-4 py-3">
      <div className="flex w-64 items-center gap-4 rounded-full bg-gray-100 px-3 py-2">
        <MdOutlineSearch className="text-xl text-gray-500" />
        <input
          type="text"
          className="border-none bg-inherit outline-none"
          placeholder="Search ..."
        />
      </div>
      <div className="flex items-center gap-2">
        <NotificationPanel />
        <Profile />
      </div>
    </div>
  );
}
