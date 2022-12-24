import {GameModeType} from "../../enums/game-mode-type";
import {APIError} from "../../pages/api/leaderboards";
import {ProposedGuess} from "../../pages/api/game/next-guess";
import {useMutation} from "@tanstack/react-query";


export async function nextGuess(gameModeType: GameModeType) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/places/next-guess`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({gameType: gameModeType})
    }).then(async res => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }

        return (await res.json()) as ProposedGuess;
    });
}

export function useNextGuess(onSuccess: (data: any) => void) {
    return useMutation({
        mutationFn: (gameModeType: GameModeType) => {
            return nextGuess(gameModeType);
        },
        onSuccess
    });
}
