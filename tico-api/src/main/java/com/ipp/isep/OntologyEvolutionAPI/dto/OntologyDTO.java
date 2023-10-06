package com.ipp.isep.OntologyEvolutionAPI.dto;

public class OntologyDTO {
    public String iri;
    public String[] versions;

    public OntologyDTO(String IRI, String[] versions) {
        this.iri = IRI;
        this.versions = versions;
    }

    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String[] getVersions() {
        return versions;
    }

    public void setVersions(String[] versions) {
        this.versions = versions;
    }
}
