import {fetchLeaderboards, useLeaderboards} from "../hooks/useLeaderboards";
import {GameModeType} from "../enums/game-mode-type";
import {useState} from "react";
import {Duration} from "ts-duration";
import Image from "next/image";
import LeaderboardRecordComponent from "../components/leaderboard-record-component";
import LeaderboardsTable from "../components/leaderboards-table";

const focusedStyles = "z-10 ring-2 ring-yellow-500 bg-yellow-700";


export default function Leaderboards() {
    const [gameMode, setGameMode] = useState(GameModeType.ALL);

    return (
        <div className={"w-full h-full text-center"}>

            <h1 className={"text-4xl text-left mx-8 mt-8"}>Leaderboards</h1>

            <div className={"flex flex-row justify-left rounded-md shadow-sm mx-16 mt-16"} role="group">
                <button type="button"
                        onClick={() => setGameMode(GameModeType.ALL)}
                        className={`py-2 px-4 text-sm font-medium rounded-l-lg border border-white text-white hover:text-white hover:bg-yellow-700 
                        ${gameMode != null && GameModeType.ALL == gameMode ? focusedStyles : ""}`}>
                    All Places
                </button>
                <button type="button"
                        onClick={() => setGameMode(GameModeType.COFFEE)}
                        className={`py-2 px-4 text-sm font-medium border-t border-b border-white text-white hover:text-white hover:bg-yellow-700 
                        ${gameMode != null && GameModeType.COFFEE == gameMode ? focusedStyles : ""}`}>
                    Coffee Shops
                </button>
                <button type="button"
                        onClick={() => setGameMode(GameModeType.LANDMARKS)}
                        className={`py-2 px-4 text-sm font-medium rounded-r-md border border-white text-white hover:text-white hover:bg-yellow-700 
                        ${gameMode != null && GameModeType.LANDMARKS == gameMode ? focusedStyles : ""}`}>
                    Landmarks
                </button>
            </div>

            <LeaderboardsTable gameMode={gameMode}/>
        </div>
    );
}


// export const getStaticProps = async () => {
//     const queryClient = new QueryClient();
//
//     await queryClient.prefetchQuery({
//         queryKey: [QueryType.LEADERBOARD_RECORDS, GameModeType.ALL],
//         queryFn: () => fetchLeaderboards(GameModeType.ALL)
//     });
//
//     return {
//         props: {
//             dehydratedState: dehydrate(queryClient),
//         }
//     };
// }

