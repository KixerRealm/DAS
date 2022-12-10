import Image from "next/image";
import {CheckIcon} from "@heroicons/react/24/solid";
import {useCallback, useEffect} from "react";
import {gameCompletedAtom, Guess, guessAtom, GuessInstance} from "../pages/game";
import {atom, useAtom} from "jotai";
import {useNextGuess} from "../hooks/useNextGuess";
import {GameModeType} from "../enums/game-mode-type";
import {useSubmitGame} from "../hooks/useSubmitGame";
import {userAtom} from "./user-nav-bar";
import {useRouter} from "next/router";
import {useHasMounted} from "../hooks/useHasMounted";

type GameStateWindowParameters = {
    gameModeType: GameModeType;
}

type GameState = {
    id: string;
    image: string;
    correctLocation: number[];
    points: number;
    guessesLeft: number;
    guessesMade: Guess[];
};

export class GameStateInstance implements GameState {
    id: string = '';
    image: string = "/thumbnails/pub18.jpg";
    correctLocation: number[] = [0, 0];
    points: number = 0;
    guessesLeft: number = 4;
    guessesMade: Guess[] = [];
}

export const gameStateAtom = atom<GameState>(new GameStateInstance());

export default function GameStateWindow(params: GameStateWindowParameters) {
    const [gameState, setGameState] = useAtom(gameStateAtom);
    const [guess, setGuess] = useAtom(guessAtom);
    const [_, setGameCompleted] = useAtom(gameCompletedAtom);
    const [user, _1] = useAtom(userAtom);
    const hasMounted = useHasMounted();

    const nextGuessMutation = useNextGuess(async (data: any) => {
        setGameState(prevState => ({
            ...prevState,
            image: data.image,
            correctLocation: data.location
        }));
    });

    const nextGameMutate = nextGuessMutation.mutate;

    const submitGameMutation = useSubmitGame(async (data: any) => {

    });

    const submitGameMutate = submitGameMutation.mutate;

    const submitGuess = useCallback((event: any) => {
        const submittedGuess = JSON.parse(JSON.stringify(guess)) as Guess;
        // fetch next guesses
        setGuess(new GuessInstance());
        setGameState(prevState => ({
            ...prevState,
            guessesLeft: prevState.guessesLeft - 1,
            points: prevState.points + 2000,
            guessesMade: prevState.guessesMade.concat([submittedGuess])
        }));
        nextGameMutate(params.gameModeType);
        if (gameState.guessesLeft <= 1) {
            setGameCompleted(true);
            submitGameMutate({
                id: gameState.id,
                email: user?.email ?? "",
                guesses: gameState.guessesMade.concat([submittedGuess]),
                points: gameState.points + 2000
            });
        }
    }, [gameState.guessesLeft, gameState.guessesMade, gameState.id, gameState.points, guess, nextGameMutate,
        params.gameModeType, setGameCompleted, setGameState, setGuess, submitGameMutate, user?.email]);


    useEffect(() => {
        if (params.gameModeType == undefined)
            return;
        nextGameMutate(params.gameModeType);
    }, [params.gameModeType, nextGameMutate]);

    if(!hasMounted) {
        return null;
    }

    return (
        <div
            className={"w-96 h-fit absolute z-50 top-4 right-4 bg-neutral-800 grid place-items-center rounded-xl py-4 shadow drop-shadow-lg"}>
            <Image className={"mt-4 w-70 h-64"}
                   src={gameState.image} alt=""
                   width={320} height={180}
            />
            <div className={"flex flex-row m-2"}>
                <div className={"mr-8"}>
                    <span className={"font-semibold text-xl"}>Points: </span>
                    <span className={"text-neutral-400 text-lg"}>{gameState.points} / 8000</span>
                </div>
                <div>
                    <span className={"font-semibold text-xl"}>Guesses left: </span>
                    <span className={"text-neutral-400 text-lg"}>{gameState.guessesLeft}</span>
                </div>
            </div>

            <div className={"m-2"}>
                <button disabled={guess.location == null} onClick={submitGuess}
                        className={"inline-flex items-center rounded mx-2 bg-blue-600 disabled:hover:bg-blue-600 hover:bg-blue-700 py-2 px-4 disabled:opacity-50"}>
                    Submit
                    <CheckIcon className={"ml-1 h-6 fill-cyan-100"}/>
                </button>
            </div>
        </div>
    );
}
