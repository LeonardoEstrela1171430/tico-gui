package com.ipp.isep.OntologyEvolutionAPI.dto;

public class GetOntologyDTO {
    public String iri;
    public String version;
    public String originalOntology;
    public String viewerVersion;

    public GetOntologyDTO(String iri, String version, String originalOntology, String viewerVersion){
        this.iri = iri;
        this.version = version;
        this.originalOntology = originalOntology;
        this.viewerVersion = viewerVersion;
    }

    public GetOntologyDTO(){
    }

    public String getIri() {
        return iri;
    }

    public void setIri(String iri) {
        this.iri = iri;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOriginalOntology() {
        return originalOntology;
    }

    public void setOriginalOntology(String originalOntology) {
        this.originalOntology = originalOntology;
    }

    public String getViewerVersion() {
        return viewerVersion;
    }

    public void setViewerVersion(String viewerVersion) {
        this.viewerVersion = viewerVersion;
    }
}
