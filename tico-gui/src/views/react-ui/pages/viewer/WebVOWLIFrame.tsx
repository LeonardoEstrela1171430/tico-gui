import React from "react";
import { useEffect, useRef } from "react";

interface WebVOWLProps {
    simplifiedVersion: boolean;
    ontology: string;
    iri: string;
    version: string;
    large?: boolean;
}

function WebVOWLIFrame ({simplifiedVersion, ontology, iri, version, large}: WebVOWLProps) {
    const iframeRef: React.RefObject<HTMLIFrameElement> = React.createRef();

    useEffect(() => {
        iframeRef.current!.contentWindow!.addEventListener('DOMContentLoaded', (event) => {
            // @ts-expect-error: Juro Joca
            iframeRef.current!.contentWindow!.startWebVOWL(ontology, iri, version);
          }); 
    }, []);
    
    return (
        <iframe 
            className={large ? "webvowl-iframe-large" : "webvowl-iframe"}
            id="iframe1"
            ref={iframeRef}
            src="/webvowl/index.html"
        />
    );
        
}

export default WebVOWLIFrame