package com.ipp.isep.OntologyEvolutionAPI.controller;

import com.ipp.isep.OntologyEvolutionAPI.service.OntologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class OntologyController {
    String myOntology = "{\"_comment\":\"CreatedwithOWL2VOWL(version0.3.7),http://vowl.visualdataweb.org[AdditionalInformationaddedbyWebVOWLExporterVersion:1.1.7]\",\"header\":{\"languages\":[\"en\",\"iri-based\"],\"baseIris\":[\"http://www.w3.org/1999/02/22-rdf-syntax-ns\",\"http://www.w3.org/2000/01/rdf-schema\",\"http://xmlns.com/foaf/0.1\"],\"prefixList\":{\"owl\":\"http://www.w3.org/2002/07/owl#\",\"rdf\":\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\",\"wot\":\"http://xmlns.com/wot/0.1/\",\"xsd\":\"http://www.w3.org/2001/XMLSchema#\",\"\":\"http://xmlns.com/foaf/0.1/\",\"dc\":\"http://purl.org/dc/elements/1.1/\",\"xml\":\"http://www.w3.org/XML/1998/namespace\",\"vs\":\"http://www.w3.org/2003/06/sw-vocab-status/ns#\",\"foaf\":\"http://xmlns.com/foaf/0.1/\",\"rdfs\":\"http://www.w3.org/2000/01/rdf-schema#\"},\"title\":{\"en\":\"Newontology\"},\"iri\":\"http://visualdataweb.org/newOntology/\",\"description\":{\"en\":\"Newontologydescription\"},\"other\":{\"title\":[{\"identifier\":\"title\",\"language\":\"en\",\"value\":\"Newontology\",\"type\":\"label\"}]}},\"namespace\":[],\"settings\":{\"global\":{\"zoom\":\"1.97\",\"translation\":[-350.48,-879.19],\"paused\":false},\"gravity\":{\"classDistance\":200,\"datatypeDistance\":120},\"filter\":{\"checkBox\":[{\"id\":\"datatypeFilterCheckbox\",\"checked\":false},{\"id\":\"objectPropertyFilterCheckbox\",\"checked\":false},{\"id\":\"subclassFilterCheckbox\",\"checked\":false},{\"id\":\"disjointFilterCheckbox\",\"checked\":true},{\"id\":\"setoperatorFilterCheckbox\",\"checked\":false}],\"degreeSliderValue\":\"0\"},\"modes\":{\"checkBox\":[{\"id\":\"pickandpinModuleCheckbox\",\"checked\":false},{\"id\":\"nodescalingModuleCheckbox\",\"checked\":true},{\"id\":\"compactnotationModuleCheckbox\",\"checked\":false},{\"id\":\"colorexternalsModuleCheckbox\",\"checked\":true}],\"colorSwitchState\":false}},\"class\":[{\"id\":\"6\",\"type\":\"rdfs:Literal\"},{\"id\":\"7\",\"type\":\"rdfs:Literal\"},{\"id\":\"2\",\"type\":\"owl:Class\"},{\"id\":\"1\",\"type\":\"owl:Class\"},{\"id\":\"4\",\"type\":\"owl:Class\"}],\"classAttribute\":[{\"id\":\"6\",\"iri\":\"http://www.w3.org/2000/01/rdf-schema#Literal\",\"baseIri\":\"http://www.w3.org/2000/01/rdf-schema\",\"label\":\"Literal\",\"attributes\":[\"datatype\"],\"pos\":[920.51,720.7]},{\"id\":\"7\",\"iri\":\"http://www.w3.org/2000/01/rdf-schema#Literal\",\"baseIri\":\"http://www.w3.org/2000/01/rdf-schema\",\"label\":\"Literal\",\"attributes\":[\"datatype\"],\"pos\":[413.12,210.06]},{\"id\":\"2\",\"iri\":\"http://xmlns.com/foaf/0.1/Class3\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"Class3\",\"iri-based\":\"Class3\",\"en\":\"NewClass\"},\"attributes\":[\"external\"],\"pos\":[476.91,433.38]},{\"id\":\"1\",\"iri\":\"http://xmlns.com/foaf/0.1/Class4\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"Class4\",\"iri-based\":\"Class4\",\"en\":\"NewClass\"},\"attributes\":[\"external\"],\"pos\":[822.24,368.39]},{\"id\":\"4\",\"iri\":\"http://xmlns.com/foaf/0.1/Class1\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"Class1\",\"iri-based\":\"Class1\",\"en\":\"NewClass\"},\"attributes\":[\"external\"],\"pos\":[691.03,699.44]}],\"property\":[{\"id\":\"0\",\"type\":\"owl:ObjectProperty\"},{\"id\":\"3\",\"type\":\"owl:ObjectProperty\"},{\"id\":\"5\",\"type\":\"owl:ObjectProperty\"},{\"id\":\"8\",\"type\":\"owl:DatatypeProperty\"},{\"id\":\"9\",\"type\":\"owl:DatatypeProperty\"}],\"propertyAttribute\":[{\"id\":\"0\",\"iri\":\"http://xmlns.com/foaf/0.1/objectProperty4\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"objectProperty4\",\"iri-based\":\"objectProperty4\",\"en\":\"newObjectProperty\"},\"attributes\":[\"external\",\"object\"],\"domain\":\"1\",\"range\":\"2\",\"pos\":[649.58,400.89]},{\"id\":\"3\",\"iri\":\"http://xmlns.com/foaf/0.1/objectProperty3\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"objectProperty3\",\"iri-based\":\"objectProperty3\",\"en\":\"newObjectProperty\"},\"attributes\":[\"external\",\"object\"],\"domain\":\"4\",\"range\":\"1\",\"pos\":[756.64,533.92]},{\"id\":\"5\",\"iri\":\"http://xmlns.com/foaf/0.1/objectProperty2\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"objectProperty2\",\"iri-based\":\"objectProperty2\",\"en\":\"newObjectProperty\"},\"attributes\":[\"external\",\"object\"],\"domain\":\"2\",\"range\":\"4\",\"pos\":[583.97,566.41]},{\"id\":\"8\",\"iri\":\"http://xmlns.com/foaf/0.1/datatypeProperty1\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"datatypeProperty1\",\"iri-based\":\"datatypeProperty1\",\"en\":\"newDatatypeProperty\"},\"attributes\":[\"external\",\"datatype\"],\"domain\":\"4\",\"range\":\"6\",\"pos\":[817.17,711.12]},{\"id\":\"9\",\"iri\":\"http://xmlns.com/foaf/0.1/datatypeProperty5\",\"baseIri\":\"http://xmlns.com/foaf/0.1\",\"label\":{\"IRI-based\":\"datatypeProperty5\",\"iri-based\":\"datatypeProperty5\",\"en\":\"newDatatypeProperty\"},\"attributes\":[\"external\",\"datatype\"],\"domain\":\"2\",\"range\":\"7\",\"pos\":[439.58,302.68]}]}";

    @Autowired
    public OntologyService ontologyService;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public String getOntologyByID(@PathVariable Integer id) throws IOException {

        return myOntology;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{IRI}")
    public String createOntology(@PathVariable String IRI, @RequestBody String file) throws IOException {
        ontologyService.createOntology(IRI, file);
        return "great success";
    }

/*    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("/{id}")
    public String createOntologyVersion(@PathVariable Integer id) throws IOException {

        return myOntology;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{id}")
    public String deleteOntology(@PathVariable Integer id) throws IOException {

        return myOntology;
    }*/


}