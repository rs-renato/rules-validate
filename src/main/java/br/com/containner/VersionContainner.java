package br.com.containner;

import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.rules.RuleValidation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by renato-rs on 28/01/2016.
 */
class VersionContainner implements IContainner<Version> {

    private Map<Version, Set<RuleValidation>> rules = new HashMap<Version, Set<RuleValidation>>();

    public VersionContainner() {

        for (Version version : Version.values()){
            if (version.equals(Version.ALL)) continue;
            rules.put(version, new HashSet<RuleValidation>());
        }
    }

    @Override
    public void addRule(RuleValidation ruleValidation, Version type) {

        switch (type){

            case ALL:{

                for (ModeloNFe modeloNFe : ModeloNFe.values()){
                    if (modeloNFe.equals(ModeloNFe.ALL)) continue;
                    rules.get(modeloNFe).add(ruleValidation);
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
