package br.com.rules.containner;

import br.com.rules.RuleValidation;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.filter.*;
import br.com.rules.group.RuleGroup;

import java.util.*;

/**
 * Created by renatorodrigues on 17/01/16.
 */
public class CompositeRulesContainner {

    private static final Map<String, Set<RuleValidation>> compositeRules = new HashMap<String, Set<RuleValidation>>();
    private static final Map<RuleGroup, Set<RuleValidation>> rulesMap = new HashMap<RuleGroup, Set<RuleValidation>>();

    public static void buildComposite(RuleGroup ruleGroup, Set<RuleValidation> ruleValidations){

        rulesMap.put(ruleGroup, ruleValidations);

        composeModels(ruleGroup, ruleValidations);
        composeVersions(ruleGroup, ruleValidations);
        combineModeVersion();
    }

    private static void combineModeVersion() {



    }

    private static void composeVersions(RuleGroup ruleGroup, Set<RuleValidation> ruleValidations){

        Filterable<Set<RuleValidation>, Version> versionFilter;

        for (Version v: Version.values()){

            if (v.equals(Version.ALL)) continue;

            versionFilter = new VersionFilter(ruleValidations, v);
            compositeRules.put(KeyBuilder.resolve(ruleGroup, v), (Set<RuleValidation>) CompositeFilter.filter(versionFilter));
        }

        List<Version> versions = new ArrayList<Version>(Arrays.asList(Version.values()));
        versions.remove(Version.ALL);

        for(Version v: versions){
            versionFilter = new VersionFilter(ruleValidations, Version.ALL);
            compositeRules.put(KeyBuilder.resolve(ruleGroup, v), (Set<RuleValidation>) CompositeFilter.filter(versionFilter));
        }
    }

    private static void composeModels(RuleGroup ruleGroup, Set<RuleValidation> ruleValidations){

        Filterable<Set<RuleValidation>, ModeloNFe> modelFilter;

        for (ModeloNFe m: ModeloNFe.values()){

            if (m.equals(ModeloNFe.ALL)) continue;

            modelFilter = new ModelFilter(ruleValidations, m);
            compositeRules.put(KeyBuilder.resolve(ruleGroup, m), (Set<RuleValidation>) CompositeFilter.filter(modelFilter));
        }

        List<ModeloNFe> modeloNFes = new ArrayList<ModeloNFe>(Arrays.asList(ModeloNFe.values()));
        modeloNFes.remove(ModeloNFe.ALL.ALL);

        for(ModeloNFe m: modeloNFes){
            modelFilter = new ModelFilter(ruleValidations, ModeloNFe.ALL);
            compositeRules.put(KeyBuilder.resolve(ruleGroup, m), (Set<RuleValidation>) CompositeFilter.filter(modelFilter));
        }
    }

    public static Set<RuleValidation> getRules(RuleGroup ruleGroup, ModeloNFe modeloNFe, Version version){

        Set<RuleValidation> ruleModel = compositeRules.get(KeyBuilder.resolve(ruleGroup, modeloNFe));
        Set<RuleValidation> ruleVersion = compositeRules.get(KeyBuilder.resolve(ruleGroup, version));

        Filterable<Set<RuleValidation>, ModeloNFe> modelFilter = new ModelFilter(modeloNFe.equals(ModeloNFe.ALL) ?
                                                                        getRules(ruleGroup) : ruleModel, modeloNFe);

        Filterable<Set<RuleValidation>, Version> versionFilter = new VersionFilter(version.equals(Version.ALL) ?
                                                                        getRules(ruleGroup) :ruleVersion, version);

        return (Set<RuleValidation>) CompositeFilter.filter(modelFilter, versionFilter);
    }

    public static Set<RuleGroup> getGroups(){
        return rulesMap.keySet();
    }

    public static Set<RuleValidation> getRules(RuleGroup ruleGroup){
        return rulesMap.get(ruleGroup);
    }
}