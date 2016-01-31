package br.com.containner;

import br.com.annotation.Rule;
import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.rules.RuleValidation;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by renato-rs on 29/01/2016.
 */
public class GroupWrapper {

    private VersionContainner versionContainner = new VersionContainner();
    private ModelContainner modelContainner = new ModelContainner();

    private Set<RuleValidation> allRules = new HashSet<RuleValidation>();

    public void addRule(RuleValidation ruleValidation){

        this.allRules.add(ruleValidation);

        Rule rule = ruleValidation.getClass().getAnnotation(Rule.class);

        addRule(ruleValidation, rule.version());
        addRule(ruleValidation, rule.modelo());
    }

    private void addRule(RuleValidation ruleValidation, Version version){
        this.versionContainner.addRule(ruleValidation, version);
    }

    private void addRule(RuleValidation ruleValidation,ModeloNFe modeloNFe){
        this.modelContainner.addRule(ruleValidation, modeloNFe);
    }

    public Set<RuleValidation> getRules(Version version){
        return versionContainner.getRules(version);
    }

    public Set<RuleValidation> getRules(ModeloNFe modeloNFe){
        return modelContainner.getRules(modeloNFe);
    }

    public Set<RuleValidation> getRules(){
        return allRules;
    }

    public Set<RuleValidation> getRules(ModeloNFe modeloNFe, Version version){

        Set<RuleValidation> rules = new HashSet<RuleValidation>(allRules);
        rules.retainAll(versionContainner.getRules(version));
        rules.retainAll(modelContainner.getRules(modeloNFe));

        return rules;
    }
}
