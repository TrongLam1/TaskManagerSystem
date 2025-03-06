"use client";

import TaskListItem from "./taskListItem";

export default function TaskListView(props: unknown) {
  return (
    <div className="w-full">
      <div className="rounded bg-white px-2 pb-9 pt-4 shadow-md md:px-4">
        <div className="overflow-x-auto">
          <table className="w-full">
            <thead className="w-full border-b border-gray-300">
              <tr className="w-full text-left text-black">
                <th className="py-2">Task Title</th>
                <th className="py-2">Priority</th>
                <th className="line-clamp-1 py-2">Created At</th>
                <th className="py-2">Assets</th>
                <th className="py-2">Team</th>
              </tr>
            </thead>
            <tbody>
              <TaskListItem />
              <TaskListItem />
              <TaskListItem />
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
