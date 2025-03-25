"use client";

import clsx from "clsx";
import { useEffect } from "react";
import { RiErrorWarningLine } from "react-icons/ri";

export default function ConfirmModal(props: unknown) {
  const { selected, confirm, close, type } = props;

  useEffect(() => {}, [selected]);

  return (
    <div className="fixed left-0 right-0 top-0 z-50 flex h-full max-h-full w-full items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-70 md:inset-0">
      <div className="relative max-h-full w-full max-w-md p-4">
        <div className="relative rounded-lg bg-white shadow-sm dark:bg-gray-700">
          <div className="rounded-md bg-white p-4 text-center md:p-5">
            <RiErrorWarningLine
              className={clsx(
                "mx-auto mb-4 h-12 w-12",
                type === "Delete" && "text-red-600",
                type === "Restore" && "text-green-600",
              )}
            />
            <h3 className="mb-5 text-lg font-normal text-black">
              Are you sure you want to <br />
              <strong
                className={clsx(
                  type === "Delete" && "text-red-600",
                  type === "Restore" && "text-green-600",
                )}
              >
                &quot;{type}&quot; {selected.name}
              </strong>{" "}
              ?
            </h3>
            <button
              type="button"
              className="inline-flex items-center rounded-lg bg-red-600 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300"
              onClick={confirm}
            >
              Yes, I'm sure
            </button>
            <button
              type="button"
              className="ms-3 rounded-lg border border-gray-600 bg-white px-5 py-2.5 text-sm font-medium text-gray-900 hover:bg-gray-100 focus:z-10 focus:outline-none focus:ring-4 focus:ring-gray-100"
              onClick={close}
            >
              No, cancel
            </button>
          </div>
        </div>
      </div>
    </div>
  );
}
