import {Guess} from "../../pages/game";
import {APIError} from "../../pages/api/leaderboards";
import {useMutation} from "@tanstack/react-query";
import {useEffect} from "react";
import {GameSubmissionRequest} from "../../pages/api/game/submit";


export async function submitGame(id: string, email: string, guesses: Guess[], points: number) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/game/submit`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({id, email, guesses, points})
    }).then(async res => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }
    });
}

export function useSubmitGame(onSuccess: (data: any) => void) {
    return useMutation({
        mutationFn: (params: GameSubmissionRequest) => {
            return submitGame(params.id, params.email, params.guesses, params.points);
        },
        onSuccess
    });
}
