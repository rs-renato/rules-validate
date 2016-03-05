package br.com.containner;

import br.com.rules.RuleValidation;

import java.util.Set;

/**
 * Contract to containers. This container must be
 * parametrized by specific type
 */
interface IContainer<T> {
    /**
     * Adds a {@link RuleValidation} to container
     * @param ruleValidation rule validation
     * @param type type of {@link T} associated to the rule
     */
    void addRule(RuleValidation ruleValidation, T type);

    /**
     * Retrieves all the rules associated by type
     * @param type type of {@link T} to retrieve the rules
     * @return a set of {@link RuleValidation} associated to {@link T}
     */
    Set<RuleValidation> getRules(T type);
}
