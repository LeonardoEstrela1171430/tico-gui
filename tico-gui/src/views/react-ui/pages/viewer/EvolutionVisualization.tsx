import { useLocation, useNavigate } from "react-router-dom";
import WebVOWLIFrame from "./WebVOWLIFrame";
import { useState } from "react";
import { Center, Grid, GridItem, SimpleGrid, Stack } from "@chakra-ui/react";
import { SearchIcon } from "@chakra-ui/icons";

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

    const content = "{\"_comment\":\"Created with OWL2VOWL (version 0.3.7), http://vowl.visualdataweb.org [Additional Information added by WebVOWL Exporter Version: 1.1.7]\",\"header\":{\"languages\":[\"en\",\"iri-based\"],\"baseIris\":[\"http://www.w3.org/1999/02/22-rdf-syntax-ns\",\"http://www.w3.org/2000/01/rdf-schema\",\"http://xmlns.com/foaf/0.1\"],\"prefixList\":{\"owl\":\"http://www.w3.org/2002/07/owl#\",\"rdf\":\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\",\"wot\":\"http://xmlns.com/wot/0.1/\",\"xsd\":\"http://www.w3.org/2001/XMLSchema#\",\"\":\"http://xmlns.com/foaf/0.1/\",\"dc\":\"http://purl.org/dc/elements/1.1/\",\"xml\":\"http://www.w3.org/XML/1998/namespace\",\"vs\":\"http://www.w3.org/2003/06/sw-vocab-status/ns#\",\"foaf\":\"http://xmlns.com/foaf/0.1/\",\"rdfs\":\"http://www.w3.org/2000/01/rdf-schema#\"},\"title\":{\"en\":\"New ontology\"},\"iri\":\"http://visualdataweb.org/newOntology/\",\"description\":{\"en\":\"New ontology description\"},\"other\":{\"title\":[{\"identifier\":\"title\",\"language\":\"en\",\"value\":\"New ontology\",\"type\":\"label\"}]}},\"namespace\":[],\"settings\":{\"global\":{\"zoom\":\"1.97\",\"translation\":[-350.48,-879.19],\"paused\":false},\"gravity\":{\"classDistance\":200,\"datatypeDistance\":120},\"filter\":{\"checkBox\":[{\"id\":\"datatypeFilterCheckbox\",\"checked\":false},{\"id\":\"objectPropertyFilterCheckbox\",\"checked\":false},{\"id\":\"subclassFilterCheckbox\",\"checked\":false},{\"id\":\"disjointFilterCheckbox\",\"checked\":true},{\"id\":\"setoperatorFilterCheckbox\",\"checked\":false}],\"degreeSliderValue\":\"0\"},\"modes\":{\"checkBox\":[{\"id\":\"pickandpinModuleCheckbox\",\"checked\":false},{\"id\":\"nodescalingModuleCheckbox\",\"checked\":true},{\"id\":\"compactnotationModuleCheckbox\",\"checked\":false},{\"id\":\"colorexternalsModuleCheckbox\",\"checked\":true}],\"colorSwitchState\":false}},\"class\":[{\"id\":\"6\",\"type\":\"rdfs:Literal\"},{\"id\":\"7\",\"type\":\"rdfs:Literal\"},{\"id\":\"2\",\"type\":\"owl:Class\"},{\"id\":\"1\",\"type\":\"owl:Class\"},{\"id\":\"4\",\"type\":\"owl:Class\"}],\"classAttribute\":[{\"id\":\"6\",\"iri\":\"http://www.w3.org/2000/01/rdf-schema#Literal\",\"baseIri\":\"http://www.w3.org/2000/01/rdf-schema\",\"label\":\"Literal\",\"attributes\":[\"datatype\"],\"pos\":[920.51,720.7]},{\"id\":\"7\",\"iri\":\"http://www.w3.org/2000/01/rdf-schema#Literal\",\"baseIri\":\"http://www.w3.org/2000/01/rdf-schema\",\"label\":\"Literal\",\"attributes\":[\"datatype\"],\"pos\":[413.12,210.06]},{\"id\":\"2\",\"iri\":\"http://xmlns.com/foaf/0.1/Class3\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"Class3\",\"iri-based\":\"Class3\",\"en\":\"NewClass\"},\"attributes\":[\"external\"],\"pos\":[476.91,433.38]},{\"id\":\"1\",\"iri\":\"http://xmlns.com/foaf/0.1/Class4\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"Class4\",\"iri-based\":\"Class4\",\"en\":\"NewClass\"},\"attributes\":[\"external\"],\"pos\":[822.24,368.39]},{\"id\":\"4\",\"iri\":\"http://xmlns.com/foaf/0.1/Class1\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"Class1\",\"iri-based\":\"Class1\",\"en\":\"NewClass\"},\"attributes\":[\"external\"],\"pos\":[691.03,699.44]}],\"property\":[{\"id\":\"0\",\"type\":\"owl:ObjectProperty\"},{\"id\":\"3\",\"type\":\"owl:ObjectProperty\"},{\"id\":\"5\",\"type\":\"owl:ObjectProperty\"},{\"id\":\"8\",\"type\":\"owl:DatatypeProperty\"},{\"id\":\"9\",\"type\":\"owl:DatatypeProperty\"}],\"propertyAttribute\":[{\"id\":\"0\",\"iri\":\"http://xmlns.com/foaf/0.1/objectProperty4\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"objectProperty4\",\"iri-based\":\"objectProperty4\",\"en\":\"newObjectProperty\"},\"attributes\":[\"external\",\"object\"],\"domain\":\"1\",\"range\":\"2\",\"pos\":[649.58,400.89]},{\"id\":\"3\",\"iri\":\"http://xmlns.com/foaf/0.1/objectProperty3\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"objectProperty3\",\"iri-based\":\"objectProperty3\",\"en\":\"newObjectProperty\"},\"attributes\":[\"external\",\"object\"],\"domain\":\"4\",\"range\":\"1\",\"pos\":[756.64,533.92]},{\"id\":\"5\",\"iri\":\"http://xmlns.com/foaf/0.1/objectProperty2\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"objectProperty2\",\"iri-based\":\"objectProperty2\",\"en\":\"newObjectProperty\"},\"attributes\":[\"external\",\"object\"],\"domain\":\"2\",\"range\":\"4\",\"pos\":[583.97,566.41]},{\"id\":\"8\",\"iri\":\"http://xmlns.com/foaf/0.1/datatypeProperty1\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"datatypeProperty1\",\"iri-based\":\"datatypeProperty1\",\"en\":\"newDatatypeProperty\"},\"attributes\":[\"external\",\"datatype\"],\"domain\":\"4\",\"range\":\"6\",\"pos\":[817.17,711.12]},{\"id\":\"9\",\"iri\":\"http://xmlns.com/foaf/0.1/datatypeProperty5\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"datatypeProperty5\",\"iri-based\":\"datatypeProperty5\",\"en\":\"newDatatypeProperty\"},\"attributes\":[\"external\",\"datatype\"],\"domain\":\"2\",\"range\":\"7\",\"pos\":[439.58,302.68]}]}";

    const FirstGridContent = (
        <Stack> 
            <Center className="grid-content">
            TODO: adicionar mais informação <SearchIcon style={{alignSelf: "right"}} onClick={() => navigate("/largeviewer", {state: {iri: iri, version: 1, ontology: originalOntology}})}/>


            </Center>
        </Stack>
    )
    

    const SecontGridContent = 
        (<Stack> 
          <Center className="grid-content">
            TODO: adicionar mais informação  <SearchIcon style={{alignSelf: "right"}} onClick={() => navigate("/largeviewer", {state: {iri: iri, version: version, ontology: viewerVersion}})}/>


            </Center>

        </Stack>)
    

    return (
        <Grid 
            h="80vh"
            templateRows='repeat(2, 1fr)'
            templateColumns='repeat(2, 1fr)'
            gap={4}
        >
            <GridItem rowSpan={1} colSpan={1}> {FirstGridContent} </GridItem>
            <GridItem rowSpan={1} colSpan={1}> <WebVOWLIFrame height={"100%"} width="95%" simplifiedVersion={false} ontology={originalOntology} iri={iri} version={"1"}/> </GridItem>
            <GridItem rowSpan={1} colSpan={1}> {SecontGridContent} </GridItem>
            <GridItem rowSpan={1} colSpan={1}>  <WebVOWLIFrame height={"100%"} width="95%" simplifiedVersion={false} ontology={viewerVersion} iri={iri} version={version}/> </GridItem>
            
           
        </Grid >
    );
        
}

export default EvolutionVisualization