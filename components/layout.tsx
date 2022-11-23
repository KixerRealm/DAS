import Footer from "./footer";
import Navbar from "./navbar";

interface LayoutParameters {
    children: JSX.Element|JSX.Element[];
}

export default function Layout(params: LayoutParameters) {
    return (
        <>
            <Navbar/>
            <main>{params.children}</main>
            <Footer/>
        </>
    )
}
