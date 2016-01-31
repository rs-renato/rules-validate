package br.com.containner;

import br.com.enums.ModeloNFe;
import br.com.rules.RuleValidation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by renato-rs on 28/01/2016.
 */
class ModelContainner implements IContainner<ModeloNFe> {

    private Map<ModeloNFe, Set<RuleValidation>> rules = new HashMap<ModeloNFe, Set<RuleValidation>>();

    public ModelContainner() {

        for (ModeloNFe modeloNFe : ModeloNFe.values()){
            if (modeloNFe.equals(ModeloNFe.ALL)) continue;
            rules.put(modeloNFe, new HashSet<RuleValidation>());
        }
    }

    @Override
    public void addRule(RuleValidation ruleValidation, ModeloNFe type) {

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
    public Set<RuleValidation> getRules(ModeloNFe type) {
        return rules.get(type);
    }
}
