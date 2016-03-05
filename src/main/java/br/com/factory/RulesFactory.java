package br.com.factory;

import br.com.rules.RuleValidation;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A factory of {@link RuleValidation} types
 */
public class RulesFactory {

    private static final Logger logger = Logger.getLogger(RulesFactory.class);
    private static final RulesFactory rulesFactory = new RulesFactory();

    private static final Map<Class<? extends RuleValidation>, RuleValidation> rulesMap = new HashMap<Class<? extends RuleValidation>, RuleValidation>();

    private RulesFactory() {
        logger.info("Initializing RulesFactory..");
    }

    public static RulesFactory getInstance(){
        return rulesFactory;
    }

    /**
     * Retrieves a {@link RuleValidation} instance from a specific class
     * @param ruleValidation group class to build and retrieve an instance;
     * @return a unique {@link RuleValidation} instance
     */
    public RuleValidation getRule(Class<? extends RuleValidation> ruleValidation){

        if (!rulesMap.containsKey(ruleValidation)){

            try {
                rulesMap.put(ruleValidation, ruleValidation.newInstance());
            } catch (Exception e) {
                logger.error(e);
            }
        }

        return rulesMap.get(ruleValidation);
    }

    /**
     * Retrieves all {@link RuleValidation} instances
     * @return a collection of rule validation instances
     */
    public Collection<RuleValidation> getRules(){
        return rulesMap.values();
    }
}
