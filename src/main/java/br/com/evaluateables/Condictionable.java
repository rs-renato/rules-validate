package br.com.evaluateables;

/**
 * Contract to condictionable rules validations
 * @param <T> Type to be evalueted
 */
public interface Condictionable<T>{

    /**
     * Check if has any condiction to type {@link T}
     * @param t type
     * @return true if condiction is satisfied on type
     */
	boolean isRuleCondiction(T t);
}
