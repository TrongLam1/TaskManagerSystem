import {
  Listbox,
  ListboxButton,
  ListboxOption,
  ListboxOptions,
  Transition,
} from "@headlessui/react";
import { Fragment, useEffect, useState } from "react";
import { FaCheck, FaChevronDown } from "react-icons/fa";

interface Item {
  id: string;
  name: string;
  value: string;
}

export default function ListBox(props: unknown) {
  const { label, list, selected, setSelected } = props;

  return (
    <>
      {label && (
        <p className="mb-2 block text-base font-medium text-gray-900">
          {label}
        </p>
      )}
      <Listbox value={selected} onChange={setSelected}>
        <div className="relative mt-1">
          <ListboxButton className="relative w-full cursor-default rounded border border-gray-300 bg-gray-50 px-3 py-2.5 pl-3 pr-10 text-left sm:text-sm 2xl:py-3">
            <span className="block truncate">
              {selected instanceof Set
                ? `Selected (${selected.size})`
                : selected.name}
            </span>
            <span className="pointer-events-none absolute inset-y-0 right-0 flex items-center pr-2">
              <FaChevronDown
                className="h-5 w-5 scale-75 text-gray-400"
                aria-hidden="true"
              />
            </span>
          </ListboxButton>
          <Transition
            as={Fragment}
            leave="transition ease-in duration-100"
            leaveFrom="opacity-100"
            leaveTo="opacity-0"
          >
            <ListboxOptions className="focus:ring-primary-500 focus:border-primary-500 absolute z-10 block w-full overflow-hidden rounded-lg border border-gray-300 bg-gray-50 text-base text-gray-900">
              {list.map((item: Item) => {
                return (
                  <ListboxOption
                    key={item.id}
                    value={item}
                    className={`relative cursor-pointer select-none py-1 pl-10 pr-4 hover:bg-amber-100 ${
                      (selected instanceof Set && selected.has(item)) ||
                      selected.name === item.name
                        ? "bg-amber-100 text-amber-900"
                        : "text-gray-900"
                    }`}
                  >
                    <div className="block truncate">{item.name}</div>
                    {((selected instanceof Set && selected.has(item)) ||
                      selected.name === item.name) && (
                      <span className="absolute inset-y-0 left-0 flex scale-75 items-center pl-3 text-amber-600">
                        <FaCheck />
                      </span>
                    )}
                  </ListboxOption>
                );
              })}
            </ListboxOptions>
          </Transition>
        </div>
      </Listbox>
    </>
  );
}
