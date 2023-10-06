/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDC.EvolActions.Interfaces;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.apache.jena.ontology.OntModel;

/**
 *
 * @author shizamura
 */
public interface EvolutionaryAction
{
    public String getURI();
    public void execute();
    @Override
    public String toString();
    public String getClassName();

}

