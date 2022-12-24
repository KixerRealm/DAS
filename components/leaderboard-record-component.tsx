import Image from "next/image";
import {Duration} from "ts-duration";
import {LeaderboardRecord} from "../pages/api/leaderboards";
import Identicon from "./icons/identicon";

interface LeaderboardRecordParameters {
    idx: number;
    record: LeaderboardRecord;
}

export default function LeaderboardRecordComponent(params: LeaderboardRecordParameters) {
    return (
        <tr
            className={`border-b bg-neutral-900 border-neutral-800`}>
            <th scope="row" className={"py-4 px-6 font-medium text-white"}>{params.idx}</th>
            <td
                className={"py-4 px-6 font-medium whitespace-nowrap text-white"}>
                <div className={"flex items-center"}>
                    {/*<Image src={params.record.profilePictureUrl} alt={""} width={40} height={40}*/}
                    {/*       className={"rounded-full"}/>*/}
                    <Identicon value={params.record.username} size={'40'}/>
                    {/* TODO: Make a link to user's profile */}
                    <p className={"ml-4"}>{params.record.username}</p>
                </div>
            </td>
            <td className={"py-4 px-6"}>
                {new Date(params.record.timeStarted).toLocaleDateString()}
            </td>
            <td className={"py-4 px-6"}>
                {Duration.millisecond(params.record.timeCompleted.valueOf() - params.record.timeStarted.valueOf()).minutes.toFixed(2)} minutes
            </td>
            <td className={"py-4 px-6"}>
                {params.record.totalPoints}
            </td>
        </tr>
    );
}
