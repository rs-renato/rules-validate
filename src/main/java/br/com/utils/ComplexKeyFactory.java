package br.com.utils;

import br.com.annotation.Rule;
import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.groups.RuleGroup;
import br.com.rules.RuleValidation;

import java.util.*;

/**
 * Created by renatorodrigues on 24/01/16.
 */
public class ComplexKeyFactory {

    private static final Map<ComplexKey, Set<RuleValidation>> rules = new HashMap<ComplexKey, Set<RuleValidation>>();

    private static ComplexKeyFactory complexKeyFactory = new ComplexKeyFactory();
    private static Map<String, ComplexKey> complexKeys = new HashMap<String, ComplexKey>();

    private ComplexKeyFactory(){

    }

    public static ComplexKeyFactory getInstance() {
        return complexKeyFactory;
    }

    public void indexKeys(RuleGroup ruleGroup, RuleValidation ruleValidation) {

        Rule rule = ruleValidation.getClass().getAnnotation(Rule.class);

        String key = KeyBuilder.resolve(ruleGroup, rule.modelo(), rule.version());
        String keyGroup = KeyBuilder.resolve(ruleGroup);

        if(!complexKeys.containsKey(key)){

            complexKeys.put(keyGroup, new ComplexKey(ruleGroup.getId(), null, null));

            if (rule.modelo().equals(ModeloNFe.ALL)){

                for(ModeloNFe modelo : ModeloNFe.values()){

                    if (modelo.equals(ModeloNFe.ALL)) continue;

                    ComplexKey complexKey =  new ComplexKey(ruleGroup.getId(), modelo, rule.version());

                    complexKeys.put(KeyBuilder.resolve(ruleGroup, modelo, rule.version()), complexKey);

                    addRule(complexKey, ruleValidation);
                }

            }else if (rule.version().equals(Version.ALL)){

                for(Version version : Version.values()){

                    if (version.equals(Version.ALL)) continue;

                    ComplexKey complexKey = new ComplexKey(ruleGroup.getId(), rule.modelo(), version);

                    complexKeys.put(KeyBuilder.resolve(ruleGroup, rule.modelo(), version), complexKey);

                    addRule(complexKey, ruleValidation);
                }

            }else {

                ComplexKey complexKey = new ComplexKey(ruleGroup.getId(), rule.modelo(), rule.version());

                complexKeys.put(key, complexKey);

                addRule(complexKey, ruleValidation);
            }
        }
    }

    private void addRule(ComplexKey complexKey, RuleValidation ruleValidation) {

        if (!rules.containsKey(complexKey)){
            rules.put(complexKey,new HashSet<RuleValidation>());
        }

        rules.get(complexKey).add(ruleValidation);
    }

    public Set<RuleValidation> getComplexKey(RuleGroup ruleGroup){
        return rules.get(complexKeys.get(KeyBuilder.resolve(ruleGroup)));
    }

    public Set<RuleValidation> getComplexKey(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version){
        return rules.get(complexKeys.get(KeyBuilder.resolve(ruleGroup, modeloNFe, version)));
    }
}
