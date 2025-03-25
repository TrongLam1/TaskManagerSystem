"use client";

import { useState } from "react";
import { FaList, FaPlus } from "react-icons/fa";
import { RxDashboard } from "react-icons/rx";
import TabView from "./tabView";
import TaskBoardView from "./board/taskBoardView";
import TaskListView from "./list/taskListView";
import TaskModal from "../modal/task/taskModal";
import { usePathname } from "next/navigation";
import { useSession } from "next-auth/react";
import { hasAdmin } from "@/utils";

const tabs = [
  {
    number: 0,
    name: "Board View",
    icon: <RxDashboard />,
    view: <TaskBoardView key={`tab-${0}`} />,
  },
  {
    number: 1,
    name: "List View",
    icon: <FaList />,
    view: <TaskListView key={`tab-${1}`} />,
  },
];

const titles = new Map([
  ["task", "Task"],
  ["to-do", "To Do"],
  ["in-progress", "In Progress"],
  ["completed", "Completed"],
]);

export default function TaskTable(props: unknown) {
  const pathname = usePathname();
  const { data: session } = useSession();

  const [openModalTask, setOpenModalTask] = useState<boolean>(false);
  const [selectedView, setSelectedView] = useState(0);

  const arrayPath = pathname.split("/");

  const closeModal = () => {
    setOpenModalTask(false);
  };

  return (
    <>
      <div className="w-full">
        <div className="mb-4 flex items-center justify-between">
          <h2 className="text-2xl font-semibold capitalize">
            {titles.get(arrayPath[1])}
          </h2>
          {arrayPath.includes("task") && hasAdmin(session) && (
            <button
              type="button"
              className="flex items-center gap-1 rounded-md bg-blue-600 px-3 py-2 text-white outline-none 2xl:py-2.5"
              onClick={() => setOpenModalTask(true)}
            >
              <FaPlus />
              <span>Create Task</span>
            </button>
          )}
        </div>
        <div className="w-full px-1 sm:px-0">
          <div className="flex space-x-6 rounded-xl p-1">
            {tabs.map((tab: unknown, index: number) => {
              return (
                <TabView
                  tab={tab}
                  key={`tab-${index}`}
                  selected={selectedView}
                  setSelected={setSelectedView}
                />
              );
            })}
          </div>
          <div className="mt-2 w-full">
            {tabs.map((tab: unknown) => {
              if (selectedView === tab.number) {
                return tab.view;
              }
            })}
          </div>
        </div>
      </div>
      {openModalTask && <TaskModal close={closeModal} />}
    </>
  );
}
