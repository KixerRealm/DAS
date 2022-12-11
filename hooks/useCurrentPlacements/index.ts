import {APIError} from "../../pages/api/leaderboards";
import {CurrentPlacement} from "../../pages/api/profile/current-placements";
import {useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";

async function currentPlacements(email: string) {
    return await fetch(`${process.env.NEXT_PUBLIC_BE_BASE}/api/profile/current-placements`, {
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

        return (await res.json()) as CurrentPlacement[];
    }).then((data: CurrentPlacement[]) => (
        data.map(item => {
            item.datePlayed = new Date(item.datePlayed);
            return item;
        })
    ));
}


export default function useCurrentPlacements(email: string) {
    return useQuery({
        queryKey: [QueryType.CURRENT_PLACEMENTS, email],
        queryFn: () => {
            if (email == undefined)
                return null;
            return currentPlacements(email);
        },
        refetchOnWindowFocus: false
    });
}
