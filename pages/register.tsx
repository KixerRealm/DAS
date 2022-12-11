import Link from "next/link";
import {useRouter} from "next/router";
import {useMutation} from "@tanstack/react-query";
import {executeRegister, UserRegistration} from "../hooks/useRegister";

export default function Register() {

    const router = useRouter();

    const {mutate, error} = useMutation({
        mutationFn: (event: any) => {
            event.preventDefault();

            const data: UserRegistration = {
                email: event.target.email.value,
                displayName: event.target.displayName.value,
                password: event.target.password.value,
                confirm: event.target.confirm.value
            };
            return executeRegister(data);
        },
        onSuccess: async _ => {
            await router.push("/login");
        }
    })

    return (
        <div className={'grid w-full min-h-screen place-items-center'} style={{
            background: 'url(/backgrounds/sn7.jpg) no-repeat center center fixed',
            backgroundPositionY: '100%',
            objectFit: 'scale-down'
        }}>
            <div className={"relative w-full max-w-md h-full md:h-auto"}>
                <div className={"relative rounded-lg shadow bg-neutral-900"}>
                    <div className={"py-6 px-6 lg:px-8"}>
                        <h3 className={"mb-4 text-xl font-medium text-white"}>Register</h3>

                        <form className={"space-y-6"} onSubmit={mutate}>

                            <div>
                                <label htmlFor={"displayName"}
                                       className={"block mb-2 text-sm font-medium text-white"}>Display Name</label>
                                <input type={"text"} name={"displayName"} id={"displayName"}
                                       className={"border text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-neutral-800 border-neutral-500 placeholder-neutral-500 text-neutral-200"}
                                       placeholder={"DenjiKun69"} required/>
                            </div>

                            <div>
                                <label htmlFor={"email"}
                                       className={"block mb-2 text-sm font-medium text-white"}>Email</label>
                                <input type={"email"} name={"email"} id={"email"}
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

                            <div>
                                <label htmlFor={"confirm"}
                                       className={"block mb-2 text-sm font-medium text-white"}>Confirm Password</label>
                                <input type={"password"} name={"confirm"} id={"confirm"} placeholder={"••••••••"}
                                       className={"border text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 bg-neutral-800 border-neutral-500 placeholder-neutral-500 text-neutral-200"}
                                       required/>
                            </div>

                            {error != null && error instanceof Error ?
                                <h1 className={"text-red-500 text-sm text-left mb-4"}>{error.message}</h1> : <div className={'mb-4'}/>
                            }

                            <button type={"submit"}
                                    className={"w-full text-white focus:ring-4 focus:outline-none font-medium rounded-lg text-sm px-5 py-2.5 text-center bg-green-700 hover:bg-green-800 focus:ring-blue-800"}>
                                Create an account
                            </button>

                            <div className={"text-sm font-medium text-gray-300"}>
                                Already have an account?{' '}
                                <Link href={"/login"}>
                                <span
                                    className={"hover:underline text-blue-600"}>
                                    Login
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
