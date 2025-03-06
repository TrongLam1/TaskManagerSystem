"use client";

import { Popover, PopoverButton, PopoverPanel } from "@headlessui/react";
import { FaRegBell } from "react-icons/fa";
import Notification from "./notification";

const data = [
  {
    _id: "65c5bbf3787832cf99f28e6d",
    team: [
      "65c202d4aa62f32ffd1303cc",
      "65c27a0e18c0a1b750ad5cad",
      "65c30b96e639681a13def0b5",
    ],
    text: "New task has been assigned to you and 2 others. The task priority is set a normal priority, so check and act accordingly. The task date is Thu Feb 29 2024. Thank you!!!",
    task: null,
    notiType: "alert",
    isRead: [],
    createdAt: "2024-02-09T05:45:23.353Z",
    updatedAt: "2024-02-09T05:45:23.353Z",
    __v: 0,
  },
  {
    _id: "65c5f12ab5204a81bde866ab",
    team: [
      "65c202d4aa62f32ffd1303cc",
      "65c30b96e639681a13def0b5",
      "65c317360fd860f958baa08e",
    ],
    text: "New task has been assigned to you and 2 others. The task priority is set a high priority, so check and act accordingly. The task date is Fri Feb 09 2024. Thank you!!!",
    task: {
      _id: "65c5f12ab5204a81bde866a9",
      title: "Test task",
    },
    notiType: "alert",
    isRead: [],
    createdAt: "2024-02-09T09:32:26.810Z",
    updatedAt: "2024-02-09T09:32:26.810Z",
    __v: 0,
  },
];

export default function NotificationPanel() {
  return (
    <>
      <Popover>
        <PopoverButton className="flex text-sm/6 font-semibold text-white/50 focus:outline-none data-[active]:text-white data-[hover]:text-white data-[focus]:outline-1 data-[focus]:outline-white">
          <div className="relative">
            <div className="inline-flex items-center outline-none">
              <div className="relative flex h-8 w-8 items-center justify-center text-gray-800">
                <FaRegBell className="text-xl" />
                <span className="absolute right-1 top-0 h-4 w-4 rounded-full bg-red-600 text-center text-sm font-semibold text-white">
                  2
                </span>
              </div>
            </div>
          </div>
        </PopoverButton>
        <PopoverPanel
          transition
          anchor="bottom end"
          className="divide-y divide-white/5 rounded-xl border bg-white text-sm/6 transition duration-200 ease-in-out [--anchor-gap:var(--spacing-5)] data-[closed]:-translate-y-1 data-[closed]:opacity-0"
        >
          {data && data.length > 0 && (
            <div className="w-screen max-w-md flex-auto overflow-hidden rounded-3xl bg-white text-sm leading-6">
              <div className="p-4">
                {data.map((item: unknown, index: number) => {
                  return <Notification item={item} key={`message-${index}`} />;
                })}
              </div>
            </div>
          )}
        </PopoverPanel>
      </Popover>
    </>
  );
}
