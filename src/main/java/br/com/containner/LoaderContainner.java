package br.com.containner;

import br.com.annotation.Group;
import br.com.factory.RulesFactory;
import br.com.groups.RuleGroup;
import br.com.rules.RuleValidation;
import br.com.factory.GroupFactory;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import java.util.Set;

/**
 * Created by renato-rs on 11/01/2016.
 */
class LoaderContainner {

    private static final Logger logger = Logger.getLogger(LoaderContainner.class);
    private static final LoaderContainner LOADER_CONTAINNER = new LoaderContainner();
    private static boolean isLoad;

    private LoaderContainner(){}

    public static LoaderContainner getInstance(){
        return LOADER_CONTAINNER;
    }

    public void startMapping() {

        if (isLoad){
            return;
        }

        logger.info("Initializing Containners..");
        logger.info("Loading Groups Rules..");

        Set<Class<?>> groupClass = new Reflections("br.com.groups").getTypesAnnotatedWith(Group.class);

        logger.info(String.format("%s Groups are loaded..", groupClass.size()));

        for (Class<?> group : groupClass) {

            RuleGroup ruleGroup = GroupFactory.getInstance().getGroup((Class<? extends RuleGroup>) group);

            for(Class<? extends RuleValidation> rule : group.getAnnotation(Group.class).rules()){

                RuleValidation ruleValidation = RulesFactory.getInstance().getRule(rule);

                IndexedContainner.getInstance().index(ruleGroup, ruleValidation);
            }
        }

        isLoad = true;
    }
}
