import {NextApiRequest, NextApiResponse} from "next";
import {faker} from "@faker-js/faker";
import {GameModeType} from "../../enums/game-mode-type";


export type LeaderboardRecord = {
    username: string;
    totalPoints: number;
    timeCompleted: Date;
    timeStarted: Date;
    type: GameModeType;
    profilePictureUrl: string;
}

export type APIError = {
    message: string;
}

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<LeaderboardRecord[] | APIError>
) {
    const gameMode = validateAndGetGameMode(req, res);
    if (gameMode == null) {
        return;
    }

    const records = [...new Array(15)].map((_, index) => {

        const started = faker.date.recent();
        const finishedMax = new Date(started);
        finishedMax.setMinutes(finishedMax.getMinutes() + 10);
        return {
            username: faker.internet.userName(),
            total: faker.datatype.number({max: 4000}),
            timeStarted: started,
            timeCompleted: faker.date.between(started, finishedMax),
            type: gameMode,
            profilePictureUrl: faker.image.avatar()
        };

    });

    function sortByTotalPoints() {
        return (a: LeaderboardRecord, b: LeaderboardRecord) =>
            a.totalPoints < b.totalPoints ? 1 :
                a.totalPoints > b.totalPoints ? -1 : 0;
    }

    res.status(200).json(records.sort(sortByTotalPoints()));
}


function validateAndGetGameMode(
    req: NextApiRequest,
    res: NextApiResponse<LeaderboardRecord[] | APIError>
): GameModeType | null {
    const {gameMode} = req.query;

    if (gameMode == undefined) {
        res.status(400).json({
            message: 'Parameter "gameMode" is required!'
        });
        return null;
    }

    if (gameMode instanceof Array) {
        res.status(400).json({
            message: 'You can only search by one game mode!'
        });
        return null;
    }

    if (!(gameMode in GameModeType)) {
        res.status(400).json({
            message: 'Game mode doesn\'t exist!'
        });
        return null;
    }


    return gameMode as GameModeType;
}

