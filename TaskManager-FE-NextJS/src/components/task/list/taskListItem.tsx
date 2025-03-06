"use client";

import { BiCommentDetail } from "react-icons/bi";
import { FaList } from "react-icons/fa";
import { MdOutlineAttachFile } from "react-icons/md";

export default function TaskListItem(props: unknown) {
  return (
    <tr className="border-b border-gray-200 text-gray-600 hover:bg-gray-300/10">
      <td className="py-2">
        <div className="flex items-center gap-2">
          <div className="h-4 w-4 rounded-full bg-blue-600"></div>
          <p className="line-clamp-2 w-full text-base text-black">
            Duplicate - Duplicate - Review Code Changes
          </p>
        </div>
      </td>
      <td className="py-2">
        <div className="flex items-center gap-1">
          <span className="text-lg text-red-600"></span>
          <span className="line-clamp-1 capitalize">high priority</span>
        </div>
      </td>
      <td className="py-2">
        <span className="text-sm text-gray-600">9-Feb-2024</span>
      </td>
      <td className="py-2">
        <div className="flex items-center gap-3">
          <div className="flex items-center gap-1 text-sm text-gray-600">
            <BiCommentDetail />
            <span>0</span>
          </div>
          <div className="flex items-center gap-1 text-sm text-gray-600 dark:text-gray-400">
            <MdOutlineAttachFile />
            <span>0</span>
          </div>
          <div className="flex items-center gap-1 text-sm text-gray-600 dark:text-gray-400">
            <FaList />
            <span>0</span>
          </div>
        </div>
      </td>
      <td className="py-2">
        <div className="flex">
          <div className="-mr-1 flex h-7 w-7 items-center justify-center rounded-full bg-blue-600 text-sm text-white">
            <div className="px-4">
              <div className="relative">
                <button
                  type="button"
                  className="group inline-flex items-center outline-none"
                >
                  <span>AB</span>
                </button>
              </div>
            </div>
          </div>
          <div className="-mr-1 flex h-7 w-7 items-center justify-center rounded-full bg-yellow-600 text-sm text-white">
            <div className="px-4">
              <div className="relative">
                <button
                  type="button"
                  className="group inline-flex items-center outline-none"
                >
                  <span>AB</span>
                </button>
              </div>
            </div>
          </div>
          <div className="-mr-1 flex h-7 w-7 items-center justify-center rounded-full bg-red-600 text-sm text-white">
            <div className="px-4">
              <div className="relative">
                <button
                  type="button"
                  className="group inline-flex items-center outline-none"
                >
                  <span>AB</span>
                </button>
              </div>
            </div>
          </div>
        </div>
      </td>
      <td className="flex justify-end gap-2 py-2 md:gap-4">
        <button
          type="button"
          className="px-3 py-2 text-sm text-blue-600 outline-none hover:text-blue-500 sm:px-0 md:text-base"
        >
          Edit
        </button>
        <button
          type="button"
          className="px-3 py-2 text-sm text-red-700 outline-none hover:text-red-500 sm:px-0 md:text-base"
        >
          Delete
        </button>
      </td>
    </tr>
  );
}
