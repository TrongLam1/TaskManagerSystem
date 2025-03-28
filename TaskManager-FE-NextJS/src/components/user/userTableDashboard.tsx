"use client";

import { getInitials } from "@/utils";
import moment from "moment";

export default function UserTableDashboard(props: unknown) {
  const { users } = props;

  const TableHeader = () => (
    <thead className="border-b border-gray-300">
      <tr className="text-left text-black">
        <th className="py-2">Full Name</th>
        <th className="py-2">Created At</th>
      </tr>
    </thead>
  );

  const TableRow = ({ user }) => (
    <tr className="border-b border-gray-200 text-gray-600 hover:bg-gray-400/10">
      <td className="py-2">
        <div className="flex items-center gap-3">
          <div className="flex h-9 w-9 items-center justify-center rounded-full bg-violet-700 text-sm text-white">
            <span className="text-center">{getInitials(user?.name)}</span>
          </div>
          <div>
            <p> {user.name}</p>
            <span className="text-xs text-black">{user?.roles[0]?.name}</span>
          </div>
        </div>
      </td>
      <td className="py-2 text-sm">{moment(user?.createdAt).fromNow()}</td>
    </tr>
  );

  return (
    <div className="h-fit w-full rounded bg-white px-2 py-4 shadow-md md:w-1/3 md:px-6">
      <table className="mb-5 w-full">
        <TableHeader />
        <tbody>
          {users?.map((user: IUser, index: number) => (
            <TableRow key={`user-${index}`} user={user} />
          ))}
        </tbody>
      </table>
    </div>
  );
}
