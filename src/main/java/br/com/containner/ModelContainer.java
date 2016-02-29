package br.com.containner;

import br.com.enums.Model;
import br.com.rules.RuleValidation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A container of models represented by {@link Model}
 */
class ModelContainer implements IContainer<Model> {

    private Map<Model, Set<RuleValidation>> rules = new HashMap<Model, Set<RuleValidation>>();

    public ModelContainer() {

        //initialize all model's set
        for (Model model : Model.values()){
            if (model.equals(Model.ALL)) continue;
            rules.put(model, new HashSet<RuleValidation>());
        }
    }

    @Override
    public void addRule(RuleValidation ruleValidation, Model type) {

        switch (type){

            case ALL:{

                //adds the rule to all models
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
    public Set<RuleValidation> getRules(Model type) {
        return rules.get(type);
    }
}
