"use client";

import { ChangePassword } from "@/app/api/userApi";
import clsx from "clsx";
import { useSession } from "next-auth/react";
import { useState } from "react";
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import { IoMdClose } from "react-icons/io";
import { IoEye, IoEyeOff } from "react-icons/io5";
import { toast } from "react-toastify";

export default function ModalChangePassword(props: unknown) {
  const { close, signOut } = props;
  const { data: session } = useSession();

  const [password, setPassword] = useState<string>();
  const [newPassword, setNewPassword] = useState<string>();
  const [confirm, setConfirm] = useState<string>();
  const [loading, setLoading] = useState<boolean>(false);
  const [showPassword, setShowPassword] = useState<unknown>({
    password: false,
    newPassword: false,
    confirmPassword: false,
  });

  const togglePasswordVisibility = (field: string) => {
    setShowPassword((prevState) => ({
      ...prevState,
      [field]: !prevState[field],
    }));
  };

  const handleChangePassword = async (e: { preventDefault: () => void }) => {
    e.preventDefault();

    setLoading(true);

    const isValid = checkValidPassword();
    if (isValid) {
      const data = { oldPassword: password, newPassword };
      const res = await ChangePassword(session?.user.token, data);
      if (res.status === 200) {
        toast.success(res.message);
        close();
        setTimeout(() => {
          signOut();
        }, 1500);
      } else {
        toast.error(res.message);
      }
    }

    setLoading(false);
  };

  const checkValidPassword = () => {
    if (
      password!.length < 8 ||
      newPassword!.length < 8 ||
      confirm!.length < 8
    ) {
      toast.error("Password must be more than 7 characters.");
      return false;
    }

    if (newPassword !== confirm) {
      toast.error("Confirm password is wrong.");
      return false;
    }

    return true;
  };

  return (
    <div className="fixed bottom-0 left-0 right-0 top-0 z-10 flex h-full max-h-full w-full items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-70 md:inset-0">
      <div className="relative max-h-full w-full max-w-lg p-4">
        <div className="relative z-10 rounded-lg bg-white shadow-sm">
          <div className="flex items-center justify-between rounded-t border-b border-gray-200 p-4 md:p-5">
            <h3 className="text-2xl font-semibold text-gray-900">
              Change password
            </h3>
            <button
              type="button"
              className="ms-auto inline-flex h-8 w-8 items-center justify-center rounded-lg bg-transparent text-sm text-gray-400 hover:bg-gray-200 hover:text-gray-900"
              data-modal-toggle="crud-modal"
              onClick={() => close(false)}
            >
              <IoMdClose className="text-xl" />
              <span className="sr-only">Close modal</span>
            </button>
          </div>
          <form onSubmit={handleChangePassword} className="p-4 md:p-5">
            <div className="mb-4">
              <div>
                <label
                  htmlFor="currentPassword"
                  className="block text-sm/6 font-medium text-gray-900"
                >
                  Current Password
                </label>
                <div className="relative mt-1">
                  <input
                    type={showPassword["password"] ? "text" : "password"}
                    required
                    autoComplete="currentPassword"
                    placeholder="Password"
                    onChange={(e) => setPassword(e.target.value)}
                    className="focus:border-primary-600 block w-full rounded-md border border-gray-300 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 sm:text-sm/6"
                  />
                  <button
                    type="button"
                    onClick={() => togglePasswordVisibility("password")}
                    className="absolute right-4 top-3 cursor-pointer text-gray-500"
                  >
                    {showPassword["password"] ? <IoEyeOff /> : <IoEye />}
                  </button>
                </div>
              </div>
              <div className="mt-2">
                <label
                  htmlFor="newPassword"
                  className="block text-sm/6 font-medium text-gray-900"
                >
                  New Password
                </label>
                <div className="relative mt-1">
                  <input
                    type={showPassword["newPassword"] ? "text" : "password"}
                    required
                    autoComplete="newPassword"
                    placeholder="New Password"
                    onChange={(e) => setNewPassword(e.target.value)}
                    className="focus:border-primary-600 block w-full rounded-md border border-gray-300 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 sm:text-sm/6"
                  />
                  <button
                    type="button"
                    onClick={() => togglePasswordVisibility("newPassword")}
                    className="absolute right-4 top-3 cursor-pointer text-gray-500"
                  >
                    {showPassword["newPassword"] ? <IoEyeOff /> : <IoEye />}
                  </button>
                </div>
              </div>
              <div className="mt-2">
                <label
                  htmlFor="confirmPassword"
                  className="block text-sm/6 font-medium text-gray-900"
                >
                  Confirm Password
                </label>
                <div className="relative mt-1">
                  <input
                    type={showPassword["confirm"] ? "text" : "password"}
                    required
                    autoComplete="confirmPassword"
                    placeholder="Confirm Password"
                    onChange={(e) => setConfirm(e.target.value)}
                    className="focus:border-primary-600 block w-full rounded-md border border-gray-300 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 sm:text-sm/6"
                  />
                  <button
                    type="button"
                    onClick={() => togglePasswordVisibility("confirm")}
                    className="absolute right-4 top-3 cursor-pointer text-gray-500"
                  >
                    {showPassword["confirm"] ? <IoEyeOff /> : <IoEye />}
                  </button>
                </div>
              </div>
            </div>
            <div className="py-1">
              <ul>
                <li className="pl-3 text-sm text-gray-500">
                  * Password must be more than 7 characters
                </li>
              </ul>
            </div>
            <div className="flex w-full justify-center">
              <button
                type="submit"
                className={clsx(
                  "w-1/3 rounded-lg bg-blue-700 px-5 py-2 text-base font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300",
                  loading &&
                    "flex cursor-not-allowed bg-gray-600 hover:bg-gray-600",
                )}
                disabled={loading ? true : false}
              >
                {loading && (
                  <AiOutlineLoading3Quarters className="mr-3 size-5 animate-spin" />
                )}
                Submit
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
