import {User} from "../../pages/api/oauth/login";
import {useMutation} from "@tanstack/react-query";

async function identify(token: string) {
    return fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/users/identify`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }).then(async (res: Response) => {
        if (res.status != 200) {
            throw new Error((await res.text()));
        }

        return (await res.json()) as User;
    })
}

export function useIdentify(onSuccess: (data: any) => void, onError: (data: Error) => void) {
    return useMutation({
        mutationFn: (token: string) => {
            return identify(token);
        },
        onSuccess,
        onError
    });
}
