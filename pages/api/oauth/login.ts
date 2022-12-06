import {NextApiRequest} from "next/types";
import {NextApiResponse} from "next";
import {UserCredentials} from "../../../hooks/useLogin";
import {APIError} from "../leaderboards";
import {faker} from "@faker-js/faker";


export type User = {
    email: string;
    displayName: string;
    profilePictureUrl: string;
}

const allowedEmails: string[] = [
    'csprogrammers@outlook.com',
    'andrej.mishevski@gmail.com',
    'kixer.wade2000@outlook.com',
    'admin@admin.com',
]

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse<User | APIError>
) {
    const credentials = req.body as UserCredentials;
    if (!allowedEmails.includes(credentials.email)) {
        res.status(400).json({
            message: 'No user matches the given credentials!'
        });
        return;
    }

    if(credentials.password != "test") {
        res.status(400).json({
            message: 'No user matches the given credentials!'
        });
        return;
    }

    res.status(200).json({
        email: credentials.email,
        displayName: faker.internet.userName(),
        profilePictureUrl: faker.image.avatar()
    });
}
