"use client";

export default function TabView(props: unknown) {
  const { tab, selected, setSelected } = props;

  return (
    <button
      type="button"
      className={`flex w-fit items-center gap-2 bg-white px-3 py-2.5 text-base font-medium leading-5 outline-none ${selected === tab.number ? "border-b-2 border-blue-600 text-blue-700" : "text-gray-800 hover:text-blue-800"}`}
      onClick={() => setSelected(tab.number)}
    >
      {tab.icon}
      <span>{tab.name}</span>
    </button>
  );
}
