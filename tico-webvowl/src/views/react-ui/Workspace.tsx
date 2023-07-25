import { useSearchParams } from "react-router-dom";
import Wrapper from "../webvowl/Wrapper";

interface WorkSpaceParams {
    id: string
}

function WorkSpace() {
  
  const [searchParams] = useSearchParams();
  const id = searchParams.get("id");
    
    return (
      <div className="WorkSpace">
            { id && 
                <div>
                    Hello there, I am ontology {id}
                </div>
            }
          
          <Wrapper />
      </div>
    );
  }
  
  export default WorkSpace;
  