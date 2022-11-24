import styles from "../styles/Home.module.css";
import Link from "next/link";

export default function Footer() {
    return (
        <footer>
            <hr className={"my-6 border-neutral-200 sm:mx-auto dark:border-neutral-700 lg:my-8"}/>
            <span className={"block text-sm text-neutral-500 sm:text-center dark:text-neutral-400 pb-8"}>
                <Link href={'/'}>
                    © 2022 <a className={"hover:underline underline-offset-2"}>FAK Studios™</a>.{' '}
                </Link>
                All Rights Reserved.
            </span>
        </footer>
    );
}
