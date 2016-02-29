package br.com.factory;

import br.com.groups.GroupRules;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * A factory of {@link GroupRules} types
 */
public class GroupFactory {

    private final Logger logger = Logger.getLogger(GroupFactory.class);
    private static final Map<Class<? extends GroupRules>, GroupRules> rulesGroup = new HashMap<Class<? extends GroupRules>, GroupRules>();
    private static  GroupFactory groupFactory = new GroupFactory();

    private GroupFactory() {
        logger.info("Initializing GroupFactory..");
    }

    public static GroupFactory getInstance(){
        return  groupFactory;
    }

    /**
     * Retrieves a {@link GroupRules} instance from a specific class
     * @param ruleGroup group class to build and retrieve an instance;
     * @return a unique {@link GroupRules} instance
     */
    public GroupRules getGroup(Class<? extends GroupRules> ruleGroup){

        if (!rulesGroup.containsKey(ruleGroup)){

            try {
                rulesGroup.put(ruleGroup, ruleGroup.newInstance());
            } catch (Exception e) {
               logger.error(e);
            }
        }

        return rulesGroup.get(ruleGroup);
    }

    /**
     * Retrieves all {@link GroupRules} instances
     * @return a collection of group rules instances
     */
    public Collection<GroupRules> getGroups(){
        return rulesGroup.values();
    }
}
