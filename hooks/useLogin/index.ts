import {APIError} from "../../pages/api/leaderboards";
import {User} from "../../pages/api/oauth/login";

export type UserCredentials = {
    username: string;
    password: string;
}

export class LoginRequest implements UserCredentials, Record<string, string> {
    [x: string]: string;
    username: string = '';
    password: string = '';
    grant_type: string = "password";
    client_id: string = "sko-client";
    client_secret: string = "Yn8JZT31j4cxQBRJQWUpP0hLMAI1shX7";
}

export async function executeLogin(data: LoginRequest) {
    return await fetch(`${process.env.NEXT_PUBLIC_KEYCLOAK_BASE}/realms/sko-realm/protocol/openid-connect/token`, {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams(data)
    })
        .then(async res => {
            console.log(res);
            if (res.status == 400) {
                console.log(res);
                const error = (await res.json()) as APIError;
                throw new Error(error.message);
            }
            const user = (await res.json()) as User;
            user.profilePictureUrl = user.profilePictureUrl ?? '';
            return user;
        });
}
