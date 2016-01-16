package br.com.rules.containner;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.RuleConfig;
import br.com.rules.annotation.RulesChild;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Priority;
import br.com.rules.enums.Version;
import br.com.rules.group.RuleGroup;
import com.sun.deploy.security.ruleset.Rule;

import java.util.*;

public class RulesContainner {

    private static final Map<ModeloNFe, Set<RuleValidation>> mContainner = new HashMap<ModeloNFe, Set<RuleValidation>>();
    private static final Map<Version, Set<RuleValidation>> vContainner = new HashMap<Version, Set<RuleValidation>>();

    private static final List<Class<? extends RuleGroup>> groupLoad = new ArrayList<Class<? extends RuleGroup>>();

    private static RulesContainner rulesContainner = new RulesContainner();

    private RulesContainner() {

    }

    public static synchronized RulesContainner getInstance() {
        return rulesContainner;
    }

    public synchronized Set<RuleValidation> getRules(Class<? extends RuleGroup> clazz, Version v, ModeloNFe m) {

        loadRules(clazz);

        Set<RuleValidation>  rules = new HashSet<RuleValidation>();
        rules.addAll(vContainner.get(v));
        rules.addAll(mContainner.get(m));

        return rules;
    }

    private void loadRules(Class<? extends RuleGroup> clazz) {

        if (!groupLoad.contains(clazz)) {

            try {

                if (clazz.isAnnotationPresent(RulesChild.class)) {

                    for (Class<? extends RuleValidation> rule : clazz.getAnnotation(RulesChild.class).rules()) {

                        RuleValidation r = rule.newInstance();

                        RuleConfig ruleConfig = r.getClass().getAnnotation(RuleConfig.class);

                        if (!vContainner.containsKey(ruleConfig.version())){
                            vContainner.put(ruleConfig.version(), new HashSet<RuleValidation>());
                        }

                        vContainner.get(ruleConfig.version()).add(r);

                        for (ModeloNFe model : ruleConfig.models()) {

                            if (!mContainner.containsKey(model)) {
                                mContainner.put(model, new HashSet<RuleValidation>());
                            }

                            mContainner.get(model).add(r);
                        }


                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            groupLoad.add(clazz);
        }
    }
}
