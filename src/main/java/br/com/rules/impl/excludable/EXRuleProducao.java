package br.com.rules.impl.excludable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.rules.excludable.Excludable;
import br.com.rules.model.Identificacao;
import br.com.rules.model.constants.AMBIENTE;
import br.com.rules.wrapper.Validateable;

public class EXRuleProducao implements Excludable<Validateable>{
	
	private static Date DATA_MARCO_PRODUCAO;
	
	static{
		
		try {
			DATA_MARCO_PRODUCAO = new SimpleDateFormat("yyyy/MM/dd").parse("2016/01/01");
		} catch (ParseException e) {}
	}

    @Override
	public boolean isRuleObjection(Validateable validateable) {
		
		Identificacao identificacao = validateable.getIdentificacao();
		
		return identificacao.getTipoAmbiente() == AMBIENTE.PRODUCAO.getCodigo()
				&& identificacao.getDataEmissao().before(DATA_MARCO_PRODUCAO);
	}
}
