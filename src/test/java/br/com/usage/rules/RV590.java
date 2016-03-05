package br.com.usage.rules;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.Version;
import br.com.messages.ValidateableMessages;
import br.com.rules.RuleValidation;
import br.com.usage.message.Messages;
import br.com.usage.model.Identificacao;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.Produto;

@Rule(version = Version.V3_10, modelo = Model.ALL)
public class RV590 extends RuleValidation<IdentificacaoWrapper> {

	@Override
	public boolean isSatisfied(IdentificacaoWrapper validateable) {

        Identificacao identificacao = validateable.getIdentificacao();
		Produto produto = validateable.getProduto();
		
		return identificacao.getCodigoRegimeTributario() == '1' && produto.getCst() != null;
	}

	@Override
	public boolean hasObjection(IdentificacaoWrapper validateable) {
		return false;
	}

	@Override
	public ValidateableMessages getValidationMessage() {
		return Messages.REJECT_590;
	}

}
