package br.com.rules.group;

import br.com.rules.annotation.RulesChild;
import br.com.rules.exceptions.RuleException;
import br.com.rules.impl.RV590;
import br.com.rules.impl.RV805;
import br.com.rules.wrapper.Validateable;

@RulesChild(rules={RV590.class, RV805.class})
public class GrupoIdentificacao extends RuleGroup {

	@Override
	public void execute(Validateable validateable) throws RuleException{
		validateRules(validateable);
	}
}
