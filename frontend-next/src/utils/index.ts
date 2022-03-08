/* eslint-disable react-hooks/rules-of-hooks */
import { useRouter } from "next/router";

export function returnSelect(route: string) {
    const router = useRouter();
    const { pathname } = router;

    let result;
    if(pathname === route) {
        result = "active";
    }
    return result;
}