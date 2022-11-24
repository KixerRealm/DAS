interface RedirectAnchorParameters {
    url: string;
    text: string;
}

export default function RedirectAnchor(params: RedirectAnchorParameters) {

    return (
        <a href={params.url}
           className={`group transition-all duration-1000 ease-in-out`}>
            <span
                className={'italic text-blue-400 hover:text-blue-500 bg-left-bottom ' +
                'bg-gradient-to-r from-blue-400 to-blue-400 bg-[length:0%_2px] bg-no-repeat' +
                ' group-hover:bg-[length:100%_2px] transition-all duration-700 ease-out'}>
                {params.text}
               </span>
        </a>
    );
}
