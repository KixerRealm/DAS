import Link from "next/link";
import {executeLogin, LoginRequest} from "../hooks/useLogin";
import {useMutation} from "@tanstack/react-query";
import {useRouter} from "next/router";
import {userAtom} from "../components/user-nav-bar";
import {useAtom} from "jotai";
import {useUpdateAtom} from "jotai/utils";
import {useIdentify} from "../hooks/useIdentify";
import {User} from "./api/oauth/login";
import {useState} from "react";

export default function Login() {

    const updateUser = useUpdateAtom(userAtom);
    const router = useRouter();
    const [user, setUser] = useState<User>({});
    const identity = useIdentify(async (data: User) => {
        user.email = data?.email;
        user.username = data?.username;
        user.profilePictureUrl = data?.profilePictureUrl;

        updateUser(user);
        const {returnPath} = router.query;
        if (returnPath != undefined) {
            await router.push(returnPath as string);
            return;
        }
        await router.push("/");
    });

    const identityMutate = identity.mutate;

    const {mutate, isLoading, error, isError} = useMutation({
        mutationFn: (event: any) => {
            event.preventDefault();
            const data: LoginRequest = new LoginRequest();
            data.username = event.target.username.value;
            data.password = event.target.password.value;
            return executeLogin(data);
        },
        onSuccess: async data => {
            setUser(prevState => ({
                ...prevState,


            }));

            user.access_token = data.access_token;
            user.refresh_token = data.refresh_token;

            identityMutate(data?.access_token ?? '');
        }
    });


    return (
        <div className={'grid w-full min-h-screen place-items-center'} style={{
            background: 'url(/backgrounds/sn7.jpg) no-repeat center center fixed',
            backgroundPositionY: '100%',
            objectFit: 'scale-down'
        }}>
            <div className={"relative w-full max-w-md h-full md:h-auto"}>
                <div className={"relative rounded-lg shadow bg-neutral-900"}>
                    <div className={"py-6 px-6 lg:px-8"}>
                        <h3 className={"mb-4 text-xl font-medium text-white"}>
                            Sign in
                        </h3>

                        <form className={"space-y-6"} onSubmit={mutate}>

                            <div>
                                <label htmlFor={"username"}
                                       className={"block mb-2 text-sm font-medium text-white"}>Username</label>
                                <input type={"username"} name={"username"} id={"username"}
                                       className={"border text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-neutral-800 border-neutral-500 placeholder-neutral-500 text-neutral-200"}
                                       placeholder={"name@company.com"} required/>
                            </div>
                            <div>
                                <label htmlFor={"password"}
                                       className={"block mb-2 text-sm font-medium text-white"}>Password</label>
                                <input type={"password"} name={"password"} id={"password"} placeholder={"••••••••"}
                                       className={"border text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-neutral-800 border-neutral-500 placeholder-neutral-500 text-neutral-200"}
                                       required/>
                            </div>
                            {error != null && error instanceof Error ?
                                <h1 className={"text-red-500 text-sm text-left mb-4"}>{error.message}</h1> :
                                <div className={'mb-4'}/>
                            }
                            <div className={"flex justify-between"}>
                                <span className={"text-sm hover:underline text-blue-600"}>
                                    Lost Password?
                                </span>
                            </div>
                            <button type={"submit"}
                                    className={"w-full text-white focus:ring-4 focus:outline-none font-medium rounded-lg text-sm px-5 py-2.5 text-center bg-green-700 hover:bg-green-800 focus:ring-blue-800"}>
                                Login to your account
                            </button>

                            <div className={"text-sm font-medium text-gray-300"}>
                                Not registered?{' '}
                                <Link href={"/register"}>
                                <span
                                    className={"hover:underline text-blue-600"}>
                                    Create account
                                </span>
                                </Link>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    );
}


