package br.com.containner;

import br.com.enums.Model;
import br.com.enums.Version;
import br.com.rules.RuleValidation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A container of version represented by {@link Version}
 */
class VersionContainer implements IContainer<Version> {

    private Map<Version, Set<RuleValidation>> rules = new HashMap<Version, Set<RuleValidation>>();

    public VersionContainer() {

        //initialize all version's set
        for (Version version : Version.values()){
            if (version.equals(Version.ALL)) continue;
            rules.put(version, new HashSet<RuleValidation>());
        }
    }

    @Override
    public void addRule(RuleValidation ruleValidation, Version type) {

        switch (type){

            case ALL:{

                //adds the rule to all versions
                for (Model model : Model.values()){
                    if (model.equals(Model.ALL)) continue;
                    rules.get(model).add(ruleValidation);
                }

                break;
            }

            default:{
                rules.get(type).add(ruleValidation);
                break;
            }
        }
    }

    @Override
    public Set<RuleValidation> getRules(Version type) {
        return rules.get(type);
    }
}
