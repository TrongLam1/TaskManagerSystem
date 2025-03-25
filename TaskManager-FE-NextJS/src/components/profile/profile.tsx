"use client";

import { GetProfile } from "@/app/api/userApi";
import { getInitials } from "@/utils";
import {
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  Transition,
} from "@headlessui/react";
import { signOut, useSession } from "next-auth/react";
import { Fragment, useEffect, useState } from "react";
import { FaUser, FaUserLock } from "react-icons/fa";
import { IoIosLogOut } from "react-icons/io";
import { toast } from "react-toastify";
import UserModal from "../modal/user/userModal";
import ModalChangePassword from "../modal/changePassword/changePassword";

export default function Profile() {
  const { data: session } = useSession();

  const [openModal, setOpenModal] = useState<boolean>(false);
  const [openChangePass, setOpenChangePass] = useState<boolean>(false);
  const [profile, setProfile] = useState<IUser>();
  const [avatar, setAvatar] = useState<string>("");

  useEffect(() => {
    if (session?.user) {
      setProfile(session?.user.user);
    }
  }, [session?.user]);

  useEffect(() => {
    if (profile && profile.name) {
      setAvatar(getInitials(profile.name));
    } else {
      setAvatar("");
    }
  }, [profile]);

  const handleGetProfile = async () => {
    const token = session?.user.token;
    const res = await GetProfile(token);
    if (res.status === 200) {
      setProfile(res.data);
      setOpenModal(true);
    } else {
      toast.error("An error occurred. Please try again.");
    }
  };

  const handleSignOut = () => {
    signOut({ redirectTo: "/login" });
  };

  return (
    <>
      <div>
        <Menu as="div" className="relative inline-block text-left">
          <div>
            <MenuButton className="h-10 w-10 rounded-full bg-blue-600 text-white 2xl:h-12 2xl:w-12">
              <span className="font-semibold text-white">{avatar}</span>
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
              <div className="py-3">
                <MenuItem>
                  <div className="cursor-pointer px-4 hover:bg-blue-300">
                    <a
                      className="group flex w-full items-center rounded-md px-2 py-2 text-base text-gray-700 hover:text-white"
                      onClick={handleGetProfile}
                    >
                      <FaUser className="mr-2" />
                      Profile
                    </a>
                  </div>
                </MenuItem>
                <MenuItem>
                  <div className="cursor-pointer px-4 hover:bg-blue-300">
                    <a
                      className="group flex w-full cursor-pointer items-center rounded-md px-2 py-2 text-base text-gray-700 hover:text-white"
                      onClick={() => setOpenChangePass(true)}
                    >
                      <FaUserLock className="mr-2" />
                      Change Password
                    </a>
                  </div>
                </MenuItem>
              </div>
              <div className="py-2">
                <MenuItem>
                  <div className="cursor-pointer px-4 hover:bg-red-500">
                    <a
                      className="group flex w-full cursor-pointer items-center rounded-md px-2 py-2 text-base text-red-600 hover:text-white"
                      onClick={handleSignOut}
                    >
                      <IoIosLogOut className="mr-2" />
                      Logout
                    </a>
                  </div>
                </MenuItem>
              </div>
            </MenuItems>
          </Transition>
        </Menu>
      </div>
      {openModal && (
        <UserModal
          setOpen={setOpenModal}
          userData={profile}
          type="profile"
          token={session?.user.token}
        />
      )}
      {openChangePass && (
        <ModalChangePassword
          close={setOpenChangePass}
          signOut={handleSignOut}
        />
      )}
    </>
  );
}
