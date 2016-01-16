package br.com.rules.group;

import br.com.rules.RuleValidation;
import br.com.rules.containner.GroupContainner;
import br.com.rules.exceptions.RuleException;
import br.com.rules.wrapper.Validateable;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RuleGroup {

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    private final int id = NEXT_ID.getAndIncrement();

    protected void validateRules(Validateable validateable) throws RuleException {

		for (RuleValidation rule: GroupContainner.getInstance().getRules(this,
                                                                        validateable.getIdentificacao().getModeloNFe(),
                                                                        validateable.getIdentificacao().getVersao())){
			rule.validate(validateable);
		}
	}
	
	public abstract void execute(Validateable validateable) throws RuleException;

    public int getId() {
        return id;
    }
}
