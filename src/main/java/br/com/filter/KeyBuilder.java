package br.com.filter;

import br.com.enums.Version;
import br.com.groups.RuleGroup;
import br.com.enums.ModeloNFe;

/**
 * Created by renato-rs on 15/01/2016.
 */
public class KeyBuilder {

    public static String resolve(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version) {
        return new StringBuilder()
                .append(ruleGroup.getId())
                .append("|")
                .append(modeloNFe.getCodigo())
                .append("|")
                .append(version.getVersion())
                .toString();
    }

    public static String resolve(RuleGroup ruleGroup, ModeloNFe modeloNFe) {
        return new StringBuilder()
                .append(ruleGroup.getId())
                .append("|")
                .append(modeloNFe.getCodigo())
                .toString();
    }

    public static String resolve(RuleGroup ruleGroup,Version version) {
        return new StringBuilder()
                .append(ruleGroup.getId())
                .append("|")
                .append(version.getVersion())
                .toString();
    }
}
