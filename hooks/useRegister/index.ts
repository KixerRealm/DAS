import {useMutation} from "@tanstack/react-query";
import {APIError, LeaderboardRecord} from "../../pages/api/leaderboards";
import {User} from "../../pages/api/oauth/login";

export type UserRegistration = {
    email: string;
    password: string;
    displayName: string;
    confirm: string;
}

export async function executeRegister(data: UserRegistration) {
    if (data.password != data.confirm) {
        throw new Error("Passwords don't match!");
    }

    return await fetch("http://localhost:3000/api/oauth/register", {
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
        });
}
