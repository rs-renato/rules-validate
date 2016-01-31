package br.com.groups;

import br.com.containner.GroupContainner;
import br.com.exceptions.RuleException;
import br.com.model.Identificacao;
import br.com.rules.RuleValidation;
import br.com.wrapper.Validateable;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class RuleGroup {

    private static final AtomicInteger NEXT_ID = new AtomicInteger(0);
    private final Integer id = NEXT_ID.getAndIncrement();

    protected void validateRules(Validateable validateable) throws RuleException {

        GroupContainner groupContainner = GroupContainner.getInstance();
        Identificacao identificacao = validateable.getIdentificacao();

		for (RuleValidation rule: groupContainner.getRules(this, identificacao.getModeloNFe(), identificacao.getVersao())){
			rule.validate(validateable);
		}
	}
	
	public abstract void execute(Validateable validateable) throws RuleException;

    public Integer getId() {
        return id;
    }
}
