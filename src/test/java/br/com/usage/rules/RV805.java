package br.com.usage.rules;

import br.com.annotation.Rule;
import br.com.enums.Model;
import br.com.enums.Version;
import br.com.evaluateables.Excludable;
import br.com.evaluateables.Validateable;
import br.com.messages.ValidateableMessages;
import br.com.rules.RuleValidation;
import br.com.usage.message.Messages;
import br.com.usage.model.IdentificacaoWrapper;
import br.com.usage.model.constants.INDI_IE_DEST;
import br.com.usage.rules.excludable.EXRuleProducao;

@Rule(version = Version.V3_10, modelo = Model.MODELO_55)
public class RV805 extends RuleValidation {
	
	private enum RV805EX implements Excludable{
		
		EXCEPTION_01{
			@Override
			public boolean isRuleObjection(Validateable validateable) {
				return false;
			}

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
				return this.excludable.isRuleObjection(validateable);
			}

		};
		
		protected Excludable excludable;

		RV805EX() {}
		
		RV805EX(Excludable excludable) {
			this.excludable = excludable;
		}
	}
	
	@Override
	public boolean isSatisfied(Validateable validateable) {

		return ((IdentificacaoWrapper)validateable).getIdentificacao().getIndIEDest() == INDI_IE_DEST.CONTRIB_ISENTO_ICMS.getCodigo();
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
