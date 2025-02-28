import Navbar from "@/components/navbar/navbar";
import Image from "next/image";
import {
  MdKeyboardArrowDown,
  MdKeyboardArrowUp,
  MdKeyboardDoubleArrowUp,
} from "react-icons/md";

export default function Home() {
  const ICONS = {
    high: <MdKeyboardDoubleArrowUp />,
    medium: <MdKeyboardArrowUp />,
    low: <MdKeyboardArrowDown />,
  };

  return (
    <div className="flex h-screen w-full bg-[#f3f4f6] text-black">
      <div className="sidebar w-1/5 p-5">sidebar</div>
      <div className="main w-full">
        <Navbar />
      </div>
    </div>
  );
}
