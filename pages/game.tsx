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
import {useStartGameEffect} from "../hooks/useStartGame";
import {userAtom} from "../components/user-nav-bar";
import {GameModeType} from "../enums/game-mode-type";
import {useRouter} from "next/router";
import {useHasMounted} from "../hooks/useHasMounted";

export type Guess = {
    id?: string;
    location?: LatLng;
    correctLocation?: LatLng;
    points: number;
};

export class GuessInstance implements Guess {
    id?: string;
    points: number = 2000;
}

type GameParameters = {
    gameType: GameModeType;
}


function getMinutesBetweenDates(startDate: Date, endDate: Date) {
    const diff = endDate.getTime() - startDate.getTime();
    return (diff / 60000);
}

export const guessAtom = atom<Guess>(new GuessInstance());
export const inGameAtom = atom<boolean>(false);
export const gameCompletedAtom = atom<boolean>(false);


export default function Game() {
    const [user, _1] = useAtom(userAtom);
    const router = useRouter();
    const query = router.query as GameParameters;
    const hasMounted = useHasMounted();
    const [_, setMap] = useState(null);
    const [guess, setGuess] = useAtom(guessAtom);
    const [gameCompleted, setGameCompleted] = useAtom(gameCompletedAtom);
    const [gameState, setGameState] = useAtom(gameStateAtom);
    const updateGame = useUpdateAtom(inGameAtom);


    useEffect(() => {
        updateGame(true);
    }, [updateGame]);

    useExit(() => {
        updateGame(false);
        setGameCompleted(false);
        setGameState(new GameStateInstance());
        setGuess(new GuessInstance());
    });

    useStartGameEffect(user?.email ?? "", query.gameType, async (data: any) => {
        setGameState(prevState => ({
            ...prevState,
            id: data!.id
        }));
    });

    const {isLoaded} = useJsApiLoader({
        googleMapsApiKey: process.env.NEXT_PUBLIC_MAPS_API_KEY!
    });

    const addMarker = useCallback((mapsMouseEvent: MapMouseEvent) => {
        setGuess(prevState => ({
            ...prevState,
            ['location']: mapsMouseEvent.latLng!
        }));
    }, [setGuess]);

    const removeMarker = useCallback((event: any) => {
        setGuess(prevState => ({
            ...prevState,
            ['location']: undefined
        }));
    }, [setGuess]);


    const onLoad = useCallback((map: any) => {
        setMap(map);
    }, []);

    const onUnmount = useCallback(function callback(map: any) {
        setMap(null)
    }, []);

    if (!hasMounted) {
        return null;
    }

    if (user == null) {
        router.push({
            pathname: "/login",
            query: {returnPath: router.asPath}
        });
    }

    return (
        <div className={'h-screen w-screen'}>
            <GameStateWindow gameModeType={query.gameType}/>
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
                        {gameCompleted ? <GameCompletedModal type={query.gameType} points={gameState.points} minutesTaken={gameState.endedAt != undefined ? getMinutesBetweenDates(gameState.startedAt, gameState.endedAt) : 0.}/> : <></>}
                        {guess?.location != null ? <Marker position={guess.location!} onClick={removeMarker}/> : <></>}
                    </GoogleMap>
                ) : <></>
            }
        </div>
    );
}



