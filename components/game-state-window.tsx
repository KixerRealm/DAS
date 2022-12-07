import Image from "next/image";
import {CheckIcon} from "@heroicons/react/24/solid";
import {useCallback, useState} from "react";
import {gameCompletedAtom, Guess, guessAtom, GuessInstance} from "../pages/game";
import {atom, useAtom} from "jotai";

type GameState = {
    image: string;
    points: number;
    guessesLeft: number;
    guessesMade: Guess[];
};

export class GameStateInstance implements GameState {
    image: string = "/thumbnails/pub18.jpg";
    points: number = 0;
    guessesLeft: number = 4;
    guessesMade: Guess[] = [];
}

export const gameStateAtom = atom<GameState>(new GameStateInstance());

export default function GameStateWindow() {
    const [gameState, setGameState] = useAtom(gameStateAtom);
    const [guess, setGuess] = useAtom(guessAtom);
    const [_, setGameCompleted] = useAtom(gameCompletedAtom);

    const submitGuess = useCallback((event: any) => {
        const submittedGuess = JSON.parse(JSON.stringify(guess)) as Guess;
        setGuess(new GuessInstance());
        setGameState(prevState => ({
            image: "/thumbnails/aleksandar.jpg",
            guessesLeft: prevState.guessesLeft - 1,
            points: prevState.points + 2000,
            guessesMade: prevState.guessesMade.concat([submittedGuess])
        }));

        if (gameState.guessesLeft <= 1) {
            setGameCompleted(true);
        }
    }, [gameState]);

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
