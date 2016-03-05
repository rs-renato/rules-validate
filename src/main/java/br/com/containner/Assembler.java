package br.com.containner;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.Version;
import br.com.rules.RuleValidation;

import java.util.HashSet;
import java.util.Set;

/**
 * An assembler to join {@link RuleValidation} in speficics sets,
 * considering their {@link Model} and {@link Version}. This assembler
 * wrapps a {@link VersionContainer} and {@link ModelContainer} instances.
 */
class Assembler {

    private final VersionContainer versionContainner = new VersionContainer();
    private final ModelContainer modelContainner = new ModelContainer();

    private final Set<RuleValidation> allRules = new HashSet<RuleValidation>();

    /**
     * Adds a {@link RuleValidation} to this assembler
     * @param ruleValidation rule validation
     */
    public void addRule(RuleValidation ruleValidation){

        this.allRules.add(ruleValidation);

        Rule rule = ruleValidation.getClass().getAnnotation(Rule.class);

        addRule(ruleValidation, rule.version());
        addRule(ruleValidation, rule.modelo());
    }

    /**
     * Adds a {@link RuleValidation} to {@link VersionContainer}, in a specific {@link Version}
     * @param ruleValidation rule validation
     * @param version version of this rule validation
     */
    private void addRule(RuleValidation ruleValidation, Version version){
        this.versionContainner.addRule(ruleValidation, version);
    }

    /**
     * Adds a {@link RuleValidation} to {@link ModelContainer}, in a specific {@link Model}
     * @param ruleValidation rule validation
     * @param model model of this rule validation
     */
    private void addRule(RuleValidation ruleValidation,Model model){
        this.modelContainner.addRule(ruleValidation, model);
    }

    /**
     * Retrieves all {@link RuleValidation} of this assembler, considering a specific {@link Version}
     * @param version version to filter this assembler
     * @return a set of rules in a specific version
     */
    public Set<RuleValidation> getRules(Version version){
        return versionContainner.getRules(version);
    }

    /**
     * Retrieves all {@link RuleValidation} of this assembler, considering a specific {@link Model}
     * @param model model to filter this assembler
     * @return a set of rules in a specific model
     */
    public Set<RuleValidation> getRules(Model model){
        return modelContainner.getRules(model);
    }

    /**
     * Retrieves a set of {@link RuleValidation} associated to this assembler,
     * considering a specific  {@link Model} <b>AND</b> {@link Version} of the rules validation.
     * This means that will return only rules in that model and version.
     * @param model the model to be filtered
     * @param version the version to be filtered
     * @return a set of rules validations of this specific model <b>AND</b> version
     */
    public Set<RuleValidation> getRules(Model model, Version version){

        Set<RuleValidation> rules = new HashSet<RuleValidation>(allRules);
        rules.retainAll(versionContainner.getRules(version));
        rules.retainAll(modelContainner.getRules(model));

        return rules;
    }

    /**
     * Retrieves all {@link RuleValidation} of this assembler
     * @return a set of rule validation
     */
    public Set<RuleValidation> getRules(){
        return allRules;
    }
}
