import Image from 'next/image';
import Socials from "../components/socials";
import RedirectAnchor from "../components/redirect-anchor";

export default function About() {
    return (
        <div className={"w-full h-full text-center"}>
            {/*About the team*/}
            <div className={'grid h-screen place-items-center'}>
                <h1 className={"italic text-4xl mt-8"}>
                    About the team
                </h1>


                <div>
                    {/*<hr className={"w-full my-6 border-neutral-200 sm:mx-auto dark:border-neutral-700 lg:my-8"}/>*/}

                    {/* Filip */}
                    <div className={'flex flex-row justify-center w-full h-full mt-4 text-left'}>
                        <div className={'h-full'}>
                            <Image className={'rounded-full border-neutral-600'}
                                   src={'/developers/pazzio.png'} width={280}
                                   height={280} alt={''}/>

                        </div>
                        <div className={'ml-16 w-1/2'}>
                            <div className={'flex flex-row italic font-semibold text-xl text-neutral-200'}>
                                <h2>Филип Јованов (Pazzio)</h2>
                                <Socials
                                    github={'https://github.com/PegasusMKD'}
                                    mail={'csprogrammers@outlook.com'}
                                    linkedIn={'https://www.linkedin.com/in/filip-jovanov-a84403225/'}
                                />
                            </div>

                            <p className={'text-sm text-neutral-200 my-1 mb-4'}>Senior full-stack developer for 4
                                years.</p>

                            <ul className={'ml-8 list-disc'}>
                                <li className={'list-disc text-neutral-200 my-2'}>
                                    Developed and designed the UI of the application using{' '}
                                    <RedirectAnchor url={'https://tailwindcss.com/'} text={'Tailwind CSS'}/> &{' '}
                                    <RedirectAnchor url={'https://nextjs.org/'} text={'NextJS'}/>.
                                    <br/>
                                    Other than development and design, he also acted as a PO, software architect and
                                    DevOps
                                    engineer.
                                </li>
                                <li className={'text-neutral-200 my-2'}>
                                    A developer with a passion for programming and challenges.<br/>
                                    Always researching new technologies and ways to improve old ones.
                                </li>
                                <li className={'text-neutral-200 my-3'}>
                                    Parallel projects:
                                    <ul className={'list-disc list-inside pl-5 mt-2'}>
                                        <li>Scientific paper & library regarding ORM optimizations;</li>
                                        <li>Text generation using a knowledge graph.</li>
                                    </ul>
                                </li>

                            </ul>
                        </div>
                    </div>

                    <hr className={"w-full my-6 border-neutral-200 sm:mx-auto dark:border-neutral-700 lg:my-8"}/>

                    {/* Kiko */}
                    <div className={'flex flex-row justify-center w-full h-full mt-4 text-left'}>
                        <div className={'ml-16 w-1/2'}>
                            <div className={'flex flex-row italic font-semibold text-xl text-neutral-200'}>
                                <h2>Кристијан Костовски (Kiko)</h2>
                                <Socials
                                    github={'https://github.com/KixerRealm'}
                                    mail={'kixer.wade2000@outlook.com'}
                                    linkedIn={'https://www.linkedin.com/in/kristijan-kostovski-10ba44251/'}
                                />
                            </div>
                            <p className={'text-sm text-neutral-200 my-1 mb-4'}>Junior full-stack developer.</p>

                            <ul className={'ml-8 list-disc'}>
                                <li className={'list-disc text-neutral-200 my-2'}>
                                    Worked on the import of data
                                    from <RedirectAnchor
                                    url={'https://developers.google.com/maps/documentation/places/web-service'}
                                    text={'Google Places API'}/> using <RedirectAnchor
                                    url={'https://spring.io/projects/spring-batch'} text={'Spring Batch'}/> with the
                                    <a className={'italic text-slate-400 hover:text-slate-600'}> pipe-and-filter
                                        architecture</a>.<br/>
                                    Also helped with deployment, requirements specification and implementation of other
                                    services.
                                </li>
                                <li className={'text-neutral-200 my-2'}>
                                    Developer with ambitions to grow his skills and experience to the likes of Bjarne
                                    Stroustrup.<br/>
                                    Honing his skills each day by deep-diving into open-source code bases.
                                </li>
                                <li className={'text-neutral-200 my-3'}>
                                    Previous difficult projects:
                                    <ul className={'list-disc list-inside pl-5 mt-2'}>
                                        <li><RedirectAnchor url={'https://github.com/KixerRealm/Yu-Gi-Oh-Backend'}
                                                            text={'Yu-Gi-Oh!'}/></li>
                                        <li><RedirectAnchor
                                            url={'https://github.com/DrejSamurai/Carbon-2D-Game-Project'}
                                            text={'Carbon'}/></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div className={'h-full'}>
                            <Image className={'rounded-full border-neutral-600'} src={'/developers/kiko.png'}
                                   width={280}
                                   height={280} alt={''}/>
                        </div>
                    </div>

                    <hr className={"w-full my-6 border-neutral-200 sm:mx-auto dark:border-neutral-700 lg:my-8"}/>

                    {/* Anche */}
                    <div className={'flex flex-row justify-center w-full h-full mt-4 text-left'}>
                        <div className={'h-full'}>
                            <Image className={'rounded-full border-neutral-600'}
                                   src={'/developers/anche.png'} width={280}
                                   height={280} alt={''}/>

                        </div>
                        <div className={'ml-16 w-1/2'}>
                            <div className={'flex flex-row italic font-semibold text-xl text-neutral-200'}>
                                <h2>Андреј Мишевски (Анче)</h2>
                                <Socials
                                    github={'https://github.com/DrejSamurai'}
                                    mail={'andrej.mishevski@gmail.com'}
                                    linkedIn={'https://www.linkedin.com/in/andrej-mishevski-81a82024b/'}
                                />
                            </div>

                            <p className={'text-sm text-neutral-200 my-1 mb-4'}>Junior full-stack developer.</p>

                            <ul className={'ml-8 list-disc'}>
                                <li className={'list-disc text-neutral-200 my-2'}>
                                    Implemented back-end services relating to leaderboards, authentication,<br/>
                                    user profiles, and any other relating services
                                    using <RedirectAnchor url={'https://spring.io/projects/spring-boot'}
                                                          text={'Spring Boot'}/>.
                                    Also helped with the design of the UI and icons.
                                </li>
                                <li className={'text-neutral-200 my-2'}>
                                    Hardware & game development enthusiast, working towards creating the new World of
                                    Warcraft.
                                </li>
                                <li className={'text-neutral-200 my-3'}>
                                    Previous difficult projects:
                                    <ul className={'list-disc list-inside pl-5 mt-2'}>
                                        <li><RedirectAnchor url={'https://github.com/KixerRealm/Yu-Gi-Oh-Backend'}
                                                            text={'Yu-Gi-Oh!'}/></li>
                                        <li><RedirectAnchor
                                            url={'https://github.com/DrejSamurai/Carbon-2D-Game-Project'}
                                            text={'Carbon'}/></li>
                                    </ul>
                                </li>

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
