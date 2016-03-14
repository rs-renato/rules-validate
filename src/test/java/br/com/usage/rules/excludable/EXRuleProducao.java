package br.com.usage.rules.excludable;

import br.com.evaluateables.Excludable;
import br.com.evaluateables.Validateable;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.constants.AMBIENTE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EXRuleProducao implements Excludable{
	
	private static Date DATA_MARCO_PRODUCAO;
	
	static{
		
		try {
			DATA_MARCO_PRODUCAO = new SimpleDateFormat("yyyy/MM/dd").parse("2016/01/01");
		} catch (ParseException e) {
            e.printStackTrace();
        }
	}

    @Override
	public boolean isRuleObjection(Validateable validateable) {

		return ((IdentificacaoWrapper)validateable).getIdentificacao().getTipoAmbiente() == AMBIENTE.PRODUCAO.getCodigo()
				&& ((IdentificacaoWrapper)validateable).getIdentificacao().getDataEmissao().before(DATA_MARCO_PRODUCAO);
	}
}
