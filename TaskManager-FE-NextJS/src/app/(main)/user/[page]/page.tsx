import { GetAllUsers } from "@/app/api/userApi";
import UserTable from "@/components/user/userTable";
import { auth } from "../../../../../auth";
import { hasAdmin } from "@/utils";

export const metadata = {
  title: "User",
};

export default async function UserPage({
  params,
}: {
  params: { page: number };
}) {
  const session = await auth();
  let res;

  if (hasAdmin(session)) {
    res = await GetAllUsers(session?.user.token, params.page);
  } else {
    res = await GetAllUsers(session?.user.token, params.page, true);
  }

  const users = res.status === 200 ? res.data!.data : null;
  const totalItems = res.status === 200 ? res.data!.totalItems : 0;
  const totalPages = res.status === 200 ? res.data!.totalPages : 1;

  return (
    <UserTable data={users} totalItems={totalItems} totalPages={totalPages} />
  );
}
