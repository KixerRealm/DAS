import NavItem from "./nav-item";
import UserNavBar, {openAtom} from "./user-nav-bar";
import {useAtom} from "jotai";

export default function Navbar() {
    const [_1, setOpen] = useAtom(openAtom);

    return (
        <nav className={"flex flex-row w-full bg-neutral-900 border-4 border-neutral-900 border-b-neutral-800"}>
            <ul className={"flex list-none pl-8 py-1 basis-5/6 text-neutral"} onClick={() => setOpen(false)} >
                <NavItem href={'/'} text={"Home"}/>
                <NavItem href={'/leaderboards'} text={"Leaderboards"}/>
                <NavItem href={'/about'} text={"About Us"}/>
            </ul>
            <ul className={'absolute right-6 top-4 pr-4 basis-1/6 text-neutral'}>
                <UserNavBar/>
            </ul>
        </nav>
    );
}


