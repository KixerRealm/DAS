import {Guess} from "../../pages/game";
import {APIError} from "../../pages/api/leaderboards";
import {useMutation} from "@tanstack/react-query";
import {useEffect} from "react";
import {GameSubmissionRequest} from "../../pages/api/game/submit";

type GameCancelRequest = {
    id: string,
    email: string;
}

export async function cancelGame(id: string, email: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/game/cancel`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({id, email})
    }).then(async res => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }
    });
}

export function useCancelGame(onSuccess: (data: any) => void) {
    return useMutation({
        mutationFn: (params: GameCancelRequest) => {
            return cancelGame(params.id, params.email);
        },
        onSuccess
    });
}
