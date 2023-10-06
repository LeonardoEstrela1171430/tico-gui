import { Heading } from "@chakra-ui/react";
import { Link } from "react-router-dom";

function Home() {
  
  return (
    <div className="base-ui workspace">
        <Heading as='h1' size='4xl' noOfLines={1}>TICO-GUI</Heading>
        <br></br>
        <Link 
            to="/load"
        >
            Load Ontology
        </Link>
        <br></br>
        <Link 
            to="/import"
        >
            Import Ontology
        </Link>
    </div>
  );
}

export default Home;
