package br.com.factory;

import br.com.groups.RuleGroup;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by renato-rs on 18/01/2016.
 */
public class GroupFactory {

    private final Logger logger = Logger.getLogger(GroupFactory.class);
    private static final Map<Class<? extends RuleGroup>, RuleGroup> rulesGroup = new HashMap<Class<? extends RuleGroup>, RuleGroup>();
    private static  GroupFactory groupFactory = new GroupFactory();

    private GroupFactory() {
        logger.info("Initializing GroupFactory..");
    }

    public static GroupFactory getInstance(){
        return  groupFactory;
    }

    public RuleGroup getGroup(Class<? extends RuleGroup> ruleGroup){

        if (!rulesGroup.containsKey(ruleGroup)){

            try {
                rulesGroup.put(ruleGroup, ruleGroup.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rulesGroup.get(ruleGroup);
    }

    public Collection<RuleGroup> getGroups(){
        return rulesGroup.values();
    }
}
