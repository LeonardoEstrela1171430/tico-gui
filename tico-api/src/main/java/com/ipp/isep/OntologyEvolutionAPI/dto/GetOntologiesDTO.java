package com.ipp.isep.OntologyEvolutionAPI.dto;

public class GetOntologiesDTO {
    public OntologyDTO[] ontologies;

    public OntologyDTO[] getOntologies() {
        return ontologies;
    }

    public void setOntologies(OntologyDTO[] ontologies) {
        this.ontologies = ontologies;
    }
}
