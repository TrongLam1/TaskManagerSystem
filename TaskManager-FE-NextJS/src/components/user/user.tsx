"use client";

import { getInitials } from "@/utils";
import clsx from "clsx";
import { useSession } from "next-auth/react";
import { useEffect, useState } from "react";

export default function User(props: unknown) {
  const { user, selected, edit, setType, isAdmin } = props;
  const { data: session } = useSession();

  const [name, setName] = useState<string>("");
  const [title, setTitle] = useState<string>("");
  const [email, setEmail] = useState<string>("");

  useEffect(() => {
    setName(user.name);
    setTitle(user.title);
    setEmail(user.email);
  }, [user]);

  return (
    <>
      <tr className="border-b border-gray-200 text-gray-600 hover:bg-gray-400/10">
        <td className="flex py-2">
          <div className="flex min-w-36 items-center gap-3">
            <div className="flex h-9 w-9 items-center justify-center rounded-full bg-blue-700 text-sm text-white">
              <span className="text-center text-xs md:text-sm">
                {getInitials(user.name)}
              </span>
            </div>
            {user.name}
          </div>
        </td>
        <td className="py-2 text-center">{user.title}</td>
        <td className="py-2 text-center">{user.email}</td>
        <td className="py-2 text-center">Analyst</td>
        <td className="text-center">
          <button
            disabled
            className={clsx(
              "w-fit rounded-full px-4 py-1",
              user.active && "bg-blue-200",
              !user.active && "bg-red-200",
            )}
          >
            {user.active ? "Active" : "Disable"}
          </button>
        </td>
        {isAdmin && (
          <td className="flex justify-center gap-4 p-2 text-center">
            <button
              className="px-3 py-2 font-semibold text-blue-600 outline-none hover:text-blue-500 sm:px-0"
              onClick={() => edit(user.email)}
            >
              Edit
            </button>
            {user.active ? (
              <button
                className="px-3 py-2 font-semibold text-red-700 outline-none hover:text-red-500 sm:px-0"
                onClick={() => {
                  selected(user);
                  setType("Delete");
                }}
              >
                Delete
              </button>
            ) : (
              <button
                className="px-3 py-2 font-semibold text-green-700 outline-none hover:text-green-500 sm:px-0"
                onClick={() => {
                  selected(user);
                  setType("Restore");
                }}
              >
                Restore
              </button>
            )}
          </td>
        )}
      </tr>
    </>
  );
}
