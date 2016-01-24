package br.com.groups;

import br.com.exceptions.RuleException;
import br.com.rules.RV590;
import br.com.annotation.Group;
import br.com.rules.RV805;
import br.com.wrapper.Validateable;

@Group(rules={RV590.class, RV805.class})
public class GrupoIdentificacao extends RuleGroup {

	@Override
	public void execute(Validateable validateable) throws RuleException {
		validateRules(validateable);
	}
}
