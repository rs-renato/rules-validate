package br.com.rules;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.Priority;
import br.com.enums.ValidationMessages;
import br.com.enums.Version;
import br.com.excludable.Excludable;
import br.com.model.Identificacao;
import br.com.model.constants.INDI_IE_DEST;
import br.com.rules.excludable.EXRuleProducao;
import br.com.wrapper.Validateable;

@Rule(priority = Priority.HIGH, version = Version.V3_10, modelo = Model.MODELO_55)
public class RV805 extends RuleValidation{
	
	private static enum RV805EX implements Excludable<Validateable>{
		
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
	public ValidationMessages getValidationMessage() {
		return ValidationMessages.REJECT_805;
	}
}
