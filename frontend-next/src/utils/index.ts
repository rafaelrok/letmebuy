/* eslint-disable react-hooks/rules-of-hooks */
import { useRouter } from "next/router";

export function returnSelect(route: string) {
  const router = useRouter();
  const { pathname } = router;

  let result;
  if (pathname === route) result = "active";

  return result;
}

export function returnActiveItem(route: string) {
  const router = useRouter();
  const { asPath } = router;
  let result;
  if (asPath === route) result = "active-sidebar-item";

  return result;
}

export const isBrowser = () => typeof window !== "undefined";
