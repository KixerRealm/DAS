import {useAtom} from "jotai";
import {userAtom} from "../components/user-nav-bar";
import Image from "next/image";
import {useHasMounted} from "../hooks/useHasMounted";
import {GameModeType} from "../enums/game-mode-type";
import {translations} from "../constants/enum-translations";
import {useRouter} from "next/router";
import useListAttempts from "../hooks/useListAttempts";
import useCurrentPlacements from "../hooks/useCurrentPlacements";
import usePeakPlacements from "../hooks/usePeakPlacements";

const peakPlacements = [
    {placement: 20, datePlayed: new Date(), gameMode: GameModeType.ALL},
    {placement: 10, datePlayed: new Date(), gameMode: GameModeType.COFFEE},
    {placement: 16, datePlayed: new Date(), gameMode: GameModeType.LANDMARKS},
];

export default function Profile() {
    const [user, _] = useAtom(userAtom);
    const hasMounted = useHasMounted();
    const router = useRouter();
    const {data} = useListAttempts(user?.email ?? '');

    const currentPlacementsQuery = useCurrentPlacements(user?.email ?? '');
    const currentPlacements = currentPlacementsQuery.data;

    const peakPlacementsQuery = usePeakPlacements(user?.email ?? '');
    const peakPlacements = peakPlacementsQuery.data;

    if (!hasMounted) {
        return null;
    }

    if (user == null) {
        router.push("/");
        return null;
    }

    if (data == null || currentPlacements == null || peakPlacements == null) {
        return null;
    }

    return (
        <div className={'w-full h-full flex flex-row'}>
            <div className={'min-h-screen basis-1/6'}>
                <div className={'h-full bg-zinc-800 pt-10 border-r-2 border-zinc-700'}>
                    <div className={'grid place-items-center'}>
                        <Image
                            className={"object-cover rounded-full border border-neutral-600 shadow drop-shadow mx-12"}
                            src={user.profilePictureUrl} alt=""
                            width={320} height={320}
                        />
                        <hr className={"w-5/6 my-6 sm:mx-auto border-zinc-700 lg:my-8"}/>
                        <div className={"grid place-items-center"}>
                            <h2 className={"font-bold text-3xl"}>{user.displayName}</h2>
                            <h3 className={"font-thin text-neutral-400"}>{user.email}</h3>
                        </div>
                        <hr className={"w-5/6 my-6 sm:mx-auto border-zinc-700 lg:my-8"}/>

                        <div className={'w-full pl-10'}>
                            <h1 className={'text-2xl font-semibold'}>Peak placements</h1>
                            <ul className={'list-disc pl-6 pt-4'}>
                                {peakPlacements.map((placement, idx) => (
                                    <li key={idx}>{translations[placement.gameMode]}:
                                        #{placement.placement} ({placement.datePlayed.toLocaleDateString()})</li>
                                ))}
                            </ul>
                        </div>

                        <div className={'w-full pl-10 mt-6'}>
                            <h1 className={'text-2xl font-semibold'}>Current placements</h1>
                            <ul className={'list-disc pl-6 pt-4'}>
                                {currentPlacements.map((placement, idx) => (
                                    <li key={idx}>{translations[placement.gameMode]}:
                                        #{placement.placement} ({placement.datePlayed.toLocaleDateString()})</li>
                                ))}
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div className={'h-full basis-5/6'}>
                <div className={'min-h-screen pt-16 ml-16'}>
                    <span className={"text-4xl font-bold"}>History</span>
                    <div className={"ml-4"}>
                        {data.map((lst, didx) => (
                            <div key={(didx + 1) * 100} className={`flex flex-row my-12`}>
                                {lst.map((item, idx) => (
                                    item == null ? <div key={idx * (didx + 1)} className={'basis-1/6 mx-4'}/> :
                                        <div key={idx * (didx + 1)}
                                             className={"basis-1/6 mx-4 flex flex-col border rounded-lg shadow-md" +
                                             " border-neutral-700 bg-neutral-800 hover:bg-neutral-700 hover:shadow-xl"}>
                                            <div className={"flex flex-col justify-between p-6 leading-normal"}>
                                                <h5 className={
                                                    "mb-2 text-2xl font-bold tracking-tight text-neutral-900 dark:text-white"
                                                }>{translations[item.gameMode]}</h5>
                                                <p className={"font-normal text-neutral-300 mb-2 text-sm"}>
                                                    Total points: {item.totalPoints} p.
                                                </p>
                                                <p className={"font-normal text-neutral-200"}>
                                                    Placed: #{item.placement}
                                                </p>
                                                <p className={"font-normal text-neutral-400"}>
                                                    Date played: {item.datePlayed.toLocaleDateString()}
                                                </p>
                                                <p className={"font-normal mt-2 text-neutral-400"}>
                                                    Time taken:<br/>
                                                    {Math.floor(item.timeTaken)} minutes {Math.round((item.timeTaken - Math.floor(item.timeTaken)) * 60)} seconds
                                                </p>
                                            </div>
                                        </div>
                                ))}
                            </div>
                        ))}
                    </div>
                </div>
            </div>
        </div>
    );
}
