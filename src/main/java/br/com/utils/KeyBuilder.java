package br.com.utils;

import br.com.enums.ModeloNFe;
import br.com.enums.Version;
import br.com.groups.RuleGroup;

/**
 * Created by renato-rs on 15/01/2016.
 */
public class KeyBuilder {

    public static String resolve(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version) {

        /*return new StringBuilder()
                .append(ruleGroup.getId())
                .append("|")
                .append(modeloNFe.getCodigo())
                .append("|")
                .append(version.getVersion())
                .toString().intern()*/;

        return (ruleGroup.getId() + "|" + modeloNFe.getCodigo() + "|" + version.getVersion()).intern();
    }

    public static String resolve(RuleGroup ruleGroup) {

        return (ruleGroup.getId().toString()).intern();

        /*return new StringBuilder()
                .append(ruleGroup.getId())
                .toString().intern();*/
    }
}
