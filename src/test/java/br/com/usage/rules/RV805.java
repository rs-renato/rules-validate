package br.com.usage.rules;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.Priority;
import br.com.enums.ValidateableMessages;
import br.com.enums.Version;
import br.com.excludable.Excludable;
import br.com.rules.RuleValidation;
import br.com.usage.message.Messages;
import br.com.usage.model.Identificacao;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.constants.INDI_IE_DEST;
import br.com.usage.rules.excludable.EXRuleProducao;
import br.com.wrapper.Validateable;

@Rule(priority = Priority.HIGH, version = Version.V3_10, modelo = Model.MODELO_55)
public class RV805 extends RuleValidation {
	
	private enum RV805EX implements Excludable<Validateable>{
		
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

        IdentificacaoWrapper wrapper = (IdentificacaoWrapper) validateable;
		
		Identificacao identificacao = wrapper.getIdentificacao();
		
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
	public ValidateableMessages getValidationMessage() {
		return Messages.REJECT_805;
	}
}
