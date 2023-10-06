package com.ipp.isep.OntologyEvolutionAPI.controller;

import com.ipp.isep.OntologyEvolutionAPI.dto.CreateOntologyDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetEvolutionaryActionsDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologiesDTO;
import com.ipp.isep.OntologyEvolutionAPI.dto.GetOntologyDTO;
import com.ipp.isep.OntologyEvolutionAPI.service.FusekiService;
import com.ipp.isep.OntologyEvolutionAPI.service.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class OntologyController {

    @Autowired
    public FusekiService fusekiService;
    @Autowired
    public OntologyService ontologyService;

    @CrossOrigin(origins = "*")
    @GetMapping("/ontologies")
    public GetOntologiesDTO getOntologies() throws IOException {
        return fusekiService.getOntologies();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/ontologies/{IRI}/{version}")
    public GetOntologyDTO getOntologyByID(@PathVariable String IRI, @PathVariable String version) throws IOException {
        return fusekiService.getOntologyByIRIAndVersion(IRI, version);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/ontologies/{IRI}")
    public GetOntologyDTO createOntology(@PathVariable String IRI, @RequestBody CreateOntologyDTO file) throws IOException, URISyntaxException, InterruptedException {
        GetOntologyDTO aux = ontologyService.createOntology(IRI, file);
        return aux;
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/ontologies/{IRI}")
    public String createOntologyVersion(@PathVariable String IRI, @RequestBody CreateOntologyDTO file) throws IOException {
        //ontologyService.createOntologyVersion(IRI, file);
        return "Success";
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/evolactions")
    public String getEvolutionaryActions(@RequestBody GetEvolutionaryActionsDTO file) throws IOException {
        String result = ontologyService.getEvolutionaryActions(file);
        return result;
    }

}