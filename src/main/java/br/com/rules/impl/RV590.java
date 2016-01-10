package br.com.rules.impl;

import br.com.rules.RuleValidation;
import br.com.rules.enums.Message;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.model.Identificacao;
import br.com.rules.model.Produto;
import br.com.rules.wrapper.Validateable;

//@RuleConfig(models={ModeloNFe.MODELO_55, ModeloNFe.MODELO_65})
public class RV590 extends RuleValidation{
	
	public RV590() {
		super(PRIORITY.LOW, 3.10f, ModeloNFe.MODELO_55, ModeloNFe.MODELO_65);
	}

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
