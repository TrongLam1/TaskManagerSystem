"use client";

import clsx from "clsx";

export default function Card(props: unknown) {
  const { stat } = props;

  return (
    <div className="flex h-32 w-full items-center justify-between rounded-md bg-white p-5 shadow-md">
      <div className="flex h-full flex-1 flex-col justify-between">
        <p className="text-base text-gray-600">{stat.label}</p>
        <span className="text-2xl font-semibold">{stat.total}</span>
        <span className="text-sm text-gray-400">{"110 last month"}</span>
      </div>

      <div
        className={clsx(
          "flex h-10 w-10 items-center justify-center rounded-full text-white",
          stat.bg,
        )}
      >
        {stat.icon}
      </div>
    </div>
  );
}
