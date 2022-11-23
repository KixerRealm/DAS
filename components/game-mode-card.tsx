import Image from "next/image";

export interface GameModeCardParameters {
    title: string;
    description: string;
    img: string;
}

export default function GameModeCard(params: GameModeCardParameters) {
    return (
        <a href={"#"}
           className={
               "basis-1/3 mx-8 flex flex-col items-center border rounded-lg shadow-md" +
               " border-neutral-700 bg-neutral-800 hover:bg-neutral-700 hover:shadow-xl"
           }>
            <Image className={"object-cover w-full rounded-t-lg h-96"}
                   src={params.img} alt=""
                   width={320} height={180}
            />
            <div className={"flex flex-col justify-between p-4 leading-normal"}>
                <h5 className={
                    "mb-2 text-2xl font-bold tracking-tight text-neutral-900 dark:text-white"
                }>{params.title}</h5>
                <p className={"mb-3 font-normal text-neutral-700 dark:text-neutral-400"}>
                    {params.description}
                </p>
            </div>
        </a>
    )
}
