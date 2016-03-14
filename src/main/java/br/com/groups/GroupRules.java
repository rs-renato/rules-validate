package br.com.groups;

import br.com.containner.GroupContainer;
import br.com.evaluateables.Validateable;
import br.com.exceptions.ValidateException;
import br.com.rules.RuleValidation;

/**
 * Abstraction class to Group Rules
 */
public abstract class GroupRules {

    /**
     * Validate associated rules to this group of rules
     * @param validateable a validation type to be evaluated
     * @throws ValidateException throws validation expeception if any rule wasn't satisfied
     */
    protected void validateRules(Validateable validateable) throws ValidateException {

        GroupContainer groupContainer = GroupContainer.getInstance();

        //get all rules from this group by yours validatetion particulars
		for (RuleValidation rule: groupContainer.getRules(this, validateable)){
			rule.validate(validateable);
		}
	}

    /**
     * Fire all validation rules from this group
     * @param validateable a validation type to be evaluated
     * @throws ValidateException validation expeception if any rule wasn't satisfied
     */
	public abstract void execute(Validateable validateable) throws ValidateException;

}
