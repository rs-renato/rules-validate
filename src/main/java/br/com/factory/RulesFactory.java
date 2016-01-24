package br.com.factory;

import br.com.rules.RuleValidation;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by renato-rs on 18/01/2016.
 */
public class RulesFactory {

    private final Logger logger = Logger.getLogger(RulesFactory.class);
    private static  RulesFactory rulesFactory = new RulesFactory();

    private static final Map<Class<? extends RuleValidation>, RuleValidation> rulesMap = new HashMap<Class<? extends RuleValidation>, RuleValidation>();

    private RulesFactory() {
        logger.info("Initializing RulesFactory..");
    }

    public static RulesFactory getInstance(){
        return rulesFactory;
    }

    public RuleValidation getRule(Class<? extends RuleValidation> ruleValidation){

        if (!rulesMap.containsKey(ruleValidation)){

            try {
                rulesMap.put(ruleValidation, ruleValidation.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return rulesMap.get(ruleValidation);
    }

    public Collection<RuleValidation> getRules(){
        return rulesMap.values();
    }
}
