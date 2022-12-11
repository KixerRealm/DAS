import {NextApiRequest, NextApiResponse} from "next";
import {allowedEmails} from "../oauth/login";
import {getCookie} from "cookies-next";
import {GameAttempt} from "../game/start";
import getMinutesBetweenDates from "../../../utilities/minutes-between-dates";
import {faker} from "@faker-js/faker";


export default function handler(
    req: NextApiRequest,
    res: NextApiResponse
) {
    const {email} = req.body;
    if (!allowedEmails.includes(email)) {
        res.status(400).json({
            message: 'User invalid... Please try again...'
        });
        return;
    }

    const attempts: GameAttempt[] = JSON.parse(getCookie('attempts', {req, res}) as string);
    res.status(200).json(
        attempts.filter(attempt => attempt.endedAt != undefined).map(attempt => ({
            timeTaken: getMinutesBetweenDates(new Date(attempt.startedAt), new Date(attempt.endedAt!)),
            id: attempt.id,
            placement: faker.datatype.number({max: 100}),
            gameMode: attempt.gameType,
            datePlayed: attempt.startedAt,
            totalPoints: attempt.totalPoints
        }))
    );
}
