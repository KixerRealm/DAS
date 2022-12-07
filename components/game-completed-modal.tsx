import {useAtom} from "jotai";
import {gameCompletedAtom} from "../pages/game";
import Link from "next/link";
import {useCallback} from "react";
import {gameStateAtom, GameStateInstance} from "./game-state-window";

export default function GameCompletedModal() {
    const [gameCompleted, setGameCompleted] = useAtom(gameCompletedAtom);
    const [_, setGameState] = useAtom(gameStateAtom);

    const resetState = useCallback(() => {
        setGameState(new GameStateInstance());
        setGameCompleted(false);
    }, []);

    return (
        <div className={`z-50 ${gameCompleted ? '' : 'hidden'} w-full p-4 overflow-x-hidden overflow-y-auto inset-0 h-full grid place-items-center absolute z-50 top-0 right-0 bg-neutral-800 bg-opacity-70`}>
            <div className={"relative max-w-2xl rounded-lg md:h-auto bg-neutral-900 grid place-items-center"}>
                <div className={"relative bg-white rounded-lg shadow dark:bg-gray-700 p-4"}>
                    <div className={"flex items-start justify-between p-4 border-b rounded-t dark:border-gray-600"}>
                        <h3 className={"text-2xl font-bold text-gray-900 dark:text-white"}>
                            You completed a game!
                        </h3>
                        <button type={"button"}
                                className={"text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center dark:hover:bg-gray-600 dark:hover:text-white"}>
                        </button>
                    </div>
                    <div className={"p-6 space-y-6"}>
                        <p className={"text-base leading-relaxed text-gray-500 dark:text-gray-400"}>
                            <span className={"font-semibold text-xl"}>Points: </span>XXXX / 8000
                        </p>
                        <p className={"text-base leading-relaxed text-gray-500 dark:text-gray-400"}>
                            <span className={"font-semibold text-xl"}>Placements: </span>XX
                        </p>
                    </div>
                    <div
                        className={"flex flex-row justify-center p-6 space-x-2 border-t border-gray-200 rounded-b dark:border-gray-600"}>
                        <button type={"button"} onClick={resetState}
                                className={"text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"}>
                            Play again!
                        </button>
                        <Link href={"/"}>
                            <button type={"button"} onClick={resetState}
                                    className={"text-gray-500 bg-white hover:bg-gray-100 focus:ring-4 focus:outline-none focus:ring-blue-300 rounded-lg border border-gray-200 text-sm font-medium px-5 py-2.5 hover:text-gray-900 focus:z-10 dark:bg-gray-700 dark:text-gray-300 dark:border-gray-500 dark:hover:text-white dark:hover:bg-gray-600 dark:focus:ring-gray-600"}>
                                Go to home page
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
}
