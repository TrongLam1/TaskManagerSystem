"use client";

import ListBox from "@/components/listBox/listBox";
import { Field, Label } from "@headlessui/react";
import { useState } from "react";
import { IoMdClose } from "react-icons/io";

const STAGE_LABEL = "Task Stage";
const PRIORITY_LABEL = "Priority Level";
const ASSIGN_LABEL = "Assign Task To:";

const STAGE_LIST = [
  {
    id: "stage-1",
    name: "TO DO",
    value: "todo",
  },
  {
    id: "stage-2",
    name: "IN PROGRESS",
    value: "inprogress",
  },
  {
    id: "stage-3",
    name: "COMPLETED",
    value: "completed",
  },
];
const PRIORITY_LIST = [
  {
    id: "priority-1",
    name: "HIGH",
    value: "high",
  },
  {
    id: "priority-2",
    name: "MEDIUM",
    value: "medium",
  },
  {
    id: "priority-3",
    name: "NORMAL",
    value: "normal",
  },
];

export default function TaskModal(props: unknown) {
  const { close, taskData } = props;

  const [title, setTitle] = useState();
  const [assign, setAssign] = useState(new Set());
  const [stage, setStage] = useState(STAGE_LIST[0]);
  const [deadline, setDeadline] = useState();
  const [priority, setPriority] = useState(PRIORITY_LIST[2]);

  const task = taskData ?? null;
  const header = task ? "Update Task" : "Create Task";

  const handleAddAssign = (member) => {
    setAssign((prev) => {
      const newSet = new Set(prev);
      if (newSet.has(member)) {
        newSet.delete(member);
      } else {
        newSet.add(member);
      }
      return new Set(newSet);
    });
  };

  return (
    <div className="fixed bottom-0 left-0 right-0 top-0 flex h-full max-h-full w-full items-center justify-center overflow-y-auto overflow-x-hidden bg-black bg-opacity-70 md:inset-0">
      <div className="relative max-h-full w-full max-w-lg p-4">
        <div className="relative z-10 rounded-lg bg-white shadow-sm">
          <div className="flex items-center justify-between rounded-t border-b border-gray-200 p-4 md:p-5">
            <h3 className="text-2xl font-semibold text-gray-900">{header}</h3>
            <button
              type="button"
              className="ms-auto inline-flex h-8 w-8 items-center justify-center rounded-lg bg-transparent text-sm text-gray-400 hover:bg-gray-200 hover:text-gray-900"
              data-modal-toggle="crud-modal"
              onClick={close}
            >
              <IoMdClose className="text-xl" />
              <span className="sr-only">Close modal</span>
            </button>
          </div>
          <form className="p-4 md:p-5">
            <div className="mb-4 grid grid-cols-2 gap-4">
              <div className="col-span-2">
                <label
                  htmlFor="title"
                  className="mb-2 block text-base font-medium text-gray-900"
                >
                  Task Title
                </label>
                <input
                  type="text"
                  name="title"
                  className="focus:ring-primary-600 focus:border-primary-600 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-base text-gray-900"
                  placeholder="Task Title"
                  required={true}
                />
              </div>
              <div className="col-span-2">
                <ListBox
                  label={ASSIGN_LABEL}
                  list={STAGE_LIST}
                  selected={assign}
                  setSelected={handleAddAssign}
                />
              </div>
              <div className="col-span-2 sm:col-span-1">
                <ListBox
                  label={STAGE_LABEL}
                  list={STAGE_LIST}
                  selected={stage}
                  setSelected={setStage}
                />
              </div>
              <Field className="col-span-2 sm:col-span-1">
                <Label className="mb-2 block text-base font-medium text-gray-900">
                  Deadline
                </Label>
                <input
                  type="date"
                  name="deadline"
                  className="focus:ring-primary-600 focus:border-primary-600 dark:focus:ring-primary-500 dark:focus:border-primary-500 block w-full rounded-lg border border-gray-300 bg-gray-50 p-2.5 text-base text-gray-900"
                  required={true}
                />
              </Field>
              <div className="col-span-2 sm:col-span-1">
                <ListBox
                  label={PRIORITY_LABEL}
                  list={PRIORITY_LIST}
                  selected={priority}
                  setSelected={setPriority}
                />
              </div>
            </div>
            <div className="flex w-full justify-end">
              <button
                className="mr-2 inline-flex items-center rounded-lg bg-red-700 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-red-800 focus:outline-none focus:ring-4 focus:ring-red-300"
                onClick={close}
              >
                Cancel
              </button>
              <button
                type="submit"
                className="inline-flex items-center rounded-lg bg-blue-700 px-5 py-2.5 text-center text-sm font-medium text-white hover:bg-blue-800 focus:outline-none focus:ring-4 focus:ring-blue-300"
              >
                Submit
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
