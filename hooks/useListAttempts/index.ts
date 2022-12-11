import {APIError} from "../../pages/api/leaderboards";
import getMinutesBetweenDates from "../../utilities/minutes-between-dates";
import {faker} from "@faker-js/faker";
import {GameModeType} from "../../enums/game-mode-type";
import {useMutation, useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";

type Attempt = {
    timeTaken: number;
    id: string;
    placement: number;
    gameMode: GameModeType;
    datePlayed: Date;
    totalPoints: number;
};

function sliceIntoChunks(arr: Attempt[], chunkSize: number) {
    const res = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
        const chunk = arr.slice(i, i + chunkSize);
        res.push(chunk);
    }
    return res;
}

async function listAttempts(email: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/profile/attempts`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({email})
    }).then(async (res: any) => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }

        return (await res.json()) as Attempt[];
    }).then(async (res: Attempt[]) => {
        res = res.map(item => {
            item.datePlayed = new Date(item.datePlayed);
            return item;
        });

        const data = sliceIntoChunks(res, 6);
        const dummies = 6 - data[data.length - 1].length;
        for (let i = 0; i < dummies; i++) {
            // @ts-ignore
            data[data.length - 1].push(null);
        }
        return data;
    });
}

export default function useListAttempts(email: string) {
    return useQuery({
        queryKey: [QueryType.LIST_ATTEMPTS, email],
        queryFn: () => {
            if (email == undefined)
                return null;
            return listAttempts(email);
        },
        refetchOnWindowFocus: false
    });
}

