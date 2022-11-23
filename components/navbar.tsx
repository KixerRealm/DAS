
import NavItem from "./nav-item";
import {ArrowRightOnRectangleIcon} from "@heroicons/react/24/solid";


export default function Navbar() {

    return (
        <nav className="flex flex-row w-full bg-neutral-900 border-4 border-neutral-900 border-b-neutral-800">
            <ul className={"flex list-none pl-8 py-1 basis-5/6 text-neutral"}>
                <NavItem href={'/'} text={"Home"}/>
                <NavItem href={'/leaderboards'} text={"Leaderboards"}/>
                <NavItem href={'/about'} text={"About"}/>
            </ul>
            <ul className={'absolute right-0 top-4 pr-4 basis-1/6 text-neutral'}>
                <button className={"inline-flex items-center rounded mx-2 bg-cyan-600 hover:bg-cyan-700 py-2 px-4"}>
                    Log in
                    <ArrowRightOnRectangleIcon className={"ml-1 h-6 fill-cyan-100"}/>
                </button>
            </ul>
        </nav>
    );
}


