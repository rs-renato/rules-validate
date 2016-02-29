package br.com.groups;

import br.com.containner.GroupContainer;
import br.com.exceptions.ValidateException;
import br.com.rules.RuleValidation;
import br.com.wrapper.Validateable;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class GroupRules {

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    private final Integer id = NEXT_ID.getAndIncrement();

    protected void validateRules(Validateable validateable) throws ValidateException {

        GroupContainer groupContainer = GroupContainer.getInstance();

		for (RuleValidation rule: groupContainer.getRules(this, validateable)){
			rule.validate(validateable);
		}
	}
	
	public abstract void execute(Validateable validateable) throws ValidateException;

    public Integer getId() {
        return id;
    }
}
