package br.com.rules.filter;

import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.group.RuleGroup;

/**
 * Created by renato-rs on 15/01/2016.
 */
public class KeyBuilder {

    public static String resolve(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version) {
        return ruleGroup.getClass().getSimpleName() + "|" +  modeloNFe.getCodigo() + "|" + version.getVersion();
    }

    public static String resolve(RuleGroup ruleGroup, ModeloNFe modeloNFe) {
        return ruleGroup.getClass().getSimpleName() + "|" +  modeloNFe.getCodigo();
    }

    public static String resolve(RuleGroup ruleGroup,Version version) {
        return ruleGroup.getClass().getSimpleName() + "|" + version.getVersion();
    }
}
