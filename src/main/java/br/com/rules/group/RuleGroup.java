package br.com.rules.group;

import java.util.Map;
import java.util.Set;

import br.com.rules.RuleValidation;
import br.com.rules.containner.RulesContainner;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.exceptions.RuleException;
import br.com.rules.wrapper.Validateable;

public abstract class RuleGroup {
	
	protected void validateRules(Validateable validateable) throws RuleException {
		
		Map<ModeloNFe, Set<RuleValidation>> map = RulesContainner.getInstance().getRules(getClass());
		
		Set<RuleValidation> rules = map.get(validateable.getIdentificacao().getModeloNFe());
		
		for (RuleValidation rule: rules) {
			rule.validate(validateable);
		}
	}
	
	public abstract void execute(Validateable validateable) throws RuleException;
	
}
