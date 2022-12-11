import Image from "next/image";
import {CheckIcon, HomeIcon} from "@heroicons/react/24/solid";
import {useAtom} from "jotai";
import {userAtom} from "./user-nav-bar";
import {gameStateAtom, GameStateInstance} from "./game-state-window";
import {useHasMounted} from "../hooks/useHasMounted";
import {useCancelGame} from "../hooks/useCancelGame";
import {gameCompletedAtom} from "../pages/game";
import {useCallback} from "react";
import {useRouter} from "next/router";


export default function GameEscapeHatch() {

    const [user, _] = useAtom(userAtom);
    const [gameState, setGameState] = useAtom(gameStateAtom);
    const [_2, setGameCompleted] = useAtom(gameCompletedAtom);
    const router = useRouter();


    const hasMounted = useHasMounted();
    const {mutate} = useCancelGame(async (data: any) => {
        setGameCompleted(true);
        setGameState(new GameStateInstance());
        await router.push("/");
    });

    const cancelGame = useCallback(() => {
        mutate({email: user?.email ?? '', id: gameState.id});
    }, [gameState.id, mutate, user?.email]);

    if (!hasMounted) {
        return null;
    }

    return (
        <div
            className={"h-fit absolute z-50 top-0 left-0 grid place-items-center rounded-xl shadow drop-shadow-lg"}>
            <div className={"m-2"}>
                <button onClick={cancelGame}
                        className={"inline-flex items-center rounded mx-2 bg-rose-700 hover:bg-rose-800 py-2 px-4 border border-neutral-800 border-2"}>
                    Home
                    <HomeIcon className={"ml-1 h-6 fill-cyan-100"}/>
                </button>
            </div>
        </div>
    )
}
