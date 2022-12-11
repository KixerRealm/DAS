import {useAtom} from "jotai";
import {gameCompletedAtom} from "../pages/game";
import Link from "next/link";
import {useCallback} from "react";
import {gameStateAtom, GameStateInstance} from "./game-state-window";
import {userAtom} from "./user-nav-bar";
import {useHasMounted} from "../hooks/useHasMounted";
import {useNextGuess} from "../hooks/useNextGuess";
import {GameModeType} from "../enums/game-mode-type";
import {useStartGame} from "../hooks/useStartGame";

type GameCompleteParameters = {
    points: number;
    type: GameModeType;
    minutesTaken: number;
};


export default function GameCompletedModal(params: GameCompleteParameters) {
    const [gameCompleted, setGameCompleted] = useAtom(gameCompletedAtom);
    const [gameState, setGameState] = useAtom(gameStateAtom);
    const [user, _1] = useAtom(userAtom);
    const hasMounted = useHasMounted();

    const nextGuessMutation = useNextGuess((data) => {
        setGameState(prevState => ({
            ...prevState,
            image: data.image,
            correctLocation: data.location
        }));
    });

    const nextGuessMutate = nextGuessMutation.mutate;

    const startGameMutation = useStartGame(async (data: any) => {
        setGameState(prevState => ({
            ...prevState,
            id: data!.id
        }));
    });

    const startGameMutate = startGameMutation.mutate;

    const playAgainState = useCallback(() => {
        setGameState(new GameStateInstance());
        nextGuessMutate(params.type);
        startGameMutate({email: user?.email ?? '', gameModeType: params.type});
        setGameCompleted(false);
    }, [nextGuessMutate, params.type, setGameCompleted, setGameState, startGameMutate, user?.email]);

    const homeState = useCallback(() => {
        setGameState(new GameStateInstance());
        nextGuessMutate(params.type);
        setGameCompleted(false);
    }, [nextGuessMutate, params.type, setGameCompleted, setGameState, startGameMutate, user?.email]);


    if (!hasMounted) {
        return null;
    }

    return (
        <div
            className={`z-50 ${gameCompleted ? '' : 'hidden'} w-full p-4 overflow-x-hidden overflow-y-auto inset-0 h-full grid place-items-center absolute z-50 top-0 right-0 bg-neutral-800 bg-opacity-70`}>
            <div className={"relative max-w-2xl rounded-lg md:h-auto bg-neutral-900 grid place-items-center"}>
                <div className={"relative rounded-lg shadow bg-gray-700 p-4"}>
                    <div className={"flex items-start justify-between p-4 border-b rounded-t border-gray-600"}>
                        <h3 className={"text-2xl font-bold text-white"}>
                            You completed a game!
                        </h3>
                        <button type={"button"}
                                className={"text-gray-400 bg-transparent rounded-lg text-sm p-1.5 ml-auto inline-flex items-center hover:bg-gray-600 hover:text-white"}>
                        </button>
                    </div>
                    <div className={"p-6 space-y-6"}>
                        <p className={"text-white-500 dark:text-white-400"}>
                            <span className={"font-semibold text-xl"}>Points: </span>
                            <span className={'font-semibold text-lg'}>{params.points} / 8000</span>
                        </p>
                        <p className={"text-white-500 dark:text-white-400"}>
                            <span className={"font-semibold text-xl"}>Time taken: </span>
                            <span
                                className={'font-semibold text-lg'}>
                                {Math.floor(params.minutesTaken)} minutes{' '}
                                {Math.round((params.minutesTaken - Math.floor(params.minutesTaken)) * 60)} seconds
                            </span>
                        </p>
                        <div className={'text-white-400'}>
                            <p>Your attempt has been submitted for review by our system,
                                results should be available on your profile shortly!</p>
                        </div>
                    </div>
                    <div
                        className={"flex flex-row justify-center p-6 space-x-2 border-t rounded-b border-gray-600"}>
                        <button type={"button"} onClick={playAgainState}
                                className={"text-white focus:ring-4 focus:outline-none font-medium rounded-lg text-sm px-5 py-2.5 text-center bg-blue-600 hover:bg-blue-700 focus:ring-blue-800"}>
                            Play again!
                        </button>
                        <Link href={"/"}>
                            <button type={"button"} onClick={homeState}
                                    className={"focus:ring-4 focus:outline-none rounded-lg border text-sm font-medium px-5 py-2.5 focus:z-10 bg-gray-700 text-gray-300 border-gray-500 hover:text-white hover:bg-gray-600 focus:ring-gray-600"}>
                                Go to home page
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
