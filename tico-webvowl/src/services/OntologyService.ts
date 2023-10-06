import SimpleOntologyDTO from "../domain/dto/SimpleOntologyDTO";
import axios from "axios";

export default class OntologyService {
    
    public static async getOntologies(): Promise<SimpleOntologyDTO[]> {
        return axios.get("http://localhost:8081/ontologies"
        ).then(function (response) {
            const actualReturn = response.data;
            return actualReturn.ontologies;
        })
    }
}