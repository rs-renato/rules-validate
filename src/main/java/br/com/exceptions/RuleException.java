package br.com.exceptions;

import br.com.enums.Message;

public class RuleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private Message mensagemNFe;
	
	public RuleException(Message mensagenNfe) {
		this.mensagemNFe = mensagenNfe;
	}
	
	public Message getMensagemNFe() {
		return mensagemNFe;
	}
}
