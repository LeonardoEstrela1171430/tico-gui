import { useState } from "react";
import EvolutionaryActionDTO from "../../dto/EvolutionaryActionDTO";
import { Box, Button, Center, Checkbox, Heading, Input } from "@chakra-ui/react";
import { useLocation, useNavigate } from "react-router-dom";
import OntologyService from "../services/OntologyService";
import LoadingPage from "../../modules/loadingPage";

interface LocationState {
    ontology: string,
    instance: string,
    actions: EvolutionaryActionDTO[],
    name: string
}

function EditEvolutionaryActions() {

    const location = useLocation();
    const { ontology, instance, actions, name } = location.state as LocationState; // Read values passed on state
    const [isLoading, setIsLoading] = useState(false);    
    const navigate = useNavigate();

    const [evolActions, setEvolActions] = useState<EvolutionaryActionDTO[]>(actions);

    const updateActionValue = (index: number, property: string, newValue: any) => {
        let auxActions = [...evolActions];
        auxActions[index][property] = newValue;
        setEvolActions(auxActions);
    }

    const updateActionArrayValue = (index: number, property: string, propertyIndex: number, newValue: any) => {
        let auxActions = [...evolActions];
        auxActions[index][property] = newValue;
        setEvolActions(auxActions);
    }

    const submit = () => {
        setIsLoading(true);
        const jsonActions = JSON.stringify(evolActions);
        OntologyService.execute(name, ontology, instance, jsonActions).then(result => {
            setIsLoading(false);
            debugger;
            navigate("/viewer", {state: {iri: result.iri, version: result.version, originalOntology: result.originalOntology, viewerVersion: result.viewerVersion}});
        }).catch(error => {
            setIsLoading(false);
            alert("Could not execute actions. Please try again later.");
            console.log(error);
        })
    }

    return (
        <>
        <LoadingPage show={isLoading}/>
        <div className="editEvolActions base-ui">

            <Heading as='h2' size="lg" noOfLines={1}>Evolutionary Actions for {name}</Heading>
            <br></br>
            <Button
                onClick={() => submit()}
            >
                Execute
            </Button>
            {
                actions.map((action, index) => {
                    const entries = Object.entries(action).filter(value => value[0] !== "className");
                    return (
                        <Box maxW='lg' borderWidth='1px' borderRadius='lg' overflow='hidden' margin={"5px"}>
                            <Heading as='h4' size='md' noOfLines={1}>
                                Action type: {action.className}
                            </Heading>
                            <Heading as='h4' size='md' noOfLines={1}>
                                Properties:
                            </Heading>
                            <br></br>
                            {
                                entries.length > 0 && entries.map(value => {
                                    if(typeof(value[1]) === "number"){
                                        return (
                                            <>
                                                <Center>
                                                    {value[0]}:
                                                    <Input
                                                            type="number"
                                                            value={value[1]}
                                                            onChange={(e) => updateActionValue(index, value[0], Number(e.target.value))}
                                                            placeholder={value[0]}
                                                        />
                                                </Center>
                                            </>    
                                        )
                                    }else if (typeof(value[1]) === "boolean"){
                                        return (
                                            <Checkbox
                                                isChecked={value[1]}
                                                onChange={(e) => updateActionValue(index, value[0], !value[1])}
                                            >
                                                {value[0]}
                                            </Checkbox>
                                        )
                                    } else if (Array.isArray(value[1])){
                                        //TODO: this probably doesn't work yet
                                        /* value[1].map((arrayValue, index) => {
                                            if(typeof(arrayValue) === "number"){
                                                return (
                                                        <Center>
                                                            {value[0]}:
                                                            <Input
                                                                    type="number"
                                                                    value={value[1]}
                                                                    onChange={(e) => updateActionValue(index, value[0], Number(e.target.value))}
                                                                    placeholder={value[0]}
                                                                />
                                                        </Center>  
                                                )
                                            } else if (arrayValue === "boolean"){
                                                return (
                                                    <Checkbox
                                                        isChecked={arrayValue}
                                                        onChange={(e) => updateActionValue(index, value[0], !value[1])}
                                                    >
                                                        {value[0]}
                                                    </Checkbox>
                                                )
                                            }else if (arrayValue === "string"){
                                                return (
                                                    <Center>
                                                        {value[0]}:
                                                        <Input
                                                                type="text"
                                                                value={value[1]}
                                                                onChange={(e) => updateActionValue(index, value[0], e.target.value)}
                                                                placeholder={value[0]}
                                                            />
                                                    </Center>  
                                                );
                                            } else {
                                                return null;
                                            }
                                            
                                        })      */
                                        return null;
                                    } else if (typeof(value[1]) === "string"){
                                        return (
                                            <Center>
                                                {value[0]}:
                                                <Input
                                                        type="text"
                                                        value={value[1]}
                                                        onChange={(e) => updateActionValue(index, value[0], e.target.value)}
                                                        placeholder={value[0]}
                                                    />
                                            </Center>  
                                        );
                                    } else {
                                        return null;
                                    }
                                })
                            }
                        </Box>
                    )
                })
            }
        </div>
        </>
    );
  }
  
  export default EditEvolutionaryActions;
  