"use client";

import { ResetPassword } from "@/app/api/userApi";
import { validateEmail } from "@/utils";
import clsx from "clsx";
import { useState } from "react";
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import { IoMdClose } from "react-icons/io";
import { toast } from "react-toastify";

export default function ResetPasswordModal(props: unknown) {
  const { close } = props;

  const [email, setEmail] = useState<string>();
  const [loading, setLoading] = useState<boolean>(false);

  const handleResetPassword = async (e: { preventDefault: () => void }) => {
    e.preventDefault();

    setLoading(true);

    if (!validateEmail(email!)) {
      toast.error("Email is not validation.");
    } else {
      const res = await ResetPassword(email!);
      if (res.status === 200) {
        toast.success("Check email to get new password.");
      } else {
        toast.error(res.message);
      }
    }

    setLoading(false);
  };

  return (
    <div className="fixed bottom-0 left-0 right-0 top-0 z-10 flex h-full max-h-full w-full items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-70 md:inset-0">
      <div className="relative max-h-full w-full max-w-lg p-4">
        <div className="relative z-10 rounded-lg bg-white shadow-sm">
          <div className="flex items-center justify-between rounded-t border-b border-gray-200 p-4 md:p-5">
            <h3 className="text-2xl font-semibold text-gray-900">
              Reset password
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
          <form onSubmit={handleResetPassword} className="p-4 md:p-5">
            <div className="mb-4">
              <div>
                <label
                  htmlFor="email"
                  className="block text-sm/6 font-medium text-gray-900"
                >
                  Email
                </label>
                <div className="relative mt-1">
                  <input
                    type="text"
                    required
                    autoComplete="email"
                    placeholder="Email"
                    onChange={(e) => setEmail(e.target.value)}
                    className="focus:border-primary-600 block w-full rounded-md border border-gray-300 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 sm:text-sm/6"
                  />
                </div>
              </div>
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
                Send
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
