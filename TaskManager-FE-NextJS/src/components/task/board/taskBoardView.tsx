"use client";

import TaskBoardItem from "./taskBoardItem";

export default function TaskBoardView(props: unknown) {
  return (
    <div className="grid w-full grid-cols-1 gap-4 py-4 sm:grid-cols-2 md:grid-cols-3 2xl:gap-10">
      <TaskBoardItem />
      <TaskBoardItem />
      <TaskBoardItem />
    </div>
  );
}
