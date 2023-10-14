import { useEffect, useState } from "react";
import EvolutionaryActionDTO from "../../../../domain/dto/EvolutionaryActionDTO";
import { Box, Button, Center, Checkbox, Heading, Input } from "@chakra-ui/react";
import { useLocation, useNavigate } from "react-router-dom";
import OntologyService from "../../../../services/OntologyService";
import LoadingPage from "../../modules/loadingPage";
import { DeleteIcon } from "@chakra-ui/icons";
import {v4 as uuidv4} from 'uuid';

interface LocationState {
    ontology: string,
    instance: string,
    actions: EvolutionaryActionDTO[],
    name: string
}

function EditEvolutionaryActions() {

    const location = useLocation();
    const { ontology, instance, name, actions } = location.state as LocationState; // Read values passed on state
    const [isLoading, setIsLoading] = useState(false);    
    const navigate = useNavigate();

    const [evolActions, setEvolActions] = useState<EvolutionaryActionDTO[]>(actions);

    const updateActionValue = (index: number, property: string, newValue: any, arrayIndex?: number) => {
        debugger;
        let auxActions = [...evolActions];
        if(typeof(arrayIndex) === "number" && arrayIndex >= 0){
            auxActions[index][property][arrayIndex] = newValue;
        }else {
            auxActions[index][property] = newValue;
        }
        setEvolActions(auxActions);
    }

    const submit = () => {
        setIsLoading(true);
        const jsonActions = JSON.stringify(evolActions);
        OntologyService.executeActions(name, ontology, instance, jsonActions).then(result => {
            setIsLoading(false);
            debugger;
            navigate("/viewer", {state: {iri: result.iri, version: result.version, originalOntology: result.originalOntology, viewerVersion: result.viewerVersion}});
        }).catch(error => {
            setIsLoading(false);
            alert("Could not execute actions. Please try again later.");
            console.log(error);
        })
    }

    const deletedAction = (index: number) => {
        debugger;
        let auxActions = [...evolActions];
        auxActions.splice(index, 1);
        setEvolActions(auxActions);
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
                evolActions.map((action, index) => {
                    const entries = Object.entries(action).filter(value => value[0] !== "className");
                    return (
                        <Box borderWidth='1px' borderRadius='lg' overflow='hidden' margin={"10px"}>
                            <DeleteIcon className="deleteButton" onClick={() => deletedAction(index)} />
                            <Heading as='h4' size='md' noOfLines={1}>
                                Action type: {action.className}
                            </Heading>
                            <Heading as='h4' size='md' noOfLines={1} className="checkboxWrapper">
                                Properties:
                            </Heading>
                            <br></br>
                            {
                                entries.length > 0 && entries.map(value => {
                                    if(typeof(value[1]) === "number"){
                                        return (
                                            <>
                                                <Center>
                                                    <div className="form-element-left">{value[0]} : </div>
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
                                            <div className="checkboxWrapper">
                                            <Checkbox
                                            
                                                isChecked={value[1]}
                                                onChange={(e) => updateActionValue(index, value[0], !value[1])}
                                            >
                                                {value[0]}
                                            </Checkbox>
                                            </div>
                                        )
                                    } else if (Array.isArray(value[1])){
                                        return(
                                        value[1].map((arrayValue, arrayIndex) => {
                                            debugger;
                                            if(typeof(arrayValue) === "number"){
                                                return (
                                                        <Center>
                                                            <div className="form-element-left">{value[0]} : </div>
                                                            <Input
                                                                    type="number"
                                                                    value={arrayValue}
                                                                    onChange={(e) => updateActionValue(index, value[0], Number(e.target.value), arrayIndex)}
                                                                    placeholder={value[0]}
                                                                />
                                                        </Center>  
                                                )
                                            } else if (typeof(arrayValue) === "boolean"){
                                                return (
                                                    <Checkbox
                                                        isChecked={arrayValue}
                                                        onChange={(e) => updateActionValue(index, value[0], !value[1], arrayIndex)}
                                                    >
                                                        {value[0]}
                                                    </Checkbox>
                                                )
                                            }else if (typeof(arrayValue) === "string"){
                                                return (
                                                    <Center>
                                                        <div className="form-element-left">{value[0]} : </div>
                                                        <Input
                                                                type="text"
                                                                value={arrayValue}
                                                                onChange={(e) => updateActionValue(index, value[0], e.target.value, arrayIndex)}
                                                                placeholder={value[0]}
                                                            />
                                                    </Center>  
                                                );
                                            } else {
                                                return null;
                                            }
                                            
                                        }) )     
                                        
                                    } else if (typeof(value[1]) === "string"){
                                        return (
                                            <Center>
                                                <div className="form-element-left">{value[0]} : </div>
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
  