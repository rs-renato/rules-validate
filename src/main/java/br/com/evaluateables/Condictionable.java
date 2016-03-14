package br.com.evaluateables;

/**
 * Contract to condictionable rules validations
 */
public interface Condictionable{

    /**
     * Check if has any condiction to type {@link Validateable}
     * @param validateable type
     * @return true if condiction is satisfied on type
     */
	boolean isRuleCondiction(Validateable validateable);
}
