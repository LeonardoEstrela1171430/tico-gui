import { useLocation, useNavigate } from "react-router-dom";
import WebVOWLIFrame from "./WebVOWLIFrame";
import { useState } from "react";
import { Center, Grid, GridItem, Highlight, SimpleGrid, Stack } from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";
import { Text } from '@chakra-ui/react'

interface LocationState {
    iri: string, 
    version: string,
    originalOntology: string,
    viewerVersion: string
}

function EvolutionVisualization () {
    const location = useLocation();
    const { iri, version, originalOntology, viewerVersion } = location.state as LocationState; // Read values passed on state
    const navigate = useNavigate();

    const [isLoading, setIsLoading] = useState(false);    

    const FirstGridContent =         
    (<Stack className="gridcontent"> 
    <div className="nowrap"><strong>Name :</strong> {iri}</div>
    <div className="nowrap"><strong>Version :</strong> {1}</div>

       <div>To see on a larger screen, click here 

       <SearchIcon style={{alignSelf: "right", marginLeft: "10px"}} onClick={() => navigate("/largeviewer", {state: {iri: iri, version: 1, ontology: originalOntology}})}/>
       </div>
   </Stack>)
    

    const SecontGridContent = 
        (<Stack className="gridcontent"> 
         <div className="nowrap"><strong>Name :</strong> {iri}</div>
         <div className="nowrap"><strong>Version :</strong> {version}</div>

            <div>To see on a larger screen, click here 

            <SearchIcon style={{alignSelf: "right", marginLeft: "10px"}} onClick={() => navigate("/largeviewer", {state: {iri: iri, version: version, ontology: viewerVersion}})}/>
            </div>
        </Stack>)
    

    return (
        <Grid 
            h="80vh"
            templateRows='repeat(2, 1fr)'
            templateColumns='repeat(5, 1fr)'
            gap={4}
        >
            <GridItem rowSpan={1} colSpan={1}> {FirstGridContent} </GridItem>
            <GridItem rowSpan={1} colStart={2} colEnd={6} > <WebVOWLIFrame simplifiedVersion={false} ontology={originalOntology} iri={iri} version={"1"}/> </GridItem>
            <GridItem rowSpan={1} colSpan={1}> {SecontGridContent} </GridItem>
            <GridItem rowSpan={1} colStart={2} colEnd={6} >  <WebVOWLIFrame simplifiedVersion={false} ontology={viewerVersion} iri={iri} version={version}/> </GridItem>
        </Grid >
    );
        
}

export default EvolutionVisualization