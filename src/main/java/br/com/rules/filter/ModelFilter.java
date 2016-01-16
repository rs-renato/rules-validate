package br.com.rules.filter;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.Rule;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.*;

/**
 * Created by renato-rs on 14/01/2016.
 */
public class ModelFilter implements Filterable<Set<RuleValidation>, ModeloNFe> {

    private Set<RuleValidation> collections;
    private ModeloNFe[] modelosNfes;

    public ModelFilter() {}

    public ModelFilter(Set<RuleValidation> collections, ModeloNFe ...modelosNfes) {
        this.collections = collections;
        this.modelosNfes = modelosNfes;
    }

    @Override
    public Set<RuleValidation> doFilter(Set<RuleValidation> collection, final ModeloNFe... modelosNFe) {

        if (modelosNFe == null || modelosNFe.length == 0
                || Arrays.asList(modelosNFe).contains(ModeloNFe.ALL) ||
                collection == null || collection.isEmpty()){

            return collection;
        }

        Set<RuleValidation> copy = new HashSet<RuleValidation>(collection);

        CollectionUtils.filter(copy, new Predicate<RuleValidation>() {

            @Override
            public boolean evaluate(RuleValidation ruleValidation) {

                boolean isSame = false;

                for(ModeloNFe modeloNFe: modelosNFe){

                    Rule rule = ruleValidation.getClass().getAnnotation(Rule.class);
                    isSame |= rule.modelo().equals(modeloNFe) || rule.modelo().equals(ModeloNFe.ALL);
                }

                return isSame;
            }
        });

        return copy;
    }

    @Override
    public Set<RuleValidation> doFilter() {
        return doFilter(this.collections, this.modelosNfes);
    }

    public Set<RuleValidation> getCollections() {
        return collections;
    }
}
