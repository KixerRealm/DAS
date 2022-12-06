import {GameModeType} from "../../enums/game-mode-type";
import {LeaderboardError, LeaderboardRecord} from "../../pages/api/leaderboards";
import {useQuery} from "@tanstack/react-query";
import {QueryType} from "../../enums/query-type";


export async function fetchLeaderboards(gameMode: GameModeType): Promise<LeaderboardRecord[]> {
    return await fetch("http://localhost:3000/api/leaderboards?" + new URLSearchParams({
        gameMode
    }))
        .then(async (data) => {
            if (data.status == 400) {
                const error = (await data.json()) as LeaderboardError;
                throw new Error(error.message);
            }

            return (await data.json()) as LeaderboardRecord[];
        })
        .then((data) => {

            return data.map<LeaderboardRecord>((item) => {
                item.timeStarted = new Date(item.timeStarted);
                item.timeCompleted = new Date(item.timeCompleted);
                return item;
            });
        });
}


export function useLeaderboards(gameMode: GameModeType) {
    return useQuery({
        queryKey: [QueryType.LEADERBOARD_RECORDS, gameMode],
        queryFn: () => fetchLeaderboards(gameMode),
        refetchOnWindowFocus: false
    });
}
