import SimpleOntologyDTO from "../domain/dto/SimpleOntologyDTO";
import axios from "axios";
import EvolutionaryActionDTO from "../views/react-ui/dto/EvolutionaryActionDTO";

export default class OntologyService {
    
    public static async getOntologies(): Promise<SimpleOntologyDTO[]> {
        return axios.get("http://localhost:8081/ontologies"
        ).then(function (response) {
            const actualReturn = response.data;
            return actualReturn.ontologies;
        })
    }

    public static async getEvolutionaryActions(
        ontology: string,
        instances: string
      ): Promise<EvolutionaryActionDTO[]> {
        return axios
          .post(
            "http://localhost:8081/evolactions", {
                ontology: ontology,
                instance: instances
            }
          )
          .then(function (response) {
            const actualReturn = response.data;
            return actualReturn;
          });
    }

    public static async execute(
      name: string,
      ontology: string,
      instances: string,
      actions: string
    ): Promise<any> {
      return axios
        .post(
          "http://localhost:8081/ontologies/" + name, {
              ontology: ontology,
              instance: instances,
              actions: actions
          }
        )
        .then(function (response) {
          const actualReturn = response.data;
          return actualReturn;
        });
  }
}
