package br.com.rules.containner;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.Group;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.group.RuleGroup;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.*;

/**
 * Created by renato-rs on 11/01/2016.
 */
public class GroupContainner{

    private static final Logger logger = Logger.getLogger(GroupContainner.class);
    private static final GroupContainner groupContainner = new GroupContainner();

    private GroupContainner(){
        indexGroupRules();
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

                CompositeRulesContainner.buildComposite(ruleGroup, rules);
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        logger.info("group indexing finished!!");
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup){
        return CompositeRulesContainner.getRules(ruleGroup);
    }

    public Set<RuleValidation> getRules(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version){
        return CompositeRulesContainner.getRules(ruleGroup, modeloNFe, version);
    }

    public Set<RuleGroup> getGroups(){
        return CompositeRulesContainner.getGroups();
    }
}
