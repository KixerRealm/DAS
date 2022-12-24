import React, {useRef, useEffect} from 'react';
import PropTypes from 'prop-types';
import jdenticon from 'jdenticon/standalone';

type IdenticonProperties = {
    size: string;
    value: string;
};

export default function Identicon(props: IdenticonProperties) {
    const icon = useRef<SVGSVGElement>(null);
    useEffect(() => {
        if (icon.current == null) {
            return;
        }
        jdenticon.update(icon.current, props.value);
    }, [props.value]);

    return (
        <div>
            <svg data-jdenticon-value={props.value} height={props.size} ref={icon} width={props.size}/>
        </div>
    );
}

