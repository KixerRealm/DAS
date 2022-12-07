import {useLeaderboards} from "../hooks/useLeaderboards";
import {GameModeType} from "../enums/game-mode-type";
import LeaderboardRecordComponent from "./leaderboard-record-component";

const translations = {
    ALL: "All Places",
    COFFEE: "Coffee Shops",
    LANDMARKS: "Landmarks"
}

interface LeaderboardsTableParameters {
    gameMode: GameModeType;
}

export default function LeaderboardsTable(params: LeaderboardsTableParameters) {
    const {isLoading, error, data} = useLeaderboards(params.gameMode);
    if (error != null && error instanceof Error) return <h1
        className={"text-red-500 text-lg text-left mx-16 my-4"}>{error.message}</h1>;

    if (isLoading) return <h1>Loading...</h1>;

    return (
        <div className={'flex flex-row justify-left my-8'}>
            <div className={"overflow-x-auto relative shadow-md sm:rounded-lg w-full mx-16 px-6"}>
                <table
                    className={"w-full text-sm text-left text-gray-400 border-l border-r border-neutral-800"}>
                    <caption
                        className={"p-5 text-lg font-semibold text-left text-white bg-neutral-800"}>
                        <h1 className={"text-2xl"}>{translations[params.gameMode]} (Top {data?.length})</h1>
                    </caption>
                    <thead className={"text-xs uppercase bg-gray-700 text-gray-400"}>
                    <tr>
                        <th scope="col" className={"py-3 px-6"}>
                            #
                        </th>
                        <th scope="col" className={"py-3 px-6"}>
                            Display Name
                        </th>
                        <th scope="col" className={"py-3 px-6"}>
                            Started At
                        </th>
                        <th scope="col" className={"py-3 px-6"}>
                            Time Taken
                        </th>
                        <th scope="col" className={"py-3 px-6"}>
                            Total Points
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    {data?.map((record, idx) => (
                        <LeaderboardRecordComponent key={idx} idx={idx + 1} record={record}/>
                    ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}
