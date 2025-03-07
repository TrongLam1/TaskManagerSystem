"use client";

import { MdDelete, MdOutlineRestore } from "react-icons/md";

export default function TrashItem(props: unknown) {
  const { task, destroy, restore, setSelected } = props;
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
          <span className="line-clamp-1 capitalize">high</span>
        </div>
      </td>
      <td className="py-2 text-center capitalize md:text-start">To Do</td>
      <td className="py-2 text-sm">
        <span className="text-sm text-gray-600">9-Feb-2024</span>
      </td>
      <td className="flex justify-end gap-2 py-2 md:gap-4">
        <button
          type="button"
          className="px-3 py-2 outline-none"
          onClick={() => restore(true)}
          // onClick={() => setSelected(task)}
        >
          <MdOutlineRestore className="text-xl text-gray-500" />
        </button>
        <button
          type="button"
          className="px-3 py-2 outline-none"
          onClick={() => destroy(true)}
          // onClick={() => setSelected(task)}
        >
          <MdDelete className="text-xl text-red-600" />
        </button>
      </td>
    </tr>
  );
}
