"use client";

import {
  Menu,
  MenuButton,
  MenuItem,
  MenuItems,
  Transition,
} from "@headlessui/react";
import { Fragment } from "react";
import { BiCommentDetail } from "react-icons/bi";
import { FaList, FaPlus, FaRegFolderOpen } from "react-icons/fa";
import { HiDocumentDuplicate, HiDotsHorizontal } from "react-icons/hi";
import {
  MdDelete,
  MdEdit,
  MdKeyboardArrowUp,
  MdKeyboardDoubleArrowUp,
  MdOutlineAttachFile,
} from "react-icons/md";

export default function TaskBoardItem(props: unknown) {
  const PRIORITY = {
    high: <MdKeyboardDoubleArrowUp />,
    medium: <MdKeyboardArrowUp />,
    normal: "",
  };

  return (
    <div className="h-fit w-full rounded bg-white p-4 shadow-md">
      <div className="flex w-full justify-between">
        <div className="flex flex-1 items-center gap-1 text-sm font-medium text-yellow-600">
          <span className="text-lg">{PRIORITY.medium}</span>
          <span className="uppercase">medium priority</span>
        </div>
        <div className="relative inline-block text-left">
          <Menu as="div" className="relative inline-block text-left">
            <div>
              <MenuButton className="inline-flex w-full justify-center rounded-md px-4 py-2 text-sm font-medium text-gray-600">
                <HiDotsHorizontal />
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
                <div className="space-y-2 px-1 py-1">
                  <MenuItem>
                    <a
                      href="#"
                      className="group flex w-full items-center rounded-md px-2 py-2 text-sm text-gray-900 hover:bg-blue-500 hover:text-white"
                    >
                      <FaRegFolderOpen />
                      Open task
                    </a>
                  </MenuItem>
                  <MenuItem>
                    <a
                      href="#"
                      className="group flex w-full items-center rounded-md px-2 py-2 text-sm text-gray-900 hover:bg-blue-500 hover:text-white"
                    >
                      <MdEdit />
                      Edit
                    </a>
                  </MenuItem>
                  <MenuItem>
                    <a
                      href="#"
                      className="group flex w-full items-center rounded-md px-2 py-2 text-sm text-gray-900 hover:bg-blue-500 hover:text-white"
                    >
                      <HiDocumentDuplicate />
                      Duplicate
                    </a>
                  </MenuItem>
                  <MenuItem>
                    <a
                      href="#"
                      className="group flex w-full items-center rounded-md px-2 py-2 text-sm text-gray-900 hover:bg-blue-500 hover:text-white"
                    >
                      <FaPlus />
                      Add subtask
                    </a>
                  </MenuItem>
                </div>
                <div className="space-y-2 px-1 py-1">
                  <MenuItem>
                    <a
                      href="#"
                      className="group flex w-full items-center rounded-md px-2 py-2 text-base text-red-600 hover:bg-red-600 hover:text-white"
                    >
                      <MdDelete />
                      Delete
                    </a>
                  </MenuItem>
                </div>
              </MenuItems>
            </Transition>
          </Menu>
        </div>
      </div>
      <div className="flex items-center gap-2">
        <div className="h-4 w-4 rounded-full bg-blue-600"></div>
        <h4 className="line-clamp-1 text-black">Test task</h4>
      </div>
      <span className="text-sm text-gray-600">9-Feb-2024</span>
      <div className="my-2 w-full border-t border-gray-200"></div>
      <div className="mb-2 flex items-center justify-between">
        <div className="flex items-center gap-3">
          <div className="flex items-center gap-1 text-sm text-gray-600">
            <BiCommentDetail />
            <span>0</span>
          </div>
          <div className="flex items-center gap-1 text-sm text-gray-600">
            <MdOutlineAttachFile />
            <span>0</span>
          </div>
          <div className="flex items-center gap-1 text-sm text-gray-600">
            <FaList />
            <span>0/1</span>
          </div>
        </div>
        <div className="flex flex-row-reverse">
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
      </div>
      <div className="border-t border-gray-200 py-4">
        <h5 className="line-clamp-1 text-base text-black">Test</h5>
        <div className="space-x-8 p-4">
          <span className="text-sm text-gray-600">9-Feb-2024</span>
          <span className="rounded0full bg-blue-600/10 px-3 py-1 font-medium text-blue-700">
            tutorial
          </span>
        </div>
      </div>
      <div className="w-full pb-2">
        <button
          type="button"
          className="disabled::text-gray-300 flex w-full items-center gap-4 text-sm font-semibold text-gray-500 disabled:cursor-not-allowed"
        >
          <FaPlus />
          <span>ADD SUBTASK</span>
        </button>
      </div>
    </div>
  );
}
