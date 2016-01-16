package br.com.rules.filter;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.Rule;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.util.*;

import static br.com.rules.enums.Version.ALL;

/**
 * Created by renato-rs on 15/01/2016.
 */
public class VersionFilter implements Filterable<Set<RuleValidation>, Version> {

    private Set<RuleValidation> collection;
    private Version[] versions;

    public VersionFilter() {}

    public VersionFilter(Set<RuleValidation> ruleValidations, Version... versions) {
        this.collection = ruleValidations;
        this.versions = versions;
    }

    @Override
    public Set<RuleValidation> doFilter(Set<RuleValidation> collection, final Version... versions) {

        if (versions == null || versions.length == 0||
                Arrays.asList(versions).contains(Version.ALL) ||
                collection == null || collection.isEmpty()){

            return collection;
        }

        final Set<RuleValidation> copy = new HashSet<RuleValidation>(collection);

        CollectionUtils.filter(copy, new Predicate<RuleValidation>() {

            @Override
            public boolean evaluate(RuleValidation ruleValidation) {

                boolean isSame = false;

                for (Version version : versions) {
                    Rule rule = ruleValidation.getClass().getAnnotation(Rule.class);
                    isSame |= rule.version().equals(version) || rule.version().equals(Version.ALL);
                }

                return isSame;
            }
        });

        return copy;
    }

    @Override
    public Set<RuleValidation> doFilter() {
        return doFilter(this.collection, this.versions);
    }
}
