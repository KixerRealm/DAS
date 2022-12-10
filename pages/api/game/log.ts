import {NextApiRequest, NextApiResponse} from "next";
import {getCookie} from "cookies-next";

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse
) {
    let cookie = getCookie('attempts', {req, res});
    if(cookie == null) {
        cookie = '[]';
    }
    res.status(200).json(JSON.parse(cookie as string));
}
