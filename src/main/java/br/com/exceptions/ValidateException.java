package br.com.exceptions;

import br.com.enums.ValidationMessages;

/**
 * Exception used to represent validation troubles
 */
public class ValidateException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private ValidationMessages mensagemNFe;
	
	public ValidateException(ValidationMessages mensagenNfe) {
		this.mensagemNFe = mensagenNfe;
	}
	
	public ValidationMessages getMensagemNFe() {
		return mensagemNFe;
	}
}
