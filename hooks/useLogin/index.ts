import {useMutation} from "@tanstack/react-query";
import {APIError, LeaderboardRecord} from "../../pages/api/leaderboards";
import {User} from "../../pages/api/oauth/login";

export type UserCredentials = {
    email: string;
    password: string;
}

export async function executeLogin(data: UserCredentials) {
    return await fetch("http://localhost:3000/api/oauth/login", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(async res => {
            if (res.status == 400) {
                const error = (await res.json()) as APIError;
                throw new Error(error.message);
            }

            return (await res.json()) as User;
        });
}


export function useLogin(data: UserCredentials) {
    return useMutation(() => executeLogin(data));
}
