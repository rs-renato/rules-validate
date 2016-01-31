package br.com.rules;

import br.com.annotation.Rule;
import br.com.enums.Version;
import br.com.model.Identificacao;
import br.com.enums.Message;
import br.com.enums.ModeloNFe;
import br.com.model.Produto;
import br.com.wrapper.Validateable;

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
