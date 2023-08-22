package com.ipp.isep.OntologyEvolutionAPI.service;

import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Owl2VowlService {
    public String fusekiURL;
    public String ontologiesDataset;
    public String instancesDataset;
    public String frontendPort;
    public String fusekiUser;
    public String fusekiPassword;

    public Owl2VowlService(@Value("${FUSEKI_URL}") String fusekiURL,
                           @Value("${ONTOLOGIES_DATASET}") String ontologiesDataset,
                           @Value("${INSTANCES_DATASET}") String instancesDataset,
                           @Value("${FRONTEND_PORT}") String frontendPort,
                           @Value("${FUSEKI_USER}") String fusekiUser,
                           @Value("${FUSEKI_PASSWORD}") String fusekiPassword) {
        this.fusekiURL = fusekiURL;
        this.ontologiesDataset =ontologiesDataset;
        this.instancesDataset = instancesDataset;
        this.frontendPort = frontendPort;
        this.fusekiUser = fusekiUser;
        this.fusekiPassword = fusekiPassword;
        System.out.println("Jerry");
    }

    public void createOntology(String IRI, String ontologyContent){
        RDFConnection conn0 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .queryEndpoint(ontologiesDataset)
                .build();


        UpdateRequest query = UpdateFactory.create("PREFIX dcterms: <prefix1> \n" +
                "INSERT DATA { GRAPH <graphteste> { <teste> <teste1>" + ontologyContent + " } }");

        conn0.update(query);
        conn0.close();
    }

}
