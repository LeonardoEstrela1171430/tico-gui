package com.ipp.isep.OntologyEvolutionAPI.service;

import com.ipp.isep.OntologyEvolutionAPI.dto.CreateOntologyDTO;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionRemote;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateRequest;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class Owl2VowlService {
    public String owl2vowlURL;
    private HttpClient httpClient = HttpClient.newHttpClient();

    public Owl2VowlService(@Value("${OWL2VOWL_URL}") String owl2vowlURL) {
        this.owl2vowlURL = owl2vowlURL;
    }

    public String getServerTimeStamp() throws IOException, InterruptedException, URISyntaxException {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(owl2vowlURL + "/serverTimeStamp"))
                    .version(HttpClient.Version.HTTP_1_1)
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return httpResponse.body();
    }

    public String getViewerVersion(String ontology) throws IOException, InterruptedException, URISyntaxException {
        String timeStamp = getServerTimeStamp();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(owl2vowlURL + "/directInput?sessionId=" + timeStamp))
                .version(HttpClient.Version.HTTP_1_1)
                .header("Content-Type", "application/text")
                .POST(HttpRequest.BodyPublishers.ofString(ontology))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return httpResponse.body();
    }
}
