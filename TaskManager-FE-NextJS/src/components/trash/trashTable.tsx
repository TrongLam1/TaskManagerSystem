"use client";

import { MdDelete, MdOutlineRestore } from "react-icons/md";
import TrashItem from "./trashItem";
import { useState } from "react";
import ConfirmModal from "../modal/dialog/confirmModal";

export default function TrashTable(props: unknown) {
  const [openModalDelete, setOpenModalDelete] = useState(false);
  const [openModalRestore, setOpenModalRestore] = useState(false);
  const [selectedTask, setSelectedTask] = useState();

  const closeModal = () => {
    setOpenModalDelete(false);
    setOpenModalRestore(false);
  };

  const handleDeleteTask = () => {};

  const handleRestoreTask = () => {};

  return (
    <>
      <div className="w-full">
        <div className="mb-4 flex items-center justify-between">
          <h2 className="text-2xl font-semibold capitalize">Trashed Tasks</h2>
          <div className="flex">
            <button
              type="button"
              className="flex items-center gap-1 rounded-md px-3 py-2 outline-none 2xl:py-2.5"
              onClick={() => setOpenModalRestore(true)}
            >
              <MdOutlineRestore />
              <span>Restore All</span>
            </button>
            <button
              type="button"
              className="flex items-center gap-1 rounded-md px-3 py-2 text-red-500 outline-none 2xl:py-2.5"
              onClick={() => setOpenModalDelete(true)}
            >
              <MdDelete />
              <span>Delete All</span>
            </button>
          </div>
        </div>
        <main className="w-full px-1 sm:px-0">
          <div className="rounded bg-white px-2 pb-9 pt-4 shadow-md md:px-4">
            <div className="overflow-x-auto">
              <table className="w-full">
                <thead className="w-full border-b border-gray-300">
                  <tr className="w-full text-left text-black">
                    <th className="py-2">Task Title</th>
                    <th className="py-2">Priority</th>
                    <th className="line-clamp-1 py-2">Stage</th>
                    <th className="py-2">Modified On</th>
                  </tr>
                </thead>
                <tbody>
                  <TrashItem
                    destroy={setOpenModalDelete}
                    restore={setOpenModalRestore}
                    setSelected={setSelectedTask}
                  />
                  <TrashItem
                    destroy={setOpenModalDelete}
                    restore={setOpenModalRestore}
                    setSelected={setSelectedTask}
                  />
                </tbody>
              </table>
            </div>
          </div>
        </main>
      </div>
      {openModalDelete && (
        <ConfirmModal
          selected={selectedTask}
          confirm={handleDeleteTask}
          close={closeModal}
          type="delete"
        />
      )}
      {openModalRestore && (
        <ConfirmModal
          selected={selectedTask}
          confirm={handleRestoreTask}
          close={closeModal}
          type="restore"
        />
      )}
    </>
  );
}
