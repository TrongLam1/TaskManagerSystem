"use client";

import { useState } from "react";
import { FaPlus } from "react-icons/fa";
import UserModal from "../modal/user/userModal";
import User from "./user";
import ConfirmModal from "../modal/dialog/confirmModal";

export default function UserTable() {
  const [selectedUser, setSelectedUser] = useState(null);
  const [openUserModal, setOpenUserModal] = useState(false);
  const [openConfirm, setOpenConfirm] = useState(false);

  const closeModal = () => {
    setOpenConfirm(false);
    setOpenUserModal(false);
  };

  const handleOpenConfirmModal = (userId: number) => {
    setOpenConfirm(true);
    setSelectedUser(userId);
  };

  const handleDeleteUser = () => {
    alert("Delete " + selectedUser);
    closeModal();
  };

  return (
    <>
      <div className="mb-6 w-full px-0 md:px-1">
        <div className="mb-8 flex items-center justify-between">
          <h2 className="text-2xl font-semibold capitalize">Team Members</h2>
          <button
            type="button"
            className="flex items-center gap-1 rounded-md bg-blue-600 px-3 py-2 text-white outline-none 2xl:py-2.5"
            onClick={() => setOpenUserModal(true)}
          >
            <FaPlus />
            <span>Add New User</span>
          </button>
        </div>
        <div className="rounded bg-white px-2 py-4 shadow-md md:px-4">
          <table className="mb-5 w-full">
            <thead className="border-b border-gray-300">
              <tr className="text-left text-black">
                <th className="py-2 text-center">Full Name</th>
                <th className="py-2 text-center">Title</th>
                <th className="py-2 text-center">Email</th>
                <th className="py-2 text-center">Role</th>
                <th className="py-2 text-center">Active</th>
              </tr>
            </thead>
            <tbody>
              <User deleteUser={() => handleOpenConfirmModal(1)} />
              <User />
              <User />
            </tbody>
          </table>
        </div>
      </div>
      {openUserModal && <UserModal setOpen={setOpenUserModal} />}
      {openConfirm && (
        <ConfirmModal
          type="remove"
          confirm={handleDeleteUser}
          close={closeModal}
        />
      )}
    </>
  );
}
