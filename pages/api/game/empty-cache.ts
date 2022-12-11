import {NextApiRequest, NextApiResponse} from "next";
import {deleteCookie, getCookie} from "cookies-next";

export default function handler(
    req: NextApiRequest,
    res: NextApiResponse
) {
    deleteCookie('attempts', {req, res});
    res.status(200).json({});
}
