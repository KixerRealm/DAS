import Link from "next/link";
import {useRouter} from "next/router";

interface NavItemParameters {
    href: string;
    text: string;
    custom?: JSX.Element;
}

export default function NavItem(params: NavItemParameters) {
    const router = useRouter();

    return (
        <>
            <li className={"text-xl p-4"}>
                <Link href={params.href}>
                    <p className={`group transition-all duration-1000 ease-in-out`}>
                        <span className={
                            router.route != params.href ?
                                'bg-left-bottom bg-gradient-to-r from-slate-400 to-slate-400 bg-[length:0%_2px] bg-no-repeat group-hover:bg-[length:100%_2px] transition-all duration-500 ease-out' :
                                'underline underline-offset-2 decoration-slate-100'
                        }>
                            {params.text}
                        </span>
                    </p>
                </Link>
            </li>
        </>
    )
}
