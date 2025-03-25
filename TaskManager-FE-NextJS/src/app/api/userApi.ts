import { sendRequest } from "@/utils/api";

interface ICreateUser {
  name: string;
  email: string;
  password: string;
}

interface IUpdateUser {
  name: string;
  email: string;
  title: string;
  role: string[];
}

export async function CreateNewUser(
  token: string,
  data: { name: string; title: string; email: string },
) {
  return await sendRequest<IBackendRes<ICreateUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/create`,
    method: "POST",
    headers: { Authorization: `Bearer ${token}` },
    body: data,
  });
}

export async function UpdateUser(
  token: string,
  data: { name: string; title: string; email: string },
) {
  return await sendRequest<IBackendRes<IUpdateUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/update`,
    method: "PUT",
    headers: { Authorization: `Bearer ${token}` },
    body: data,
  });
}

export async function ResetPassword(email: string) {
  return await sendRequest<IBackendRes<IUpdateUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/auth/reset-password`,
    method: "PUT",
    queryParams: { email },
  });
}

export async function ChangePassword(
  token: string,
  data: { oldPassword: string; newPassword: string },
) {
  return await sendRequest<IBackendRes<IUpdateUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/change-password`,
    method: "PUT",
    headers: { Authorization: `Bearer ${token}` },
    body: data,
  });
}

export async function GetProfile(token: string) {
  return await sendRequest<IBackendRes<IUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/profile`,
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
  });
}

export async function GetUserByEmail(token: string, email: string) {
  return await sendRequest<IBackendRes<IUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/get-one/${email}`,
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
    nextOption: {
      next: { tags: [`user-${email}`] },
      cache: "force-cache",
    },
  });
}

export async function GetAllUsers(
  token: string,
  pageNo: number,
  isActive?: boolean,
  pageSize?: number,
  sortBy?: string,
  sortDirection?: string,
) {
  return await sendRequest<IBackendRes<IUser>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/all`,
    method: "GET",
    headers: { Authorization: `Bearer ${token}` },
    queryParams: { pageNo, pageSize, sortBy, sortDirection, isActive },
    nextOption: {
      next: { tags: [`list-users-${pageNo}-${isActive}`] },
      cache: "force-cache",
    },
  });
}

export async function RestoreUsers(token: string, emails: string[]) {
  const query = emails.map((email) => `${email}`).join(",");
  return await sendRequest<IBackendRes<string>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/restore`,
    method: "PUT",
    headers: { Authorization: `Bearer ${token}` },
    queryParams: { emails: query },
  });
}

export async function DeleteUsers(token: string, emails: string[]) {
  const query = emails.map((email) => `${email}`).join(",");
  return await sendRequest<IBackendRes<string>>({
    url: `${process.env.NEXT_PUBLIC_BACKEND_URL}/user/remove`,
    method: "DELETE",
    headers: { Authorization: `Bearer ${token}` },
    queryParams: { emails: query },
  });
}
