package br.com.usage.group;

import br.com.annotation.Group;
import br.com.enums.Priority;
import br.com.evaluateables.Validateable;
import br.com.exceptions.ValidateException;
import br.com.groups.GroupRules;
import br.com.usage.rules.RV590;
import br.com.usage.rules.RV805;

@Group(rules={RV590.class, RV805.class}, priority = Priority.HIGH)
public class GrupoIdentificacao extends GroupRules {

	@Override
	public void execute(Validateable validateable) throws ValidateException {
		validateRules(validateable);
	}
}
