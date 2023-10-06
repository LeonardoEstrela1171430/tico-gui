package com.ipp.isep.OntologyEvolutionAPI.service;

import org.springframework.beans.factory.annotation.Value;

public class TicoService {
    public String ticoURL;

    public TicoService(@Value("${TICO_URL}") String ticoURL) {
        this.ticoURL = ticoURL;
    }
}
