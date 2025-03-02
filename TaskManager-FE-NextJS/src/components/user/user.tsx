"use client";

export default function User(props: unknown) {
  const { deleteUser } = props;
  return (
    <>
      <tr className="border-b border-gray-200 text-gray-600 hover:bg-gray-400/10">
        <td className="flex justify-center py-2">
          <div className="flex items-center gap-3">
            <div className="flex h-9 w-9 items-center justify-center rounded-full bg-blue-700 text-sm text-white">
              <span className="text-center text-xs md:text-sm">TL</span>
            </div>
            Emily Wilson
          </div>
        </td>
        <td className="py-2 text-center">Data Analyst</td>
        <td className="py-2 text-center">user.emal.com</td>
        <td className="py-2 text-center">Analyst</td>
        <td className="text-center">
          <button className="w-fit rounded-full bg-blue-200 px-4 py-1">
            Active
          </button>
        </td>
        <td className="flex justify-center gap-4 p-2 text-center">
          <button className="px-3 py-2 font-semibold text-blue-600 outline-none hover:text-blue-500 sm:px-0">
            Edit
          </button>
          <button
            className="px-3 py-2 font-semibold text-red-700 outline-none hover:text-red-500 sm:px-0"
            onClick={() => deleteUser(1)}
          >
            Delete
          </button>
        </td>
      </tr>
    </>
  );
}
