import {NextApiRequest, NextApiResponse} from "next";
import {APIError} from "../leaderboards";
import {allowedEmails} from "../oauth/login";
import {GameModeType} from "../../../enums/game-mode-type";
import {Guess} from "../../game";
import {getCookie, setCookie} from "cookies-next";

type GameStartedRequest = {
    email: string;
    gameType: GameModeType;
};

export type GameAttempt = {
    id: string;
    email: string;
    gameType: GameModeType;
    startedAt: Date;
    endedAt?: Date;
    guesses: Guess[];
    totalPoints?: number;
}

class GameAttemptInstance implements GameAttempt {
    id: string = Math.random().toString(36).slice(2);
    email: string = "";
    startedAt: Date = new Date();
    endedAt?: Date;
    gameType: GameModeType = GameModeType.ALL;
    guesses: Guess[] = [];
    totalPoints?: number = undefined;
}

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<APIError | GameAttempt>
) {
    const gameStart = req.body as GameStartedRequest;
    // Check if user exists
    if (!allowedEmails.includes(gameStart.email)) {
        res.status(400).json({
            message: 'User invalid... Please try again...'
        });
        return;
    }

    const attempt = new GameAttemptInstance();
    attempt.email = gameStart.email;
    attempt.gameType = gameStart.gameType;
    let cookie = getCookie('attempts', {req, res});
    if(cookie == null) {
        cookie = '[]';
    }
    const attempts: GameAttempt[] = JSON.parse(cookie as string);
    attempts.push(attempt);
    setCookie('attempts', JSON.stringify(attempts), {req, res});
    res.status(200).json(attempt);
}
