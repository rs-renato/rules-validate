package br.com.rules.impl;

import br.com.rules.RuleValidation;
import br.com.rules.annotation.Rule;
import br.com.rules.enums.Message;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.enums.Priority;
import br.com.rules.enums.Version;
import br.com.rules.excludable.Excludable;
import br.com.rules.group.RuleGroup;
import br.com.rules.impl.excludable.EXRuleProducao;
import br.com.rules.model.Identificacao;
import br.com.rules.model.constants.INDI_IE_DEST;
import br.com.rules.wrapper.Validateable;

@Rule(priority = Priority.HIGH, version = Version.ALL, modelo = ModeloNFe.ALL)
public class RV805 extends RuleValidation{
	
	public enum RV805EX implements Excludable<Validateable>{
		
		EXCEPTION_01{
			@Override
			public boolean isRuleObjection(Validateable validateable) {
				return false;
			};
			
		},
		
		EXCEPTION_02{
			@Override
			public boolean isRuleObjection(Validateable validateable) {
				return false;
			}
		},
		
		EXCEPTION_03(new EXRuleProducao()){
			@Override
			public boolean isRuleObjection(Validateable validateable) {
				return this.validateable.isRuleObjection(validateable);
			}

		};
		
		protected Excludable<Validateable> validateable;

		RV805EX() {}
		
		RV805EX(Excludable<Validateable> validateable) {
			this.validateable = validateable;
		}
	}
	
	@Override
	public boolean isSatisfied(Validateable validateable) {
		
		Identificacao identificacao = validateable.getIdentificacao();
		
		return identificacao.getIndIEDest() == INDI_IE_DEST.CONTRIB_ISENTO_ICMS.getCodigo();
	}
	
	@Override
	public boolean hasObjection(Validateable validateable) {
		
		for (RV805EX ex : RV805EX.values()) {
			if (ex.isRuleObjection(validateable)) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public Message getMessage() {
		return Message.REJECT_805;
	}
}
