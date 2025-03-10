"use client";

import clsx from "clsx";
import moment from "moment";
import { useSession } from "next-auth/react";
import {
  MdKeyboardArrowDown,
  MdKeyboardArrowUp,
  MdKeyboardDoubleArrowUp,
} from "react-icons/md";

const ICONS = {
  high: <MdKeyboardDoubleArrowUp />,
  medium: <MdKeyboardArrowUp />,
  low: <MdKeyboardArrowDown />,
};

export default function TaskTableDashboard(props: unknown) {
  const TableHeader = () => {
    return (
      <thead className="border-b border-gray-300">
        <tr className="text-left text-black">
          <th className="py-2">Task Title</th>
          <th className="py-2">Priority</th>
          <th className="py-2">Team</th>
          <th className="hidden py-2 md:block">Created At</th>
        </tr>
      </thead>
    );
  };

  const TableRow = (task) => {
    return (
      <tr className="border-b border-gray-300 text-gray-600 hover:bg-gray-300/10">
        <td className="py-2">
          <div className="flex items-center gap-2">
            <div
              className={clsx("h-4 w-4 rounded-full", TASK_TYPE[task.stage])}
            />

            <p className="text-base text-black">{task.title}</p>
          </div>
        </td>

        <td className="py-2">
          <div className="flex items-center gap-1">
            <span className={clsx("text-lg", PRIOTITYSTYELS[task.priority])}>
              {ICONS[task.priority]}
            </span>
            <span className="capitalize">{task.priority}</span>
          </div>
        </td>

        <td className="py-2">
          <div className="flex">
            {task.team.map((m, index) => (
              <div
                key={index}
                className={clsx(
                  "-mr-1 flex h-7 w-7 items-center justify-center rounded-full text-sm text-white",
                  BGS[index % BGS.length],
                )}
              >
                <UserInfo user={m} />
              </div>
            ))}
          </div>
        </td>
        <td className="hidden py-2 md:block">
          <span className="text-base text-gray-600">
            {moment(task?.date).fromNow()}
          </span>
        </td>
      </tr>
    );
  };

  return (
    <div className="w-full rounded bg-white px-2 pb-4 pt-4 shadow-md md:w-2/3 md:px-4">
      <table className="w-full">
        <TableHeader />
      </table>
    </div>
  );
}
