import {APIError} from "../../pages/api/leaderboards";
import {CurrentPlacement} from "../../pages/api/profile/current-placements";
import {useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";

async function currentPlacements(token: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/game/current-placements`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        },
    }).then(async (res: any) => {
        if (res.status == 400) {
            const error = (await res.json()) as APIError;
            throw new Error(error.message);
        }

        return (await res.json()) as CurrentPlacement[];
    }).then((data: CurrentPlacement[]) => (
        data.map(item => {
            item.endedAt = new Date(item.endedAt);
            return item;
        })
    ));
}


export default function useCurrentPlacements(token: string) {
    return useQuery({
        queryKey: [QueryType.CURRENT_PLACEMENTS, token],
        queryFn: () => {
            if (token == undefined)
                return null;
            return currentPlacements(token);
        },
        refetchOnWindowFocus: false
    });
}
