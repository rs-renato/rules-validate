package br.com.containner;

import br.com.rules.RuleValidation;
import br.com.annotation.Rule;
import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.filter.CompositeFilter;
import br.com.filter.KeyBuilder;
import br.com.filter.ModelFilter;
import br.com.filter.VersionFilter;
import br.com.groups.RuleGroup;

import java.util.*;

/**
 * Created by renatorodrigues on 17/01/16.
 */
public class CompositeRulesContainner {

    private static final Map<RuleGroup, Set<RuleValidation>> rulesMap = new HashMap<RuleGroup, Set<RuleValidation>>();
    private static final Map<String, Set<RuleValidation>> composeRules= new HashMap<String, Set<RuleValidation>>();

    public static void buildComposite(RuleGroup ruleGroup, Set<RuleValidation> ruleValidations){
        rulesMap.put(ruleGroup, ruleValidations);
        buildRules(ruleGroup, ruleValidations);
    }

    private static void buildRules(RuleGroup ruleGroup, Set<RuleValidation> ruleValidations) {

        for(RuleValidation ruleValidation : ruleValidations){

            Rule rule =  ruleValidation.getClass().getAnnotation(Rule.class);

            String key = KeyBuilder.resolve(ruleGroup, rule.modelo(), rule.version());

            Set<RuleValidation> rules = (Set<RuleValidation>) CompositeFilter.filter(
                    new ModelFilter(getRules(ruleGroup), rule.modelo()),
                    new VersionFilter(getRules(ruleGroup), rule.version()));

            if (!composeRules.containsKey(key)){
                composeRules.put(KeyBuilder.resolve(ruleGroup, rule.modelo(), rule.version()), rules);
            }else{
                composeRules.get(key).addAll(rules);
            }
        }
    }

    public static Set<RuleValidation> getRules(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version){
        return composeRules.get(KeyBuilder.resolve(ruleGroup, modeloNFe, version));
    }

    public static Set<RuleValidation> getRules(RuleGroup ruleGroup){
        return rulesMap.get(ruleGroup);
    }
}