package br.com.rules.excludable;

import br.com.excludable.Excludable;
import br.com.model.Identificacao;
import br.com.model.constants.AMBIENTE;
import br.com.wrapper.Validateable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
