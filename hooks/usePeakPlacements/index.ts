import {APIError} from "../../pages/api/leaderboards";
import {useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";
import {PeakPlacement} from "../../pages/api/profile/peak-placements";

async function peakPlacements(email: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/profile/peak-placements`, {
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

        return (await res.json()) as PeakPlacement[];
    }).then((data: PeakPlacement[]) => (
        data.map(item => {
            item.datePlayed = new Date(item.datePlayed);
            return item;
        })
    ));
}


export default function usePeakPlacements(email: string) {
    return useQuery({
        queryKey: [QueryType.CURRENT_PLACEMENTS, email],
        queryFn: () => {
            if (email == undefined)
                return null;
            return peakPlacements(email);
        },
        refetchOnWindowFocus: false
    });
}
