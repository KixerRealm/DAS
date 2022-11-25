import Footer from "./footer";
import Navbar from "./navbar";
import Head from "next/head";
import {useRouter} from "next/router";

interface LayoutParameters {
    children: JSX.Element|JSX.Element[];
}

export default function Layout(params: LayoutParameters) {
    const router = useRouter();

    return (
        <>
            <Head>
                <title>Skopje GeoGuessr</title>
                <meta name="description" content="GeoGuessr clone meant only for places in Skopje." />
                <link rel="icon" href="/favicon.ico" />
            </Head>
            <Navbar/>
            <main>{params.children}</main>
            {!['/login', '/register'].includes(router.route) ? <Footer/> : <></>}
        </>
    )
}
