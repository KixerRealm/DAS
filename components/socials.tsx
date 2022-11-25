import GithubIcon from "./icons/github-icon";
import {InboxStackIcon} from "@heroicons/react/24/solid";
import LinkedInIcon from "./icons/linked-in-icon";

interface SocialsParameters {
    github: string;
    mail: string;
    linkedIn: string;
}

export default function Socials(params: SocialsParameters) {
    return (
        <div className={'flex flex-row'}>
            <a href={params.github}>
                <div className={'fill-white w-6 ml-2 hover:fill-blue-500'}>
                    <GithubIcon/>
                </div>
            </a>

            <a href={`mailto:${params.mail}`}>
                <InboxStackIcon className={'w-6 ml-2 hover:fill-green-500'}/>
            </a>

            <a href={params.linkedIn}>
                <div className={'fill-white w-6 ml-2 hover:fill-red-500'}>
                    <LinkedInIcon/>
                </div>
            </a>
        </div>
    );
}
