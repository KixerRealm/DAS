import {fetchLeaderboards, useLeaderboards} from "../hooks/useLeaderboards";
import {GameModeType} from "../enums/game-mode-type";
import {useState} from "react";
import {Duration} from "ts-duration";
import Image from "next/image";

const focusedStyles = "z-10 ring-2 ring-yellow-500 bg-yellow-700";

const translations = {
    ALL: "All Places",
    COFFEE: "Coffee Shops",
    LANDMARKS: "Landmarks"
}

export default function Leaderboards() {
    const [gameMode, setGameMode] = useState(GameModeType.ALL);

    const {isLoading, error, data} = useLeaderboards(gameMode);

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
            {error != null && error instanceof Error ?
                <h1 className={"text-red-500 text-lg text-left mx-16 my-4"}>{error.message}</h1> : <></>}

            {isLoading ? <h1>Loading...</h1> :
                <div className={'flex flex-row justify-left my-8'}>
                    <div className={"overflow-x-auto relative shadow-md sm:rounded-lg w-full mx-16 px-6"}>
                        <table
                            className={"w-full text-sm text-left text-gray-400 border-l border-r border-neutral-800"}>
                            <caption
                                className={"p-5 text-lg font-semibold text-left text-white bg-neutral-800"}>
                                <h1 className={"text-2xl"}>{translations[gameMode]} (Top 15)</h1>
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
                                <tr key={idx}
                                    className={`border-b bg-neutral-900 border-neutral-800`}>
                                    <th scope="row" className={"py-4 px-6 font-medium text-white"}>{idx + 1}</th>
                                    <td
                                        className={"py-4 px-6 font-medium whitespace-nowrap text-white"}>
                                        <div className={"flex items-center"}>
                                            <Image src={record.profilePictureUrl} alt={""} width={40} height={40}
                                            className={"rounded-full"}/>
                                            {/* TODO: Make a link to user's profile */}
                                            <p className={"ml-4"}>{record.username}</p>
                                        </div>
                                    </td>
                                    <td className={"py-4 px-6"}>
                                        {new Date(record.timeStarted).toLocaleDateString()}
                                    </td>
                                    <td className={"py-4 px-6"}>
                                        {Duration.millisecond(record.timeCompleted.valueOf() - record.timeStarted.valueOf()).minutes.toFixed(2)} minutes
                                    </td>
                                    <td className={"py-4 px-6"}>
                                        {record.total}
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>}


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

