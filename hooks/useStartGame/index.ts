import {GameModeType} from "../../enums/game-mode-type";
import {APIError} from "../../pages/api/leaderboards";
import {GameAttempt} from "../../pages/api/game/start";
import {useMutation} from "@tanstack/react-query";
import {useEffect} from "react";

type StartGameParameters = {
    email: string;
    gameModeType: GameModeType;
};

export async function startGame(email: string, gameModeType: GameModeType) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/game/start`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({email, gameType: gameModeType}),
    }).then(async res => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }

        return (await res.json()) as GameAttempt;
    });
}

export function useStartGame(onSuccess: (data: any) => void) {
    return useMutation({
        mutationFn: (params: StartGameParameters) => {
            return startGame(params.email, params.gameModeType);
        },
        onSuccess
    });
}

export function useStartGameEffect(email: string, gameModeType: GameModeType, onSuccess: (data: any) => void) {
    const {mutate} = useStartGame(onSuccess);
    useEffect(() => {
        if (email == undefined || gameModeType == undefined)
            return;
        mutate({email, gameModeType});
    }, [email, gameModeType, mutate]);
}
