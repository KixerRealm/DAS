import {GoogleMap, Marker, useJsApiLoader} from "@react-google-maps/api";
import {useCallback, useEffect, useState} from "react";
import {atom, useAtom} from "jotai";
import {useUpdateAtom} from "jotai/utils";
import useExit from "../hooks/useExit";
import MapMouseEvent = google.maps.MapMouseEvent;
import LatLng = google.maps.LatLng;
import GameStateWindow, {gameStateAtom, GameStateInstance} from "../components/game-state-window";
import GameCompletedModal from "../components/game-completed-modal";
import mapOptions from "../styles/maps-style.json";

export type Guess = {
    id?: string;
    location: LatLng | null;
};

export class GuessInstance implements Guess {
    id?: string;
    location: LatLng | null = null;
}


export const guessAtom = atom<Guess>(new GuessInstance());
export const inGameAtom = atom<boolean>(false);
export const gameCompletedAtom = atom<boolean>(false);


export default function Game() {
    const [_, setMap] = useState(null);
    const [guess, setGuess] = useAtom(guessAtom);
    const [gameCompleted, setGameCompleted] = useAtom(gameCompletedAtom);
    const [_1, setGameState] = useAtom(gameStateAtom);
    const updateGame = useUpdateAtom(inGameAtom);
    useEffect(() => {
        updateGame(true);
    }, [null]);

    useExit(() => {
        updateGame(false);
        setGameCompleted(false);
        setGameState(new GameStateInstance());
        setGuess(new GuessInstance());
    });

    const {isLoaded} = useJsApiLoader({
        googleMapsApiKey: process.env.NEXT_PUBLIC_MAPS_API_KEY!
    });

    const addMarker = useCallback((mapsMouseEvent: MapMouseEvent) => {
        setGuess(prevState => ({
            ...prevState,
            ['location']: mapsMouseEvent.latLng!
        }));
    }, []);

    const removeMarker = useCallback((event: any) => {
        setGuess(prevState => ({
            ...prevState,
            ['location']: null
        }));
    }, []);


    const onLoad = useCallback((map: any) => {
        setMap(map);
    }, []);

    const onUnmount = useCallback(function callback(map: any) {
        setMap(null)
    }, []);

    return (
        <div className={'h-screen w-screen'}>
            <GameStateWindow/>
            {
                isLoaded ? (
                    <GoogleMap
                        clickableIcons={false}
                        options={mapOptions}
                        onClick={addMarker}
                        mapContainerClassName={"h-full w-full"}
                        zoom={16}
                        onLoad={onLoad}
                        onUnmount={onUnmount}
                    >
                        {gameCompleted ? <GameCompletedModal/> : <></>}
                        {guess?.location != null ? <Marker position={guess.location!} onClick={removeMarker}/> : <></>}
                    </GoogleMap>
                ) : <></>
            }
        </div>
    );
}



