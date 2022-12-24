import {NextApiRequest, NextApiResponse} from "next";
import {faker} from "@faker-js/faker";
import {GameModeType} from "../../../enums/game-mode-type";

export type PeakPlacement = {
    placement: number;
    endedAt: Date;
    gameType: GameModeType;
}

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse
) {
    // This should serve to access previous
    //  game attempts for a specific user so
    //  we can display it on the FE - profile - history
    const data = [];
    for (let i = 0; i < 3; i++) {
        data.push({
            placement: faker.datatype.number({max: 15}),
            datePlayed: faker.date.soon(5),
            gameMode: Object.values(GameModeType)[i]
        });
    }

    res.status(200).json(data);
}
