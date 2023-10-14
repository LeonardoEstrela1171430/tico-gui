import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import OntologyService from '../../../../services/OntologyService';
import SimpleOntologyDTO from "../../../../domain/dto/SimpleOntologyDTO";
import { Button, Heading, Select } from '@chakra-ui/react'
import LoadingPage from "../../modules/loadingPage";

function ChooseOntology() {
    const [ontologies, setOntologies] = useState<SimpleOntologyDTO[]>();
    const [chosenOntology, setChosenOntology] = useState<SimpleOntologyDTO>();
    const [isLoading, setIsLoading] = useState(false);    
    const navigate = useNavigate();
      
    useEffect(() => {
        OntologyService.getOntologies().then(content => {
            setOntologies(content);
        });
/*         setOntologies([
            new SimpleOntologyDTO("Ontology1"),
            new SimpleOntologyDTO("Ontology2"),
            new SimpleOntologyDTO("Ontology3"),
            new SimpleOntologyDTO("Ontology4"),
            new SimpleOntologyDTO("Ontology5"),
            new SimpleOntologyDTO("Ontology6"),
        ]); */
    }, [])

    if(!ontologies){
        return null
    }

    const submit = () => {
        setIsLoading(true);

        if (chosenOntology) {
            OntologyService.getOntology(chosenOntology?.iri).then(result => {
                setIsLoading(false);
                navigate("/viewer", {state: {iri: result.iri, version: result.version, originalOntology: result.originalOntology, viewerVersion: result.viewerVersion}});
            }).catch(error => {
                setIsLoading(false);
                alert("Could not fetch the Ontology. Please try again later.");
                console.log(error);
            })
        }else {
            setIsLoading(false);
            alert("You must first select one of the Ontologies.");
        }
    }

    return (
        <>
            <LoadingPage show={isLoading}/>
            <div className="chooseOntology base-ui">
                <Heading> Choose an Ontology </Heading>
                <Select placeholder='Select option'>
                    {ontologies.map((onto, index) => {
                        return (
                            <option onClick={() => setChosenOntology(onto)} value={index}>{onto.iri}</option>
                        )
                    })}
                </Select>
                {chosenOntology &&
                    <Button colorScheme='blue' onClick={() => submit()}>Button</Button>
                }
            </div>
        </>
    );
  }
  
  export default ChooseOntology;
  