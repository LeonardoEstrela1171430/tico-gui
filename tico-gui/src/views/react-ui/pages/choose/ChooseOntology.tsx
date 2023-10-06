import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import OntologyService from '../../../../services/OntologyService';
import SimpleOntologyDTO from "../../../../domain/dto/SimpleOntologyDTO";
import { Button, Select } from '@chakra-ui/react'

function ChooseOntology() {
    const [ontologies, setOntologies] = useState<SimpleOntologyDTO[]>();
    const [chosenOntology, setChosenOntology] = useState<SimpleOntologyDTO>();
    const [chosenVersion, setChosenVersion] = useState<string>();
      
    useEffect(() => {
        OntologyService.getOntologies().then(content => {
            setOntologies(content);
        });
/*         setOntologies([
            new SimpleOntologyDTO("1", ["1", "2"]),
            new SimpleOntologyDTO("2", ["1"]),
            new SimpleOntologyDTO("3", ["1", "2"]),
            new SimpleOntologyDTO("4", ["1", "2"]),
        ]); */
    }, [])

    if(!ontologies){
        return null
    }

    return (
        <div>
            <Select placeholder='Select option'>
                {ontologies.map((onto, index) => {
                    return (
                        <option onClick={() => setChosenOntology(onto)} value={index}>{onto.iri}</option>
                    )
                })}
            </Select>
            {
                chosenOntology && 
                <Select placeholder='Select option'>
                    {chosenOntology.versions.map((version, index) => {
                        return (
                            <option onClick={() => setChosenVersion(version)} value={index}>{version}</option>
                        )
                    })}
                </Select>
            }
            {chosenOntology && chosenVersion &&
                <Link to={"/workspace/?id="+ chosenOntology.iri + "&version=" + chosenVersion}>
                    <Button colorScheme='blue'>Button</Button>
                </Link>
            }
        </div>
    );
  }
  
  export default ChooseOntology;
  