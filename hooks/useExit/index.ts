import {useRouter} from "next/router";
import {useEffect} from "react";


export default function useExit(fn: () => void) {
    const router = useRouter();
    useEffect(() => {
        router.events.on('routeChangeStart', fn);
        return () => {
            router.events.off('routeChangeStart', fn);
        };
    }, []);
}
