"use client";

import {
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  Transition,
} from "@headlessui/react";
import { Fragment } from "react";
import { FaUser, FaUserLock } from "react-icons/fa";
import { IoIosLogOut } from "react-icons/io";

export default function Profile() {
  return (
    <div>
      <Menu as="div" className="relative inline-block text-left">
        <div>
          <MenuButton className="h-10 w-10 rounded-full bg-blue-600 text-white 2xl:h-12 2xl:w-12">
            <span className="font-semibold text-white">C</span>
          </MenuButton>
        </div>

        <Transition
          as={Fragment}
          enter="transition ease-out duration-100"
          enterFrom="transform opacity-0 scale-95"
          enterTo="transform opacity-100 scale-100"
          leave="transition ease-in duration-75"
          leaveFrom="transform opacity-100 scale-100"
          leaveTo="transform opacity-0 scale-95"
        >
          <MenuItems
            transition
            className="focus:outline-hidden data-closed:scale-95 data-closed:transform data-closed:opacity-0 data-enter:duration-100 data-enter:ease-out data-leave:duration-75 data-leave:ease-in absolute right-0 z-10 mt-2 w-56 origin-top-right divide-y divide-gray-100 rounded-md bg-white shadow-lg ring-1 ring-black/5 transition"
          >
            <div className="px-4 py-3">
              <MenuItem>
                <a
                  href="#"
                  className="group flex w-full items-center rounded-md px-2 py-2 text-base text-gray-700"
                >
                  <FaUser className="mr-2" />
                  Profile
                </a>
              </MenuItem>
              <MenuItem>
                <a
                  href="#"
                  className="group flex w-full items-center rounded-md px-2 py-2 text-base text-gray-700"
                >
                  <FaUserLock className="mr-2" />
                  Change Password
                </a>
              </MenuItem>
            </div>
            <div className="px-4 py-2">
              <MenuItem>
                <a
                  href="#"
                  className="group flex w-full items-center rounded-md px-2 py-2 text-base text-red-600"
                >
                  <IoIosLogOut className="mr-2" />
                  Logout
                </a>
              </MenuItem>
            </div>
          </MenuItems>
        </Transition>
      </Menu>
    </div>
  );
}
