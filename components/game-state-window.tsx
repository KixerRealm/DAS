import Image from "next/image";
import {CheckIcon, HomeIcon} from "@heroicons/react/24/solid";
import {useCallback, useEffect} from "react";
import {gameCompletedAtom, Guess, guessAtom, GuessInstance} from "../pages/game";
import {atom, useAtom} from "jotai";
import {useNextGuess} from "../hooks/useNextGuess";
import {GameModeType} from "../enums/game-mode-type";
import {useSubmitGame} from "../hooks/useSubmitGame";
import {userAtom} from "./user-nav-bar";
import {useHasMounted} from "../hooks/useHasMounted";
import LatLng = google.maps.LatLng;
import computeDistanceBetween from "../utilities/compute-distance-between";

type GameStateWindowParameters = {
    gameModeType: GameModeType;
}

type GameState = {
    id: string;
    image: string;
    correctLocation?: LatLng;
    points: number;
    guessesLeft: number;
    guessesMade: Guess[];
    startedAt: Date;
    endedAt?: Date;
};

export class GameStateInstance implements GameState {
    id: string = '';
    image: string = "/thumbnails/pub18.jpg";
    points: number = 0;
    guessesLeft: number = 4;
    guessesMade: Guess[] = [];
    startedAt: Date = new Date();
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

        // calculate guess accuracy points
        const distance = computeDistanceBetween(guess.location!, gameState.correctLocation!); // in meters
        const calculatedPoints = submittedGuess.points - Math.floor(distance);
        submittedGuess.points = Math.max(calculatedPoints, 0);

        // set correct location for the guess
        submittedGuess.correctLocation = gameState.correctLocation;

        // fetch next guesses
        setGuess(new GuessInstance());
        setGameState(prevState => ({
            ...prevState,
            guessesLeft: prevState.guessesLeft - 1,
            points: prevState.points + submittedGuess.points,
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
            setGameState(prevState => ({
                ...prevState,
                endedAt: new Date()
            }));
        }
    }, [gameState.correctLocation, gameState.guessesLeft, gameState.guessesMade, gameState.id, gameState.points,
        guess, nextGameMutate, params.gameModeType, setGameCompleted, setGameState, setGuess, submitGameMutate, user?.email]);


    useEffect(() => {
        if (params.gameModeType == undefined)
            return;
        nextGameMutate(params.gameModeType);
    }, [params.gameModeType, nextGameMutate]);

    if (!hasMounted) {
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
                        className={"inline-flex items-center rounded mx-2 bg-blue-600 disabled:hover:bg-blue-600 hover:bg-blue-700 py-2 px-8 disabled:opacity-50"}>
                    Submit
                    <CheckIcon className={"ml-1 h-6 fill-cyan-100"}/>
                </button>
            </div>
        </div>
    );
}
