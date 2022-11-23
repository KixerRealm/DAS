
import NavItem from "./nav-item";


export default function Navbar() {

    return (
        <nav className="flex flex-row w-full bg-neutral-900 border-4 border-neutral-900 border-b-neutral-800">
            <ul className={"flex list-none pl-8 py-2 basis-5/6 text-slate"}>
                <NavItem href={'/'} text={"Home"}/>
                <NavItem href={'/leaderboards'} text={"Leaderboards"}/>
                <NavItem href={'/about'} text={"About"}/>
            </ul>
            <ul className={'absolute right-0 pr-8 p-2 basis-1/6 text-slate'}>
                <NavItem href={'/login'} text={"Login"}/>
            </ul>
        </nav>
    );
}


