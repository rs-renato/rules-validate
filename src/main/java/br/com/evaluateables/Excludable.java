package br.com.evaluateables;

/**
 * Contract to objection rules validations
 */
public interface Excludable{

    /**
     * Check if has any objection to type {@link Validateable}
     * @param validateable type
     * @return true if objection is satisfied on type
     */
	boolean isRuleObjection(Validateable validateable);
}
