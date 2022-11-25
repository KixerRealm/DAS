import {useAtom} from "jotai";
import {ArrowRightOnRectangleIcon} from "@heroicons/react/24/solid";
import Link from "next/link";
import {atomWithStorage} from "jotai/utils";
import {User} from "../pages/api/oauth/login";
import {useHasMounted} from "../hooks/useHasMounted";

export const userAtom = atomWithStorage<User | null>('user', null);

export default function UserNavBar() {
    const [user, _] = useAtom(userAtom);
    const hasMounted = useHasMounted();

    if(!hasMounted) {
        return null;
    }

    if (!user) {
        return (
            <Link href={'/login'}>
                <button
                    className={"inline-flex items-center rounded mx-2 bg-cyan-600 hover:bg-cyan-700 py-2 px-4"}>
                    Log in
                    <ArrowRightOnRectangleIcon className={"ml-1 h-6 fill-cyan-100"}/>
                </button>
            </Link>
        );
    } else {
        return (
            <h1>Hello, {user.displayName}</h1>
        )
    }
}

