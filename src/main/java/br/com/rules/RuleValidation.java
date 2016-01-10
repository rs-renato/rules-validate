package br.com.rules;

import java.util.Date;

import br.com.rules.enums.CampoSequencial;
import br.com.rules.enums.Message;
import br.com.rules.enums.ModeloNFe;
import br.com.rules.exceptions.RuleException;
import br.com.rules.wrapper.Validateable;

public abstract class RuleValidation{

	private Date inicioVigencia;
	private Date finalVigencia;
	
	private CampoSequencial campoSequencial;
	
	private ModeloNFe[] modeloNFe;
	private float version;
	
	private PRIORITY priority;
	
	protected enum PRIORITY{
		LOW,
		MEDIUM,
		HIGH
	};
	
	public RuleValidation(PRIORITY priority, float version, ModeloNFe ...modeloNFe) {
		this.priority = priority;
		this.version = version;
		this.modeloNFe = modeloNFe;
	}
	
	public RuleValidation(float version, ModeloNFe ...modeloNFe) {
		this.priority = PRIORITY.LOW;
		this.version = version;
		this.modeloNFe = modeloNFe;
	}
	
	
	public abstract Message getMessage();
	
	public abstract boolean isSatisfied(Validateable validateable);
	
	public abstract boolean hasObjection(Validateable validateable);
	
	public void validate(Validateable validateable) throws RuleException{
		
		if (isSatisfied(validateable) && !hasObjection(validateable)) {
			
			throw new RuleException(getMessage());
		}
		
		System.out.println("Regra valida!!!");
	}

	public ModeloNFe[] getModeloNFe() {
		return modeloNFe;
	}

	public void setModeloNFe(ModeloNFe[] modeloNFe) {
		this.modeloNFe = modeloNFe;
	}

	public float getVersion() {
		return version;
	}

	public void setVersion(float version) {
		this.version = version;
	}

	public PRIORITY getPriority() {
		return priority;
	}

	public void setPriority(PRIORITY priority) {
		this.priority = priority;
	}
	
	
}