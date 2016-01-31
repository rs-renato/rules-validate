package br.com.containner;

import br.com.annotation.Group;
import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.factory.GroupFactory;
import br.com.factory.RulesFactory;
import br.com.groups.RuleGroup;
import br.com.rules.RuleValidation;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Created by renato-rs on 28/01/2016.
 */
public class GroupContainner/* implements IContainner<RuleGroup>*/{

    private static final Map<RuleGroup, GroupWrapper> groups = new HashMap<RuleGroup, GroupWrapper>();
    private static final GroupContainner groupContainner = new GroupContainner();
    private Logger logger = Logger.getLogger(GroupContainner.class);

    private GroupContainner() {
        startMapping();
    }

    public void startMapping() {

        logger.info("Initializing Containners..");
        logger.info("Loading Groups Rules..");

        Set<Class<?>> groupClass = new Reflections("br.com.groups").getTypesAnnotatedWith(Group.class);

        logger.info(String.format("%s Groups are loaded..", groupClass.size()));

        for (Class<?> group : groupClass) {

            RuleGroup ruleGroup = GroupFactory.getInstance().getGroup((Class<? extends RuleGroup>) group);

            for(Class<? extends RuleValidation> rule : group.getAnnotation(Group.class).rules()){

                RuleValidation ruleValidation = RulesFactory.getInstance().getRule(rule);

                addRule(ruleValidation, ruleGroup);
            }
        }
    }

    public static GroupContainner getInstance() {
        return groupContainner;
    }

//    @Override
    private void addRule(RuleValidation ruleValidation, RuleGroup type) {

        if(!groups.containsKey(type)){
            groups.put(type, new GroupWrapper());
        }

        groups.get(type).addRule(ruleValidation);
    }

    public Set<RuleValidation> getRules(RuleGroup type, ModeloNFe modeloNFe, Version version) {
        return groups.get(type).getRules(modeloNFe, version);
    }

    public Collection<RuleGroup> getGroups(){
        return GroupFactory.getInstance().getGroups();
    }
}