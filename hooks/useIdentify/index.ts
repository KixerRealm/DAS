import {User} from "../../pages/api/oauth/login";
import {useMutation} from "@tanstack/react-query";

async function identify(token: string) {
    return fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/users/identify`, {
        headers: {
            'Authorization': `Bearer ${token}`
        }
    }).then(async (res: Response) => {
        if (res.status == 400) {
            throw new Error("error");
        }

        return (await res.json()) as User;
    })
}

export function useIdentify(onSuccess: (data: any) => void) {
    return useMutation({
        mutationFn: (token: string) => {
            return identify(token);
        },
        onSuccess
    });
}
