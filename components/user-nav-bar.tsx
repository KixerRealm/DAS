import {atom, useAtom} from "jotai";
import {ArrowRightOnRectangleIcon} from "@heroicons/react/24/solid";
import Link from "next/link";
import {atomWithStorage} from "jotai/utils";
import {User} from "../pages/api/oauth/login";
import {useHasMounted} from "../hooks/useHasMounted";
import {useCallback} from "react";
import DropdownIcon from "./icons/dropdown-icon";
import Identicon from "./icons/identicon";

export const userAtom = atomWithStorage<User | null>('user', null);
export const openAtom = atom(false);

export default function UserNavBar() {
    const hasMounted = useHasMounted();
    const [user, setUser] = useAtom(userAtom);
    const [open, setOpen] = useAtom(openAtom);

    const handleLogout = useCallback(async () => {
        setUser(null);
        setOpen(false);
    }, [setUser]);

    if (!hasMounted) {
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
            <div className={'w-full'}>
                <button onClick={() => setOpen(!open)}
                        className={"flex items-center text-sm font-medium rounded-lg hover:text-blue-500 md:mr-0 focus:ring-4 focus:ring-neutral-700 text-white px-12 w-full"}
                        type={"button"}>
                    {/*<Image className={"mr-2 rounded-full my-2"} src={user?.profilePictureUrl ?? ''}*/}
                    {/*       alt={"user photo"} width={30} height={30}/>*/}
                    <Identicon value={user?.username ?? ''} size={'30'}/>
                    <p className={'text-right w-full'}>{user.username}</p>
                    <DropdownIcon/>
                </button>

                <div
                    className={`${open ? 'block' : 'hidden'} z-10 rounded divide-y shadow bg-neutral-800 divide-neutral-600`}>
                    <div className={"py-3 px-4 text-sm text-white"}>
                        <div className={"font-medium "}>{user.username}</div>
                        <div className={"truncate"}>{user.email}</div>
                    </div>
                    <ul className={"py-1 text-sm text-neutral-200"} onClick={() => setOpen(false)}>
                        <li>
                            <Link href={'/profile'}>
                                <span
                                    className={"block py-2 px-4 hover:bg-neutral-600 hover:text-white"}>Profile</span>
                            </Link>
                        </li>
                        {/*TODO: Implement Settings page*/}
                        {/*<li>*/}
                        {/*    <Link href={"#"}>*/}
                        {/*        <span*/}
                        {/*            className={"block py-2 px-4 hover:bg-neutral-600 hover:text-white"}>Settings</span>*/}
                        {/*    </Link>*/}
                        {/*</li>*/}
                    </ul>
                    <button onMouseDown={handleLogout} className={'w-full text-left py-1'}>
                        <span
                            className={"block py-2 px-4 text-sm hover:bg-neutral-600 text-neutral-200 hover:text-white"}>
                            Log out
                        </span>
                    </button>
                </div>

            </div>
        )
    }
}
