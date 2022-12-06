import '../styles/globals.css'
import type {AppProps} from 'next/app'
import Layout from "../components/layout";
import React, {useRef} from "react";
import {QueryClientProvider, QueryClient, Hydrate} from "@tanstack/react-query";
import { ReactQueryDevtools } from '@tanstack/react-query-devtools';

export default function App({Component, pageProps}: AppProps) {
    const queryClient = useRef(new QueryClient());

    return (
        <QueryClientProvider client={queryClient.current}>
            <Hydrate state={pageProps.dehydratedState}>
                <Layout>
                    <Component {...pageProps} />
                </Layout>
                <ReactQueryDevtools initialIsOpen={false}/>
            </Hydrate>
        </QueryClientProvider>
    );
}
