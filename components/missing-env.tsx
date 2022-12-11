import React from "react";

export default function MissingEnv() {
    return (
        <div className={"w-screen text-center grid content-center h-screen"}>
            <div className={"p-10 text-3xl font-bold"}>
                <h1>Please enter your Google Maps API Key into the .env file :)</h1>
                <div className={"text-2xl font-semibold pt-8"}>
                    <h2>Also you get a special demo account...</h2>
                    <h3>Email: admin@admin.com</h3>
                    <h3>Password: test</h3>
                </div>
            </div>
        </div>
    );
}
