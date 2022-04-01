export default function hasAdminAccess(role) {
  return role === "ADMIN";
}
// eslint-disable-next-line no-unused-vars
export function hasTeacherAccess(role) {
  return hasAdminAccess(role) || role === "TEACHER";
}

// eslint-disable-next-line no-unused-vars
export function hasTAAccess(role) {
  return hasTeacherAccess(role) || role === "TA";
}
