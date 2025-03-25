"use client";

import { UpdateUser } from "@/app/api/userApi";
import { revalidateUser } from "@/utils/revalidate";
import clsx from "clsx";
import { useSession } from "next-auth/react";
import { useEffect, useState } from "react";
import { IoMdClose } from "react-icons/io";
import { toast } from "react-toastify";

export default function UserModal(props: unknown) {
  const { setOpen, userData, type, token } = props;

  const { update } = useSession();

  const [name, setName] = useState<string>("");
  const [title, setTitle] = useState<string>("");
  const [email, setEmail] = useState<string>("");
  const [role, setRole] = useState<string>("");

  let header = "";

  if (type === "profile") {
    header = "Update Profile";
  } else {
    header = userData ? "Update User" : "Add New User";
  }

  useEffect(() => {
    if (userData) {
      setName(userData.name ?? "");
      setTitle(userData.title ?? "");
      setEmail(userData.email);
      // setRole(userData.role);
    }
  }, []);

  const handleUpdateUser = async () => {
    const data = {
      name: name,
      title: title,
      email: userData.email,
    };

    const res = await UpdateUser(token, data);
    if (res.status === 200) {
      if (type === "profile") {
        await update(res.data);
      } else {
        revalidateUser();
      }
      toast.success(res.message);
      setOpen(false);
    } else {
      toast.error(res.message);
    }
  };

  return (
    <div className="fixed bottom-0 left-0 right-0 top-0 z-10 flex h-full max-h-full w-full items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-70 md:inset-0">
      <div className="relative max-h-full w-full max-w-lg p-4">
        <div className="relative z-10 rounded-lg bg-white shadow-sm">
          <div className="flex items-center justify-between rounded-t border-b border-gray-200 p-4 md:p-5">
            <h3 className="text-2xl font-semibold text-gray-900">{header}</h3>
            <button
              type="button"
              className="ms-auto inline-flex h-8 w-8 items-center justify-center rounded-lg bg-transparent text-sm text-gray-400 hover:bg-gray-200 hover:text-gray-900"
              data-modal-toggle="crud-modal"
              onClick={() => setOpen(false)}
            >
              <IoMdClose className="text-xl" />
              <span className="sr-only">Close modal</span>
            </button>
          </div>
          <form className="p-4 md:p-5">
            <div className="mb-4 grid grid-cols-2 gap-4">
              <div className="col-span-2">
                <label
                  htmlFor="name"
                  className="mb-2 block text-base font-medium text-gray-900"
                >
                  Full Name
                </label>
                <input
                  type="text"
                  name="name"
                  className="focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-base text-gray-900"
                  placeholder="Full name"
                  onChange={(e) => setName(e.target.value)}
                  value={name}
                  required={true}
                />
              </div>
              <div className="col-span-2">
                <label
                  htmlFor="title"
                  className="mb-2 block text-base font-medium text-gray-900"
                >
                  Title
                </label>
                <input
                  type="text"
                  name="title"
                  className="focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-base text-gray-900"
                  placeholder="Title"
                  onChange={(e) => setTitle(e.target.value)}
                  value={title}
                  required={true}
                />
              </div>
              <div className="col-span-2">
                <label
                  htmlFor="email"
                  className="mb-2 block text-base font-medium text-gray-900"
                >
                  Email
                </label>
                <input
                  type="text"
                  name="email"
                  className={clsx(
                    "focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-base text-gray-900",
                    userData && "cursor-not-allowed outline-none",
                  )}
                  placeholder="Email"
                  value={email}
                  readOnly
                  required={true}
                />
              </div>
              <div className="col-span-2">
                <label
                  htmlFor="role"
                  className="mb-2 block text-base font-medium text-gray-900"
                >
                  Role
                </label>
                <input
                  type="text"
                  name="role"
                  className={clsx(
                    "focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-base text-gray-900",
                    type === "profile" && "cursor-not-allowed outline-none",
                  )}
                  placeholder="Role"
                  onChange={(e) => setRole(e.target.value)}
                  value={role}
                  required={true}
                  disabled={type === "profile" ? true : false}
                />
              </div>
            </div>
            <div className="flex w-full justify-end">
              <button
                className="mr-2 inline-flex items-center rounded-lg bg-red-700 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300"
                onClick={() => setOpen(false)}
              >
                Cancel
              </button>
              <button
                type="button"
                className="inline-flex items-center rounded-lg bg-blue-700 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300"
                onClick={handleUpdateUser}
              >
                Submit
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
