package br.com.rules.containner;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.Group;
import br.com.rules.annotation.Rule;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.filter.*;
import br.com.rules.group.RuleGroup;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by renato-rs on 11/01/2016.
 */
public class GroupContainner{

    private static final Logger logger = Logger.getLogger(GroupContainner.class);
    private static final Map<RuleGroup, Set<RuleValidation>> rulesMap = new HashMap<RuleGroup, Set<RuleValidation>>();
    private static final Map<String, Set<RuleValidation>> compositeRules = new HashMap<String, Set<RuleValidation>>();
    private static final GroupContainner groupContainner = new GroupContainner();

    private GroupContainner(){
        indexGroupRules();;
    }

    public static GroupContainner getInstance(){
        return groupContainner;
    }

    private void indexGroupRules() {

        Set<Class<?>> groupClass =  new Reflections("br.com.rules.group").getTypesAnnotatedWith(Group.class);
        Set<RuleValidation> rules;
        RuleGroup ruleGroup;

        try{

            for (Class<?> group : groupClass) {

                rules = new HashSet<RuleValidation>();

                ruleGroup = (RuleGroup) group.newInstance();

                for(Class<? extends RuleValidation> rule : group.getAnnotation(Group.class).rules()){
                    rules.add(rule.newInstance());
                }

                rulesMap.put(ruleGroup, rules);

                for (RuleValidation ruleValidation : getRules(ruleGroup)) {

                    Rule rule = ruleValidation.getClass().getAnnotation(Rule.class);

                    String key = KeyBuilder.resolve(ruleGroup, rule.modelo(), rule.version());

                    if (!compositeRules.containsKey(key)){

                        Set<RuleValidation> set = getRules(ruleGroup);

                        Filterable<Set<RuleValidation>, ModeloNFe> modelFilter = new ModelFilter(set, rule.modelo());
                        Filterable<Set<RuleValidation>, Version> versionFilter = new VersionFilter(set, rule.version());

                        compositeRules.put(key, (Set<RuleValidation>) CompositeFilter.filter(modelFilter, versionFilter));

                    }else{
                        compositeRules.get(key).add(ruleValidation);
                    }

                }
            }

            Set<Integer> ids = new HashSet<Integer>();

            for (Set<RuleValidation> ruleValidations : compositeRules.values()) {

                for(RuleValidation rule: ruleValidations){
                    ids.add(System.identityHashCode(rule));
                }
            }

            System.out.println(ids);


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        logger.info("group indexing finished!!");
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup){
        return rulesMap.get(ruleGroup);
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version){
        return compositeRules.get(KeyBuilder.resolve(ruleGroup, modeloNFe, version));
    }

    public Set<RuleGroup> getGroups(){
        return rulesMap.keySet();
    }
}
