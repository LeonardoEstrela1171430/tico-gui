package isep.ipp.pt.tico.controller;
import IDC.Comparator;
import IDC.EvolActions.Interfaces.EvolutionaryAction;
import IDC.ModelManager;
import isep.ipp.pt.tico.dto.EvolutionaryActionsDTO;
import isep.ipp.pt.tico.dto.ExecuteActionsDTO;
import org.apache.commons.io.IOUtils;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import Utils.Configs;
import java.io.IOException;
import java.util.List;

import static Utils.Utilities.unstringifyEvolActionsList;

@RestController
public class TicoController {

    Configs configs = new Configs();

    @CrossOrigin(origins = "*")
    @PostMapping("/evolactions")
    public List<EvolutionaryAction> generateEvolutionaryActions(@RequestBody EvolutionaryActionsDTO dto) throws IOException {
        Configs.functional_threshold     = 50;
        Configs.subclass_threshold       = 100;
        Configs.equivalent_threshold     = 150;
        Configs.someValuesFrom_threshold = 20;

        OntModel ontModel = ModelFactory.createOntologyModel();
        ontModel.read(IOUtils.toInputStream(dto.ontology, "UTF-8"), "", "TTL");
        OntModel instanceModel = ModelFactory.createOntologyModel();
        instanceModel.read(IOUtils.toInputStream(dto.instance, "UTF-8"), "", "TTL");

        ModelManager.getManager().setup(ontModel, ontModel, instanceModel);        Comparator comparator = new Comparator();

        List<EvolutionaryAction> toReturn = comparator.getEvolutionaryActions();
        return toReturn;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/execute")
    public String executeEvolutionaryActions(@RequestBody ExecuteActionsDTO dto) throws IOException {
        Configs.functional_threshold     = 50;
        Configs.subclass_threshold       = 100;
        Configs.equivalent_threshold     = 150;
        Configs.someValuesFrom_threshold = 20;

        OntModel ontModel = ModelFactory.createOntologyModel();
        ontModel.read(IOUtils.toInputStream(dto.ontology, "UTF-8"), "", "TTL");
        OntModel instanceModel = ModelFactory.createOntologyModel();
        instanceModel.read(IOUtils.toInputStream(dto.instance, "UTF-8"), "", "TTL");

        ModelManager.getManager().setup(ontModel, ontModel, instanceModel);

        Comparator comparator = new Comparator();

        List<JSONObject> actualActions = unstringifyEvolActionsList(dto.actions);

        String result = comparator.executeActions(actualActions);
        return result;
    }

}
