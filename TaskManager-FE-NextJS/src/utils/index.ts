export function getInitials(fullName: string) {
  const names = fullName.split(" ");

  const selectedNames = names.length <= 2 ? names : names.slice(-2);

  const initials = selectedNames.map((name) => name[0].toUpperCase());

  const initialsStr = initials.join("");

  return initialsStr;
}

export function hasAdmin(session) {
  const roles = session?.user.user.roles;
  return (
    session?.user && Array.from(roles).some((role) => role.name === "ADMIN")
  );
}

export function validateEmail(email: string) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

export function timeFromNow(date: string | number | Date) {
  const from = new Date(date);
  const now = new Date();

  // Calculating the time difference
  // of two dates
  const Difference_In_Time = now.getTime() - from.getTime();

  // Calculating the no. of days between
  // two dates
  return Math.round(Difference_In_Time / (1000 * 3600 * 24));
}
