import {APIError} from "../../pages/api/leaderboards";
import getMinutesBetweenDates from "../../utilities/minutes-between-dates";
import {faker} from "@faker-js/faker";
import {GameModeType} from "../../enums/game-mode-type";
import {useMutation, useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";

type Attempt = {
    id: string;
    placement: number;
    gameType: GameModeType;
    endedAt: Date;
    totalPoints: number;
    minutes: number;
    seconds: number;
};

function sliceIntoChunks(arr: Attempt[], chunkSize: number) {
    const res = [];
    for (let i = 0; i < arr.length; i += chunkSize) {
        const chunk = arr.slice(i, i + chunkSize);
        res.push(chunk);
    }
    return res;
}

async function listAttempts(token: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/game/attempts`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }
    }).then(async (res: any) => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }

        return (await res.json()) as Attempt[];
    }).then(async (res: Attempt[]) => {
        res = res.map(item => {
            item.endedAt = new Date(item.endedAt);
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

export default function useListAttempts(token: string) {
    return useQuery({
        queryKey: [QueryType.LIST_ATTEMPTS, token],
        queryFn: () => {
            if (token == undefined)
                return null;
            return listAttempts(token);
        },
        refetchOnWindowFocus: false
    });
}

