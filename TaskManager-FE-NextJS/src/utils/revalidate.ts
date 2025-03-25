"use server";

import { revalidatePath } from "next/cache";

export async function revalidateUser() {
  revalidatePath("/(main)/user/[page]", "layout");
}
