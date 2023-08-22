package com.ipp.isep.OntologyEvolutionAPI.service;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


@Component
public class AdminService {
    private final Logger logger = LoggerFactory.getLogger(AdminService.class);

    @Value("${FUSEKI_URL}")
    public String fusekiURL;
    @Value("${FUSEKI_USER}")
    public String user;
    @Value("${FUSEKI_PASSWORD}")
    public String password;
    @Value("${ONTOLOGIES_DATASET}")
    public String ontologiesDataset;
    @Value("${INSTANCES_DATASET}")
    public String instancesDataset;
    @Value("${VIEWER_DATASET}")
    public String viewerDataset;

    @EventListener(ApplicationReadyEvent.class)
    public void setupFusekiDatasets() throws IOException {
        String auth = user + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.UTF_8));
        String authHeaderValue = "Basic " + new String(encodedAuth);

        URL postOntologiesDataset = new URL(fusekiURL + "/$/datasets?dbName=" + ontologiesDataset + "&dbType=tdb");
        URL postInstancesDataset = new URL(fusekiURL + "/$/datasets?dbName=" + instancesDataset + "&dbType=tdb");
        URL postViewerDataset = new URL(fusekiURL + "/$/datasets?dbName=" + viewerDataset + "&dbType=tdb");

        URL getOntologiesDataset = new URL(fusekiURL + "/$/datasets/" + ontologiesDataset);
        URL getInstancesDataset = new URL(fusekiURL + "/$/datasets/" + instancesDataset);
        URL getVersionsDataset = new URL(fusekiURL + "/$/datasets/" + viewerDataset);

        HttpURLConnection conGetOntologies = (HttpURLConnection) getOntologiesDataset.openConnection();
        conGetOntologies.setRequestProperty("Authorization", authHeaderValue);
        conGetOntologies.setRequestMethod("GET");
        Integer codeOntologies = conGetOntologies.getResponseCode();

        HttpURLConnection conGetInstances = (HttpURLConnection) getInstancesDataset.openConnection();
        conGetInstances.setRequestProperty("Authorization", authHeaderValue);
        conGetInstances.setRequestMethod("GET");
        Integer codeInstances = conGetOntologies.getResponseCode();

        HttpURLConnection conGetViewer = (HttpURLConnection) getVersionsDataset.openConnection();
        conGetViewer.setRequestProperty("Authorization", authHeaderValue);
        conGetViewer.setRequestMethod("GET");
        Integer codeViewer = conGetViewer.getResponseCode();

        if(codeInstances == 500 || codeOntologies == 500 || codeViewer == 500){
            logger.error("An internal Fuseki error has occurred, while trying to fetch the datasets. Check Fuseki's logs in order to better understand the problem.");
        }

        if(codeOntologies == 404){
            HttpURLConnection conPostOntologies = (HttpURLConnection) postOntologiesDataset.openConnection();
            conPostOntologies.setRequestProperty("Authorization", authHeaderValue);
            conPostOntologies.setRequestMethod("POST");
            Integer codeOntologiesPost = conPostOntologies.getResponseCode();
            if(codeOntologiesPost == 200){
                logger.error("Successfully created Ontologies dataset.");
            }else {
                logger.error("An internal Fuseki error has occurred, while trying to create Ontologies dataset. Check Fuseki's logs in order to better understand the problem.");
            }
        }

        if(codeInstances == 404){
            HttpURLConnection conPostInstances = (HttpURLConnection) postInstancesDataset.openConnection();
            conPostInstances.setRequestProperty("Authorization", authHeaderValue);
            conPostInstances.setRequestMethod("POST");
            Integer codeInstancesPost = conPostInstances.getResponseCode();
            if(codeInstancesPost == 200){
                logger.error("Successfully created Instances dataset.");
            }else {
                logger.error("An internal Fuseki error has occurred, while trying to create Instances dataset. Check Fuseki's logs in order to better understand the problem.");
            }
        }

        if(codeViewer == 404){
            HttpURLConnection conPostViewer = (HttpURLConnection) postViewerDataset.openConnection();
            conPostViewer.setRequestProperty("Authorization", authHeaderValue);
            conPostViewer.setRequestMethod("POST");
            Integer codeViewerPost = conPostViewer.getResponseCode();
            if(codeViewerPost == 200){
                logger.error("Successfully created Versions dataset.");
            }else {
                logger.error("An internal Fuseki error has occurred, while trying to create Ontologies dataset. Check Fuseki's logs in order to better understand the problem.");
            }
        }
    }
}
