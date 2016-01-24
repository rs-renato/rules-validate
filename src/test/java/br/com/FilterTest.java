package br.com;

import br.com.containner.CompositeContainner;
import br.com.enums.Version;
import br.com.filter.*;
import br.com.enums.ModeloNFe;
import br.com.filter.ModelFilter;
import br.com.filter.VersionFilter;
import br.com.rules.RuleValidation;

import java.util.Set;

/**
 * Created by renato-rs on 15/01/2016.
 */
public class FilterTest {

//    @Test
    public void deveTestat(){

        CompositeContainner compositeContainner = CompositeContainner.getInstance();
        Set<RuleValidation> set = compositeContainner.getRules(compositeContainner.getGroups().iterator().next());

        Filterable<Set<RuleValidation>, ModeloNFe> modelFilter = new ModelFilter(set, ModeloNFe.MODELO_65);
        Filterable<Set<RuleValidation>, Version> versionFilter = new VersionFilter(set, Version.V3_10);

        System.out.println(CompositeFilter.filter(modelFilter, versionFilter));


    }
}
