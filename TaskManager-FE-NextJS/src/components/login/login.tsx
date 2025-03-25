"use client";

import logo from "@/assets/images/favicon.png";
import authenticate from "@/utils/actions";
import clsx from "clsx";
import { useSession } from "next-auth/react";
import Image from "next/image";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { AiOutlineLoading3Quarters } from "react-icons/ai";
import { toast } from "react-toastify";
import ResetPasswordModal from "../modal/changePassword/resetPassword";

export default function Login() {
  const router = useRouter();
  const { update } = useSession();

  const [email, setEmail] = useState<string>();
  const [password, setPassword] = useState<string>();
  const [loading, setLoading] = useState<boolean>(false);
  const [open, setOpen] = useState<boolean>(false);

  const handleLogin = async (e) => {
    e.preventDefault();

    setLoading(true);
    const res = await authenticate(email!, password!);

    if (res.error) {
      toast.error(res.error);
      setLoading(false);
    } else {
      await update();
      router.push("/dashboard");
    }
  };

  return (
    <>
      <div className="flex h-screen justify-center bg-[#f3f4f6] px-6 lg:px-8">
        <div className="flex w-full flex-col items-center justify-center bg-white px-4 md:w-1/3 md:p-1">
          <div className="form-container flex w-full flex-col gap-y-8 px-10 pb-14 pt-14 md:w-[400px]">
            <div className="sm:mx-auto sm:w-full sm:max-w-sm">
              <Image src={logo} alt="logo" className="mx-auto h-10 w-auto" />
              <h2 className="mt-10 text-center text-2xl/9 font-bold tracking-tight text-gray-900">
                Sign in to your account
              </h2>
              <div className="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                <form onSubmit={handleLogin} className="space-y-6">
                  <div>
                    <label
                      htmlFor="email"
                      className="block text-sm/6 font-medium text-gray-900"
                    >
                      Email
                    </label>
                    <div className="mt-2">
                      <input
                        type="email"
                        required
                        autoComplete="email"
                        placeholder="Email"
                        onChange={(e) => setEmail(e.target.value)}
                        className="focus:border-primary-600 block w-full rounded-md border border-gray-300 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 sm:text-sm/6"
                      />
                    </div>
                  </div>

                  <div>
                    <div className="flex items-center justify-between">
                      <label
                        htmlFor="password"
                        className="block text-sm/6 font-medium text-gray-900"
                      >
                        Password
                      </label>
                      <div className="text-sm">
                        <div
                          className="cursor-pointer font-semibold text-indigo-600 hover:text-indigo-500"
                          onClick={() => setOpen(true)}
                        >
                          Forgot password?
                        </div>
                      </div>
                    </div>
                    <div className="mt-2">
                      <input
                        name="password"
                        type="password"
                        required
                        autoComplete="current-password"
                        placeholder="Password"
                        onChange={(e) => setPassword(e.target.value)}
                        className="focus:border-primary-600 block w-full rounded-md border border-gray-300 bg-white px-3 py-1.5 text-base text-gray-900 outline-1 -outline-offset-1 outline-gray-300 placeholder:text-gray-400 sm:text-sm/6"
                      />
                    </div>
                  </div>

                  <div>
                    <button
                      type="submit"
                      className={clsx(
                        "shadow-xs flex w-full justify-center rounded-md bg-indigo-600 px-3 py-1.5 text-sm/6 font-semibold text-white outline-none hover:bg-indigo-500",
                        loading &&
                          "cursor-not-allowed bg-gray-600 text-red-700 hover:bg-gray-500",
                      )}
                      disabled={loading ? true : false}
                    >
                      {loading && (
                        <AiOutlineLoading3Quarters className="mr-3 size-5 animate-spin" />
                      )}
                      Sign in
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
      {open && <ResetPasswordModal close={setOpen} />}
    </>
  );
}
