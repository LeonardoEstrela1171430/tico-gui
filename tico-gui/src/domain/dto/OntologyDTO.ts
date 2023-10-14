export default class OntologyDTO {
    public iri: string;
    public version: string;
    public originalOntology: string;
    public viewerVersion: string;

    constructor(iri: string, version: string, originalOntology: string, viewerVersion: string){
        this.iri = iri;
        this.version = version;
        this.originalOntology = originalOntology;
        this.viewerVersion = viewerVersion;
    }
}