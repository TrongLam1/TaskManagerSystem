"use client";

import moment from "moment";
import { BiSolidMessageRounded } from "react-icons/bi";
import { HiBellAlert } from "react-icons/hi2";

const ICONS = {
  alert: (
    <HiBellAlert className="h-5 w-5 text-gray-600 group-hover:text-indigo-600" />
  ),
  message: (
    <BiSolidMessageRounded className="h-5 w-5 text-gray-600 group-hover:text-indigo-600" />
  ),
};

export default function Notification(props: unknown) {
  const { item } = props;

  return (
    <a
      className="block rounded-lg px-3 py-2 transition hover:bg-white/5"
      href="#"
    >
      <div className="mt-1 flex h-8 w-8 items-center justify-center rounded-lg bg-gray-200 group-hover:bg-white">
        {ICONS[item.notiType]}
      </div>

      <div className="cursor-pointer">
        <div className="flex items-center gap-3 font-semibold capitalize text-gray-900">
          <p> {item.notiType}</p>
          <span className="text-xs font-normal lowercase">
            {moment(item.createdAt).fromNow()}
          </span>
        </div>
        <p className="mt-1 line-clamp-1 text-gray-600">{item.text}</p>
      </div>
    </a>
  );
}
