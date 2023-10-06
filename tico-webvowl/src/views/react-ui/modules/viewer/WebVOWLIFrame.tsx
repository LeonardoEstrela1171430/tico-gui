import React from "react";
import { useEffect, useRef } from "react";

interface WebVOWLProps {
    height: string;
    width: string;
    simplifiedVersion: boolean;
    ontology: string;
    iri: string;
    version: string;
}

function WebVOWLIFrame ({height, width, simplifiedVersion, ontology, iri, version}: WebVOWLProps) {
    const iframeRef: React.RefObject<HTMLIFrameElement> = React.createRef();

    useEffect(() => {
        iframeRef.current!.contentWindow!.addEventListener('DOMContentLoaded', (event) => {
            // @ts-expect-error: Juro Joca
            iframeRef.current!.contentWindow!.startWebVOWL(ontology, iri, version);
          }); 
    }, []);
    
    return (
        <iframe 
            className="webvowl-iframe"
            id="iframe1"
            ref={iframeRef}
            src="/webvowl/index.html"
            height={height}
            width={width}
        />
    );
        
}

export default WebVOWLIFrame