package com.ipp.isep.OntologyEvolutionAPI.service;

import com.ipp.isep.OntologyEvolutionAPI.dto.CreateOntologyDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetEvolutionaryActionsDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologyDTO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class OntologyService {
    public String ticoURL;
    public String frontendPort;
    public String owl2vowlURL;

    @Autowired
    private Owl2VowlService owl2VowlService;
    @Autowired
    private FusekiService fusekiService;

    public OntologyService(@Value("${FRONTEND_PORT}") String frontendPort,
                         @Value("${TICO_URL}") String ticoURL,
                         @Value("${OWL2VOWL_URL}") String owl2vowlURL) {
        this.ticoURL = ticoURL;
        this.owl2vowlURL =owl2vowlURL;
        this.frontendPort = frontendPort;
    }

    public String getEvolutionaryActions(GetEvolutionaryActionsDTO ontologyContent) {
        try{
            HttpClient httpClient = HttpClient.newHttpClient();
            JSONObject json = new JSONObject();
            json.put("ontology", ontologyContent.ontology);
            json.put("instance", ontologyContent.instance);

            String inputString = json.toString();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(ticoURL + "/evolactions"))
                    .version(HttpClient.Version.HTTP_1_1)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(inputString))
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
        } catch (URISyntaxException | InterruptedException | IOException e) {
            e.printStackTrace();
        }

        return "[]";
    }

    public GetOntologyDTO createOntology(String IRI, CreateOntologyDTO ontologyContent) throws IOException, InterruptedException, URISyntaxException {
        // get new version from TICO
        HttpClient httpClient = HttpClient.newHttpClient();
        JSONObject json = new JSONObject();
        json.put("ontology", ontologyContent.ontology);
        json.put("instance", ontologyContent.instance);
        json.put("actions", ontologyContent.actions);

        String inputString = json.toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(ticoURL + "/execute"))
                .version(HttpClient.Version.HTTP_1_1)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputString))
                .build();

        HttpResponse<String> executeResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String executeResult = executeResponse.body(); //this is the new version!
        //Now we need to get the viewer version, and save both to Fuseki
        //Get viewer version
        String viewerVersionOriginal = owl2VowlService.getViewerVersion(ontologyContent.ontology);
        String viewerVersion = owl2VowlService.getViewerVersion(executeResult);
        //Save to Fuseki
        fusekiService.createOntology(IRI, ontologyContent.ontology, viewerVersionOriginal);
        fusekiService.createOntologyVersion(IRI, executeResult, viewerVersion);
        // if succeeds, save both versions and return new version
        return new GetOntologyDTO(IRI, "2", viewerVersionOriginal, viewerVersion);
    }
}
