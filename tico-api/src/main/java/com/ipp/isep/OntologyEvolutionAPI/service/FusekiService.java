package com.ipp.isep.OntologyEvolutionAPI.service;

import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologiesDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologyDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.OntologyDTO;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class FusekiService {
    public String fusekiURL;
    public String ontologiesDataset;
    public String instancesDataset;
    public String fusekiUser;
    public String fusekiPassword;
    public String viewerDataset;

    public FusekiService(@Value("${FUSEKI_URL}") String fusekiURL,
                             @Value("${ONTOLOGIES_DATASET}") String ontologiesDataset,
                             @Value("${INSTANCES_DATASET}") String instancesDataset,
                             @Value("${VIEWER_DATASET}") String viewerDataset,
                             @Value("${FUSEKI_USER}") String fusekiUser,
                             @Value("${FUSEKI_PASSWORD}") String fusekiPassword) {
        this.fusekiURL = fusekiURL;
        this.ontologiesDataset =ontologiesDataset;
        this.instancesDataset = instancesDataset;
        this.fusekiUser = fusekiUser;
        this.fusekiPassword = fusekiPassword;
        this.viewerDataset = viewerDataset;
    }

    public void createOntology(String IRI, String originalOntology, String viewerVersion){
        // Insert ontology
        RDFConnection conn0 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .updateEndpoint(ontologiesDataset)
                .build();

        String auxOriginal = StringEscapeUtils.escapeJava(originalOntology); ;

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

        String auxViewer = StringEscapeUtils.escapeJava(viewerVersion); ;

        String queryViewerText = "PREFIX dcterms: <prefix1> \n" +
                "INSERT DATA { <" + IRI + "> <version0> \"" + auxViewer + "\" }";

        UpdateRequest queryViewer = UpdateFactory.create(queryViewerText);

        conn1.update(queryViewer);
        conn1.close();

    }

    public void createOntologyVersion(String IRI, String originalOntology, String viewerVersion){
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
            String auxOriginal = StringEscapeUtils.escapeJava(originalOntology);
            String auxViewer = StringEscapeUtils.escapeJava(viewerVersion);

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

    public GetOntologyDTO getOntologyByIRIAndVersion(String IRI, String version){
        GetOntologyDTO dto = new GetOntologyDTO();
        String versionText = version;

        dto.setIri(IRI);
        dto.setVersion(version);

        RDFConnection conn0 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .queryEndpoint(ontologiesDataset)
                .build();

        RDFConnection conn1 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .queryEndpoint(viewerDataset)
                .updateEndpoint(viewerDataset)
                .build();

        String queryTextOriginal = "SELECT ?s ?p ?o WHERE { ?s ?p ?o FILTER( CONTAINS(str(?s) , \"" + IRI + "\" ) && CONTAINS(str(?p) , \"" + versionText + "\" ) ) }";
        conn0.querySelect(queryTextOriginal, (qs) -> {
            dto.setOriginalOntology(qs.getLiteral("o").getString());
        });

        String queryTextViewer = "SELECT ?s ?p ?o WHERE { ?s ?p ?o FILTER( CONTAINS(str(?s) , \"" + IRI + "\" ) && CONTAINS(str(?p) , \"" + versionText + "\" ) ) }";
        conn1.querySelect(queryTextViewer, (qs) -> {
            dto.setViewerVersion(qs.getLiteral("o").getString());
        });

        return dto;
    }

    public GetOntologiesDTO getOntologies(){
        GetOntologiesDTO resultList = new GetOntologiesDTO();
        List<OntologyDTO> auxList = new ArrayList<>();
        RDFConnection conn0 = RDFConnectionRemote.newBuilder()
                .destination(fusekiURL)
                .queryEndpoint(ontologiesDataset)
                .build();

        String queryTextOriginal = "SELECT DISTINCT (str(?s) as ?iri) WHERE { ?s ?p ?o }";

        QueryExecution qExec = conn0.query(queryTextOriginal);
        ResultSet rs = qExec.execSelect() ;
        while(rs.hasNext()) {
            QuerySolution qs = rs.next() ;
            String content = qs.getLiteral("iri").toString() ;
            String[] auxArray = content.split("/");
            String actualContent = auxArray[auxArray.length - 1]; //this is the IRI, or the name of the ontology
            String queryVersions = "SELECT DISTINCT (str(?p) as ?version) WHERE { ?s ?p ?o FILTER (CONTAINS (str(?s), \"" + actualContent + "\" ))}";
            QueryExecution qExecVersions = conn0.query(queryVersions);
            ResultSet rs2 = qExecVersions.execSelect();
            List<String> versionList = new ArrayList<>();
            while(rs2.hasNext()) {
                QuerySolution qsversion = rs2.next() ;
                String contentVersion = qsversion.getLiteral("version").toString() ;
                String[] auxArrayVersion = contentVersion.split("/");
                String actualContentVersion = auxArrayVersion[auxArray.length - 1];
                versionList.add(actualContentVersion);
            }
            OntologyDTO newOntology = new OntologyDTO(actualContent, versionList.toArray(String[]::new));
            auxList.add(newOntology);
        }
        resultList.setOntologies(auxList.toArray(OntologyDTO[]::new));
        qExec.close() ;
        conn0.close() ;
        return resultList;
    }
}
