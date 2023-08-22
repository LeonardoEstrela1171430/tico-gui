package com.ipp.isep.OntologyEvolutionAPI.service;

import com.ipp.isep.OntologyEvolutionAPI.dto.CreateOntologyDTO;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class OntologyService {
    public String fusekiURL;
    public String ontologiesDataset;
    public String instancesDataset;
    public String frontendPort;
    public String fusekiUser;
    public String fusekiPassword;
    public String viewerDataset;

    public OntologyService(@Value("${FUSEKI_URL}") String fusekiURL,
                             @Value("${ONTOLOGIES_DATASET}") String ontologiesDataset,
                             @Value("${INSTANCES_DATASET}") String instancesDataset,
                             @Value("${VIEWER_DATASET}") String viewerDataset,
                             @Value("${FRONTEND_PORT}") String frontendPort,
                             @Value("${FUSEKI_USER}") String fusekiUser,
                             @Value("${FUSEKI_PASSWORD}") String fusekiPassword) {
        this.fusekiURL = fusekiURL;
        this.ontologiesDataset =ontologiesDataset;
        this.instancesDataset = instancesDataset;
        this.frontendPort = frontendPort;
        this.fusekiUser = fusekiUser;
        this.fusekiPassword = fusekiPassword;
        this.viewerDataset = viewerDataset;
    }

    public void createOntology(String IRI, CreateOntologyDTO ontologyContent){
        // Insert ontology
        RDFConnection conn0 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .updateEndpoint(ontologiesDataset)
                .build();

        String auxOriginal = StringEscapeUtils.escapeJava(ontologyContent.originalOntology); ;

        String queryText = "PREFIX dcterms: <prefix1> \n" +
        "INSERT DATA { <" + IRI + "> <version0> \"" + auxOriginal + "\" }";

        UpdateRequest query = UpdateFactory.create(queryText);

        conn0.update(query);
        conn0.close();

        // Insert viewer version
        RDFConnection conn1 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .updateEndpoint(viewerDataset)
                .build();

        String auxViewer = StringEscapeUtils.escapeJava(ontologyContent.viewerVersion); ;

        String queryViewerText = "PREFIX dcterms: <prefix1> \n" +
                "INSERT DATA { <" + IRI + "> <version0> \"" + auxViewer + "\" }";

        UpdateRequest queryViewer = UpdateFactory.create(queryViewerText);

        conn1.update(queryViewer);
        conn1.close();
    }

    public void createOntologyVersion(String IRI, CreateOntologyDTO ontologyContent){
        AtomicReference<Integer> totalNumber = new AtomicReference<>(0);
        RDFConnection conn0 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .queryEndpoint(ontologiesDataset)
                .updateEndpoint(ontologiesDataset)
                .build();


        RDFConnection conn1 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .queryEndpoint(viewerDataset)
                .updateEndpoint(viewerDataset)
                .build();

        String queryCountText = "SELECT (COUNT(*) as ?TotalNumber) WHERE { ?s ?p ?o FILTER( CONTAINS(str(?s) , \"" + IRI + "\" )) }";

        conn0.querySelect(queryCountText, (qs) -> {
            String totalNumberString = qs.getLiteral("TotalNumber").getString() ;
            totalNumber.set(Integer.parseInt(totalNumberString));
        });

        if (totalNumber.get() > 0){
            String version = "version" + (totalNumber.get());
            String auxOriginal = StringEscapeUtils.escapeJava(ontologyContent.originalOntology);
            String auxViewer = StringEscapeUtils.escapeJava(ontologyContent.viewerVersion);

            String insertContentOriginal = "<" + IRI + "> <" + version + "> \"" + auxOriginal + "\"";
            String insertContentViewer = "<" + IRI + "> <" + version + "> \"" + auxViewer + "\"";

            String queryOriginalText = "PREFIX dcterms: <prefix1> \n" +
                    "INSERT DATA {" + insertContentOriginal + "}";
            String queryViewerText = "PREFIX dcterms: <prefix1> \n" +
                    "INSERT DATA {" + insertContentViewer + "}";

            UpdateRequest queryOriginal = UpdateFactory.create(queryOriginalText);
            UpdateRequest queryViewer = UpdateFactory.create(queryViewerText);

            conn0.update(queryOriginal);
            conn1.update(queryViewer);
        }

        conn0.close();
        conn1.close();
    }
}
