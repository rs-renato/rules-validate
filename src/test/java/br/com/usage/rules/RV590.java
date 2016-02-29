package br.com.usage.rules;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.ValidationMessages;
import br.com.enums.Version;
import br.com.rules.RuleValidation;
import br.com.usage.model.Identificacao;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.Produto;
import br.com.wrapper.Validateable;

@Rule(version = Version.V3_10, modelo = Model.ALL)
public class RV590 extends RuleValidation {

	@Override
	public boolean isSatisfied(Validateable validateable) {

        IdentificacaoWrapper wrapper = (IdentificacaoWrapper) validateable.getWrapper();

        Identificacao identificacao = wrapper.getIdentificacao();
		Produto produto = wrapper.getProduto();
		
		return identificacao.getCodigoRegimeTributario() == '1' && produto.getCst() != null;
	}

	@Override
	public boolean hasObjection(Validateable validateable) {
		return false;
	}

	@Override
	public ValidationMessages getValidationMessage() {
		return ValidationMessages.REJECT_590;
	}

}
