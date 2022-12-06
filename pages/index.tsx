import {StarIcon, UserPlusIcon} from "@heroicons/react/24/solid";
import GameModeCardRow from "../components/game-mode-card-row";
import Link from "next/link";
import {useAtom} from "jotai";
import {userAtom} from "../components/user-nav-bar";
import {useHasMounted} from "../hooks/useHasMounted";

export default function Home() {

    const gameModes = [
        [
            {
                title: 'All Places', description: 'This includes the entire data set that we currently have,' +
                    ' regardless of type (aka,coffee shops, landmarks, etc.).', img: "/thumbnails/skopje.jpg"
            },
            {
                title: 'Coffee Shops',
                description: 'This only includes coffee shops that we have gathered in our data set.',
                img: "/thumbnails/pub18.jpg"
            },
            {
                title: 'Landmarks',
                description: 'This only includes landmarks that we have gathered in our data set.',
                img: "/thumbnails/aleksandar.jpg"
            }
        ]
    ]

    const [user, _] = useAtom(userAtom);
    const hasMounted = useHasMounted();
    if (!hasMounted) {
        return null;
    }

    return (
        <div>
            <main className={"w-full h-full text-center"}>
                {/*Title*/}
                <div className={'pt-12'}>
                    <h1>
                        <span className={"font-bold text-6xl"}>
                            Skopje GeoGuessr
                        </span>
                    </h1>
                </div>

                {/*Promotional message*/}
                <div className={"p-8"}>
                    <p className={'font-medium  text-xl text-neutral-600'}>
                        Explore different locations throughout Skopje while also testing your knowledge of the city!
                        <br/>
                        The better you are, the higher you can climb the leaderboards!
                    </p>
                </div>

                {/*<div className={"h-0.5 bg-neutral-800"}/>*/}

                {/*Buttons for play*/}
                <div className={"flex flex-row justify-center w-full p-4"}>
                    <Link href={"/register"}>
                        <button disabled={user != null}
                                className={"inline-flex items-center rounded mx-2 bg-cyan-600 hover:bg-cyan-700 py-2 px-4 disabled:opacity-50"}>
                            Register
                            <UserPlusIcon className={"ml-1 h-6 fill-cyan-100"}/>
                        </button>
                    </Link>
                    <Link href={"/leaderboards"}>
                        <button
                            className={"inline-flex items-center rounded mx-2 bg-cyan-600 hover:bg-cyan-700 py-2 px-4"}>
                            Leaderboards
                            <StarIcon className={"ml-1 h-6 fill-cyan-100"}/>
                        </button>
                    </Link>
                </div>


                {/*Game modes choose*/}
                <div className={"my-12"}>
                    <h1 className={"text-3xl text-white italic font-bold text-left ml-8"}>
                        Game modes
                    </h1>

                    {gameModes.map((mode, idx) => <GameModeCardRow key={idx} cards={mode}/>)}
                </div>

            </main>
        </div>
    )
}
