package br.com.rules.containner;

import br.com.rules.RuleValidation;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;

import java.util.*;

/**
 * Created by renato-rs on 11/01/2016.
 */
public class RuleContainner {

    private static final Map<String, Set<RuleValidation>> rulesMap = new HashMap<String, Set<RuleValidation>>();
    private static final RuleContainner ruleContainner = new RuleContainner();

    private RuleContainner(){
    }

    public static RuleContainner getInstance(){
        return ruleContainner;
    }

    public Set<RuleValidation> getRules(ModeloNFe modeloNFe, Version version){
        return Collections.unmodifiableSet(rulesMap.get(modeloNFe.getCodigo() + version.getVersion()));
    }

    public synchronized void addRule(RuleValidation ruleValidation) {

        String code = ruleValidation.getModeloNFe().getCodigo();
        String version = ruleValidation.getVersion().getVersion();

       //FIXME validar em caso de multiplas versoes e modelos

        if (!rulesMap.containsKey(code + version)){
            rulesMap.put(code + version, new HashSet<RuleValidation>());
            rulesMap.get(code + version).add(ruleValidation);
        }
    }
}
