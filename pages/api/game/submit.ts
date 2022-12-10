import {NextApiRequest, NextApiResponse} from "next";
import {APIError} from "../leaderboards";
import {allowedEmails} from "../oauth/login";
import {GameAttempt} from "./start";
import {Guess} from "../../game";
import {deleteCookie, getCookie, setCookie} from "cookies-next";

export type GameSubmissionRequest = {
    id: string;
    email: string;
    points: number;
    guesses: Guess[];
};

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<APIError | object>
) {
    const submission = req.body as GameSubmissionRequest;
    console.log(submission);
    if (!allowedEmails.includes(submission.email)) {
        res.status(400).json({
            message: 'User invalid... Please try again...'
        });
        return;
    }

    const attempts: GameAttempt[] = JSON.parse(getCookie('attempts', {req, res}) as string);

    if (attempts.filter(att => att.id == submission.id).length <= 0) {
        res.status(400).json({
            message: 'Game ID is invalid.'
        });
        return;
    }

    const attempt = attempts
        .filter(att => att.id == submission.id)
        .pop()!;
    attempts.forEach((element, index) => {
        if (element.id == attempt.id) attempts.splice(index, 1);
    });
    attempt.endedAt = new Date();
    attempt.guesses = submission.guesses;
    attempt.totalPoints = submission.points;
    attempts.push(attempt);
    setCookie('attempts', JSON.stringify(attempts), {req, res});
    res.status(200).json({});
}
