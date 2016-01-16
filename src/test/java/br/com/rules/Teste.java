package br.com.rules;

import br.com.rules.containner.GroupContainner;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.filter.CompositeFilter;
import br.com.rules.filter.Filterable;
import br.com.rules.filter.ModelFilter;
import br.com.rules.filter.VersionFilter;

import java.util.Set;

/**
 * Created by renato-rs on 14/01/2016.
 */
public class Teste {

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GroupContainner groupContainner = GroupContainner.getInstance();
        Set<RuleValidation> set = groupContainner.getRules(groupContainner.getGroups().iterator().next());

        Filterable<Set<RuleValidation>, ModeloNFe> modelFilter = new ModelFilter(set, ModeloNFe.MODELO_65);
        Filterable<Set<RuleValidation>, Version> versionFilter = new VersionFilter(set, Version.V3_10);

        System.out.println(CompositeFilter.filter(modelFilter, versionFilter));

    }
}
