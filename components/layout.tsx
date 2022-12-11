import Footer from "./footer";
import Navbar from "./navbar";
import Head from "next/head";
import {useRouter} from "next/router";
import {useAtom} from "jotai";
import {inGameAtom} from "../pages/game";
import {openAtom} from "./user-nav-bar";

interface LayoutParameters {
    children: JSX.Element | JSX.Element[];
}

export default function Layout(params: LayoutParameters) {
    const router = useRouter();
    const [game, _] = useAtom(inGameAtom);
    const [_1, setOpen] = useAtom(openAtom);

    return (
        <>
            <Head>
                <title>Skopje GeoGuessr</title>
                <meta name="description" content="GeoGuessr clone meant only for places in Skopje."/>
                <link rel="icon" href={"/favicon.ico"}/>
            </Head>
            {!game ? <Navbar/> : <></>}
            <main onClick={() => setOpen(false)}>{params.children}</main>
            {!game && !['/login', '/register'].includes(router.route) ? <Footer/> : <></>}

        </>
    )
}
