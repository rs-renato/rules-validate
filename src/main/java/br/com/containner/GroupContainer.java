package br.com.containner;

import br.com.annotation.Group;
import br.com.enums.Model;
import br.com.enums.Version;
import br.com.factory.GroupFactory;
import br.com.factory.RulesFactory;
import br.com.groups.GroupRules;
import br.com.rules.RuleValidation;
import br.com.evaluateables.Validateable;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.*;


/**
 * A container of groups represented by {@link GroupRules}
 */
public class GroupContainer implements IContainer<GroupRules> {

    private static final Map<GroupRules, Assembler> groups = new HashMap<GroupRules, Assembler>();
    private static final GroupContainer GROUP_CONTAINER = new GroupContainer();
    private Logger logger = Logger.getLogger(GroupContainer.class);

    private static final String GROUP_PACKAGE = "br.com.usage.group"; //FIXME change package convention; load from property

    private GroupContainer() {
        init();
    }

    /**
     * Initialize the container, loading all {@link GroupRules} and their respectives {@link RuleValidation}.
     * Every single rule is a unique instance created by {@link RulesFactory} and associated to your group.
     */
    public void init() {

        logger.info("Initializing Container..");
        logger.info("Loading Groups Rules..");

        Set<Class<?>> groupClass = new Reflections(GROUP_PACKAGE).getTypesAnnotatedWith(Group.class);

        logger.info(String.format("Groups loaded: %s", Arrays.asList(groupClass.toArray())));

        for (Class<?> group : groupClass) {

            GroupRules groupRules = GroupFactory.getInstance().getGroup((Class<? extends GroupRules>) group);

            for(Class<? extends RuleValidation> rule : group.getAnnotation(Group.class).rules()){

                RuleValidation ruleValidation = RulesFactory.getInstance().getRule(rule);

                addRule(ruleValidation, groupRules);
            }
        }
    }

    /**
     * Returns a unique instance of {@link GroupContainer}
     * @return instance of this container.
     */
    public static GroupContainer getInstance() {
        return GROUP_CONTAINER;
    }

    @Override
    public void addRule(RuleValidation ruleValidation, GroupRules type) {

        if(!groups.containsKey(type)){
            groups.put(type, new Assembler());
        }

        groups.get(type).addRule(ruleValidation);
        logger.info(ruleValidation.getClass().getName() + " was loaded to " + type.getClass().getName());
    }

    @Override
    public Set<RuleValidation> getRules(GroupRules type) {
        return groups.get(type).getRules();
    }

    /**
     * Retrieves a set of {@link RuleValidation} associated to {@link GroupRules},
     * considering a specific  {@link Model} <b>AND</b> {@link Version} of the rules validation
     * contained in this group rule. This means that will return only rules in that model and version.
     * @param groupRules the rule's owner
     * @param validateable a validation type to be evaluated
     * @return a set of rules validations of this specific model <b>AND</b> version
     */
    public Set<RuleValidation> getRules(GroupRules groupRules, Validateable validateable) {
        return groups.get(groupRules).getRules(validateable.getModel(), validateable.getVersion());
    }

    /**
     * Retrieves all {@link GroupRules} loaded in the {@link GroupContainer}
     * @return a collection of group rules
     */
    public Collection<GroupRules> getGroups(){
        return GroupFactory.getInstance().getGroups();
    }
}