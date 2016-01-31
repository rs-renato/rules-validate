package br.com.containner;

import br.com.rules.RuleValidation;

import java.util.Set;

/**
 * Created by renato-rs on 29/01/2016.
 */
public interface IContainner<T> {
    void addRule(RuleValidation ruleValidation, T type);
    Set<RuleValidation> getRules(T type);
}
