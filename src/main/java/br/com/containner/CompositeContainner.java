package br.com.containner;

import br.com.rules.RuleValidation;
import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.factory.RulesFactory;
import br.com.filter.CompositeFilter;
import br.com.filter.VersionFilter;
import br.com.groups.RuleGroup;
import br.com.factory.GroupFactory;
import br.com.filter.ModelFilter;

import java.util.Collection;
import java.util.Set;

/**
 * Created by renato-rs on 18/01/2016.
 */
public class CompositeContainner {

    private static CompositeContainner compositeContainner = new CompositeContainner();

    private CompositeContainner() {
        LoaderContainner.getInstance().startMapping();
    }

    public static CompositeContainner getInstance(){
        return compositeContainner;
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup){
        return IndexedContainner.getInstance().getRules(ruleGroup);
    }

    public Collection<RuleGroup> getGroups(){
        return GroupFactory.getInstance().getGroups();
    }

    public Collection<RuleValidation> getRules(){
        return RulesFactory.getInstance().getRules();
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version){

        Set<RuleValidation> rules = getRules(ruleGroup);

        return (Set<RuleValidation>) CompositeFilter.filter(new ModelFilter(rules, modeloNFe), new VersionFilter(rules, version));
    }
}
