"use client";

import { useState } from "react";
import { FaPlus } from "react-icons/fa";
import UserModal from "../modal/user/userModal";
import User from "./user";
import ConfirmModal from "../modal/dialog/confirmModal";
import { DeleteUsers, GetUserByEmail, RestoreUsers } from "@/app/api/userApi";
import { useSession } from "next-auth/react";
import { revalidateUser } from "@/utils/revalidate";
import { toast } from "react-toastify";
import { hasAdmin } from "@/utils";

export default function UserTable(props: unknown) {
  const { data } = props;
  const { data: session } = useSession();

  const [selectedUser, setSelectedUser] = useState<IUser>();
  const [openUserModal, setOpenUserModal] = useState<boolean>(false);
  const [openConfirm, setOpenConfirm] = useState<boolean>(false);
  const [typeModal, setTypeModal] = useState<string>();

  const isAdmin = hasAdmin(session);

  const closeModal = () => {
    setOpenConfirm(false);
    setOpenUserModal(false);
  };

  const handleOpenConfirmModal = (user: IUser) => {
    setOpenConfirm(true);
    setSelectedUser(user);
  };

  const handleOpenEditUser = async (email: string) => {
    const res = await GetUserByEmail(session?.user.token, email);
    if (res.status === 200) {
      setSelectedUser(res.data);
      setOpenUserModal(true);
    }
  };

  const handleDeleteUser = async () => {
    const emails = [selectedUser?.email];
    const res = await DeleteUsers(session?.user.token, emails);
    if (res.status === 200) {
      toast.success(res.message);
      revalidateUser();
    } else {
      toast.error(res.message);
    }
    closeModal();
  };

  const handleRestoreUser = async () => {
    const emails = [selectedUser?.email];
    const res = await RestoreUsers(session?.user.token, emails);
    if (res.status === 200) {
      toast.success(res.message);
      revalidateUser();
    } else {
      toast.error(res.message);
    }
    closeModal();
  };

  return (
    <>
      <div className="mb-6 w-full px-0 md:px-1">
        <div className="mb-8 flex items-center justify-between">
          <h2 className="text-2xl font-semibold capitalize">Team Members</h2>
          {isAdmin && (
            <button
              type="button"
              className="flex items-center gap-1 rounded-md bg-blue-600 px-3 py-2 text-white outline-none 2xl:py-2.5"
              onClick={() => setOpenUserModal(true)}
            >
              <FaPlus />
              <span>Add New User</span>
            </button>
          )}
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
              {data &&
                data.length > 0 &&
                data.map((user: IUser, index: number) => {
                  return (
                    <User
                      key={`user-${index}`}
                      user={user}
                      edit={handleOpenEditUser}
                      selected={handleOpenConfirmModal}
                      setType={setTypeModal}
                      isAdmin={isAdmin}
                    />
                  );
                })}
            </tbody>
          </table>
        </div>
      </div>
      {openUserModal && (
        <UserModal
          setOpen={setOpenUserModal}
          token={session?.user.token}
          userData={selectedUser}
          type="Edit"
        />
      )}
      {openConfirm && (
        <ConfirmModal
          type={typeModal}
          confirm={
            typeModal === "Delete" ? handleDeleteUser : handleRestoreUser
          }
          close={closeModal}
          selected={selectedUser}
        />
      )}
    </>
  );
}
