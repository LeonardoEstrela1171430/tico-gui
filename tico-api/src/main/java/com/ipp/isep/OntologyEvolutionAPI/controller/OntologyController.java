package com.ipp.isep.OntologyEvolutionAPI.controller;

import com.ipp.isep.OntologyEvolutionAPI.dto.CreateOntologyDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetEvolutionaryActionsDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologiesDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologyDTO;
import com.ipp.isep.OntologyEvolutionAPI.service.FusekiService;
import com.ipp.isep.OntologyEvolutionAPI.service.TicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class OntologyController {

    @Autowired
    public FusekiService fusekiService;
    @Autowired
    public TicoService ticoService;

    @CrossOrigin(origins = "*")
    @GetMapping("/ontologies")
    public GetOntologiesDTO getOntologies() throws IOException {
        return fusekiService.getOntologies();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/ontologies/{IRI}/{v1}/{v2}")
    public GetOntologyDTO getOntologyByID(@PathVariable String IRI, @PathVariable String v1, @PathVariable String v2) throws IOException {
        GetOntologyDTO dto = new GetOntologyDTO();
        String original = fusekiService.getOntologyViewerVersionByIRI(IRI, v1);
        String updated = fusekiService.getOntologyViewerVersionByIRI(IRI, v2);

        dto.setOriginalOntology(original);
        dto.setViewerVersion(updated);
        return dto;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/ontologies/{IRI}")
    public GetOntologyDTO createOntology(@PathVariable String IRI, @RequestBody CreateOntologyDTO file) throws IOException, URISyntaxException, InterruptedException {
        GetOntologyDTO aux = ticoService.createOntology(IRI, file);
        return aux;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/evolactions")
    public String getEvolutionaryActions(@RequestBody GetEvolutionaryActionsDTO file) throws IOException {
        String result = ticoService.getEvolutionaryActions(file);
        return result;
    }

}