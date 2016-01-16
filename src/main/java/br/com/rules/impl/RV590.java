package br.com.rules.impl;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.Rule;
import br.com.rules.enums.Message;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Version;
import br.com.rules.group.RuleGroup;
import br.com.rules.model.Identificacao;
import br.com.rules.model.Produto;
import br.com.rules.wrapper.Validateable;

@Rule(version = Version.V3_10, modelo = ModeloNFe.ALL)
public class RV590 extends RuleValidation{

	@Override
	public boolean isSatisfied(Validateable validateable) {

		Identificacao identificacao = validateable.getIdentificacao();
		Produto produto = validateable.getProduto();
		
		return identificacao.getCodigoRegimeTributario() == '1' && produto.getCst() != null;
	}

	@Override
	public boolean hasObjection(Validateable validateable) {
		return false;
	}

	@Override
	public Message getMessage() {
		return Message.REJECT_590;
	}

}
