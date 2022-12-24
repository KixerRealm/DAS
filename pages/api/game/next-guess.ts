import {NextApiRequest, NextApiResponse} from "next";
import {GameModeType} from "../../../enums/game-mode-type";

export type ProposedGuess = {
    photo_reference?: string;
    location?: CustomLatLng;
};

export type CustomLatLng =  {
    lat: number;
    lng: number;
}

type RequestedGameType = {
    gameType: GameModeType;
};

const places: { [gameMode: string]: ProposedGuess[] } = {
    "COFFEE": [
        {photo_reference: '/places/pub18.jpg', location: {lat: 42.00309517983155, lng: 21.417579153818505}},
        {photo_reference: '/places/kotur.jpg', location: {lat: 41.99462838215089, lng: 21.429372027723335}},
        {photo_reference: '/places/shakespeare.jpg', location: {lat: 41.998312391107156, lng: 21.423461082654534}},
        {photo_reference: '/places/kasa.jpg', location: {lat: 42.00394679572184, lng: 21.398661182654656}}
    ],
    "LANDMARKS": [
        {photo_reference: '/places/aleksandar.jpg', location: {lat: 41.99601170706747, lng: 21.43139735381833}},
        {photo_reference: '/places/aqueduct.jpg', location: {lat: 42.02331469645388, lng: 21.41866568128308}},
        {photo_reference: '/places/partisan.png', location: {lat: 41.997619752374625, lng: 21.442380685098197}},
        {photo_reference: '/places/saat-kula.jpg', location: {lat: 42.002139528044076, lng: 21.441413345222614}}
    ]
}

places["ALL"] = places["COFFEE"].concat(places["LANDMARKS"]);

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<ProposedGuess>
) {
    const requestedGameType = req.body as RequestedGameType;
    const data = places[requestedGameType.gameType];

    res.status(200).json(
        data[Math.floor(Math.random() * data.length)]
    );
}
