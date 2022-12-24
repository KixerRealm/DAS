import {APIError} from "../../pages/api/leaderboards";
import {useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";
import {PeakPlacement} from "../../pages/api/profile/peak-placements";

async function peakPlacements(token: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/game/peak-placements`, {
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

        return (await res.json()) as PeakPlacement[];
    }).then((data: PeakPlacement[]) => (
        data.map(item => {
            item.endedAt = new Date(item.endedAt);
            return item;
        })
    ));
}


export default function usePeakPlacements(token: string) {
    return useQuery({
        queryKey: [QueryType.PEAK_PLACEMENTS, token],
        queryFn: () => {
            if (token == undefined)
                return null;
            return peakPlacements(token);
        },
        refetchOnWindowFocus: false
    });
}
