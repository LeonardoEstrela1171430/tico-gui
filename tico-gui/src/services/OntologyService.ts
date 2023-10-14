import SimpleOntologyDTO from "../domain/dto/SimpleOntologyDTO";
import axios from "axios";
import EvolutionaryActionDTO from "../domain/dto/EvolutionaryActionDTO";
import OntologyDTO from "../domain/dto/OntologyDTO";

export default class OntologyService {
    
    public static async getOntologies(): Promise<SimpleOntologyDTO[]> {
        return axios.get("http://localhost:8081/ontologies"
        ).then(function (response) {
            const actualReturn = response.data;
            return actualReturn.ontologies;
        })
    }

    public static async getOntology(name: string): Promise<OntologyDTO> {
      return axios.get("http://localhost:8081/ontologies/" + name + "/version0/version1"
      ).then(function (response) {
          const actualReturn = response.data;
          return actualReturn;
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

    public static async executeActions(
      name: string,
      ontology: string,
      instances: string,
      actions: string
    ): Promise<OntologyDTO> {
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

