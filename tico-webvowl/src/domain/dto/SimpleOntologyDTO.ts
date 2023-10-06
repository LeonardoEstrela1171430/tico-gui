export default class OntologyDTO {
    public iri: string;
    public versions: string[];

    constructor(iri: string, versions: string[]){
        this.iri = iri;
        this.versions = versions;
    }
}