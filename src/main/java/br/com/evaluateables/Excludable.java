package br.com.evaluateables;

/**
 * Contract to objection rules validations
 * @param <T> Type to be evalueted
 */
public interface Excludable<T>{

    /**
     * Check if has any objection to type {@link T}
     * @param t type
     * @return true if objection is satisfied on type
     */
	boolean isRuleObjection(T t);
}
