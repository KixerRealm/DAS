import Link from "next/link";
import {useAtom} from "jotai";
import {openAtom} from "./user-nav-bar";

export default function Footer() {
    const [_1, setOpen] = useAtom(openAtom);

    return (
        <footer onClick={() => setOpen(false)}>
            <hr className={"my-6 border-neutral-200 sm:mx-auto dark:border-neutral-700 lg:my-8"}/>
            <span className={"block text-sm text-neutral-500 sm:text-center dark:text-neutral-400 pb-8"}>
                © 2022{' '}
                <Link href={'/'}>
                     <span className={"hover:underline underline-offset-2"}>FAK Studios™</span>.{' '}
                </Link>
                All Rights Reserved.
            </span>
        </footer>
    );
}
