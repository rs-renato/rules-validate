package br.com.containner;

import br.com.groups.RuleGroup;
import br.com.rules.RuleValidation;

import java.util.*;

/**
 * Created by renato-rs on 18/01/2016.
 */
class IndexedContainner {

    private static final Map<RuleGroup, Set<RuleValidation>> rules = new HashMap<RuleGroup, Set<RuleValidation>>();
    private static final IndexedContainner indexedContainner = new IndexedContainner();

    private IndexedContainner(){}

    public static IndexedContainner getInstance(){
        return indexedContainner;
    }

    public void index(RuleGroup ruleGroup, RuleValidation ruleValidation) {

        if (!rules.containsKey(ruleGroup)){
            rules.put(ruleGroup, new HashSet<RuleValidation>());
        }

        rules.get(ruleGroup).add(ruleValidation);
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup){
        return rules.get(ruleGroup);
    }
}
