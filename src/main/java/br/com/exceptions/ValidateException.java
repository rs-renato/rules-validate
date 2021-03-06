package br.com.exceptions;

import br.com.messages.ValidateableMessages;

/**
 * Exception used to represent validation troubles
 */
public class ValidateException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private final ValidateableMessages mensagemNFe;
	
	public ValidateException(ValidateableMessages mensagenNfe) {
		this.mensagemNFe = mensagenNfe;
	}
	
	public ValidateableMessages getMensagemNFe() {
		return mensagemNFe;
	}
}
