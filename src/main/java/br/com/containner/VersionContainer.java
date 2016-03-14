package br.com.containner;

import br.com.enums.Version;
import br.com.rules.RuleValidation;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A container of version represented by {@link Version}
 */
class VersionContainer implements IContainer<Version> {

    private static final Logger logger = Logger.getLogger(VersionContainer.class);
    private final Map<Version, Set<RuleValidation>> rules = new HashMap<Version, Set<RuleValidation>>();

    public VersionContainer() {

        //initialize all version's set
        for (Version version : Version.values()){
            if (version.equals(Version.ALL)) continue;
            rules.put(version, new HashSet<RuleValidation>());
        }

        logger.debug("Rules by version' map loaded.. ");
    }

    @Override
    public void addRule(RuleValidation ruleValidation, Version type) {

        switch (type){

            case ALL:{

                //adds the rule to all versions
                for (Version version : Version.values()){
                    if (version.equals(Version.ALL)) continue;
                    rules.get(version).add(ruleValidation);
                }

                break;
            }

            default:{
                rules.get(type).add(ruleValidation);
                break;
            }
        }
    }

    @Override
    public Set<RuleValidation> getRules(Version type) {
        return rules.get(type);
    }
}
