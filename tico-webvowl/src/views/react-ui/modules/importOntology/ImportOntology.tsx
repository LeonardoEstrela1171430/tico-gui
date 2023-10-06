import { Button, Heading, Input } from "@chakra-ui/react";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import OntologyService from "../../services/OntologyService";
import LoadingPage from "../loadingPage";

function ImportOntology() {
    const [ontologyName, setOntologyName] = useState<string>("");
    const [ontologyValue, setOntologyValue] = useState<File>();
    const [instancesValue, setInstancesValue] = useState<File>();
    const [isLoading, setIsLoading] = useState(false);    


    const navigate = useNavigate();
    
    const handleSetOntologyFile = (files: any) => {
        if(files){
            setOntologyValue(files[0]);
        }else{
            alert("failed to load file");
        }
    }

    const handleSetInstancesFile = (files: any) => {
        if(files){
            setInstancesValue(files[0]);
        }else{
            alert("failed to load file");
        }
    }

    const submit = async () => {
        
        let ontologyActualValue: any;
        let instanceActualValue: any;

        const reader = new FileReader()
        const reader2 = new FileReader();
        reader.onload = async (e) => { 
            ontologyActualValue = (e.target?.result)
            reader2.onload = async (e2) => { 
                instanceActualValue = (e2.target?.result)
                if(ontologyActualValue && instanceActualValue && ontologyName){
                    setIsLoading(true);
                    const stringifiedOntology = ontologyActualValue;
                    const stringifiedInstance = instanceActualValue;
                    OntologyService.getEvolutionaryActions(stringifiedOntology, stringifiedInstance)
                    .then(response => {
                        navigate("/actions", {state: {ontology: ontologyActualValue, instance: instanceActualValue, actions: response, name: ontologyName}});
                        setIsLoading(false);
                    }).catch(error => {
                        setIsLoading(false);
                        alert("There was an error while processing your data, please contact the IT support team.")
                    })
                }else {
                    setIsLoading(false);
                    alert("Something went wrong, please contact the IT support team.")
                }
              };
          };
          reader.readAsText(ontologyValue!)
        reader2.readAsText(instancesValue!)
    }

    return (
        <>
            <LoadingPage show={isLoading}/>

        <div className="importOntology base-ui">
             <Heading as='h2' size='lg' noOfLines={1}>Insert ontology name and files</Heading>
            <br></br>
            <Input
                placeholder="Insert ontology name"
                size="md"
                type="text"
                value={ontologyName}
                onChange={(e) => setOntologyName(e.target.value)}
            />
            <br></br>
            <br></br>
            <Input
                placeholder="Insert ontology file"
                size="md"
                type="file"
                onChange={(e) => handleSetOntologyFile(e.target.files)}
            />
            <br></br>
            <br></br>
            <Input
                placeholder="Insert instances file"
                size="md"
                type="file"
                onChange={(e) => handleSetInstancesFile(e.target.files)}
            />
            <br></br>
            <br></br>
            <Button
                onClick={() => submit()}
            >
                Submit
            </Button>
        </div>
        </>
    );
  }
  
  export default ImportOntology;
  