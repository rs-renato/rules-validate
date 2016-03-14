package br.com.usage.rules;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.Priority;
import br.com.enums.Version;
import br.com.evaluateables.Validateable;
import br.com.messages.ValidateableMessages;
import br.com.rules.RuleValidation;
import br.com.usage.message.Messages;
import br.com.usage.model.Identificacao;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.Produto;

@Rule(version = Version.V3_10, modelo = Model.ALL,priority = Priority.HIGH)
public class RV590 extends RuleValidation {

	@Override
	public boolean isSatisfied(Validateable validateable) {

        IdentificacaoWrapper wrapper = (IdentificacaoWrapper) validateable;

        Identificacao identificacao = wrapper.getIdentificacao();
		Produto produto = wrapper.getProduto();
		
		return identificacao.getCodigoRegimeTributario() == '1' && produto.getCst() != null;
	}

	@Override
	public boolean hasObjection(Validateable validateable) {
		return false;
	}

	@Override
	public ValidateableMessages getValidationMessage() {
		return Messages.REJECT_590;
	}

}
