package br.com.excludable;

/**
 * Contract to excludable rules validations
 * @param <T> Type to be evalueted
 */
public interface Excludable<T>{

    /**
     * Check if has any objection to type {@link T}
     * @param t type
     * @return true if has objection on type
     */
	boolean isRuleObjection(T t);
}
